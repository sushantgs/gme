
var app = angular.module('gmeApp', ['ngRoute', 'ui.grid',
                                    'ui.grid.pagination', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav', 'ui.grid.exporter']);

app.config(function($routeProvider){

	$routeProvider
		.when('/home',{
			templateUrl: 'home'
		}).when('/attachment',{
			templateUrl: 'attachment'
		}).when('/bill',{
			templateUrl: 'bill'
		}).when('/payment',{
			templateUrl: 'payment'
		}).otherwise({
			redirectTo:'/gme'
		});

});

app.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;

          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
}]);
app.service('fileUpload', ['$http', function ($http, $rootScope) {
    this.uploadFileToUrl = function(fd, uploadUrl) {

        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function() {
        	//$scope.fileName = "";
        	//$scope.fileDescription = "";
        	//$scope.billFile = "";
        	$rootScope.$broadcast('reloadAttGrd', {
                data: 'reload'
            });
        })
        .error(function() {
        });
    }
}]);
app.controller('fileCtrl', ['$scope', 'fileUpload', function($scope, fileUpload) {
    $scope.uploadFile = function() {
       var fd = new FormData();
       fd.append('fileName', $scope.fileName);
       fd.append('fileDescription', $scope.fileDescription);
       fd.append('file', $scope.billFile);

       var uploadUrl = "upload";
       fileUpload.uploadFileToUrl(fd, uploadUrl);
    };
 }]);


app.controller('billCtrl', function($scope, $window) {
    $scope.myData = $window.bills;
    //columnDefs: [{field:'name', displayName:'Name'}, {field:'age', displayName:'Age'}]
});

app.controller('attachGrdCtrl', function ($scope, $http) {
//app.controller('attachGrdCtrl', ['$scope', '$http', 'fileUpload', function($scope, $http, fileUpload) {
	$scope.loadData = function() {
		$http.get('getAttachments')
		.success(function(arg) {
			$scope.attachmentsData = arg;
		}).error(function(err) {
			$scope.attachmentsData = err;
		});
	}
	$scope.$on('reloadAttGrd', function(event, data) {
        $scope.loadData();
    })
	
	$scope.gridOptions = {
			data: 'attachmentsData',
			columnDefs: [
			             {field:'attachmentID', displayName:'Attachment ID', visible: false},
			             {field:'name', displayName:'Name',
			            	 cellTemplate:'<div>' +
		                       '<a href="#bill?attachmentID={{ row.entity.attachmentID }}">{{ row.entity.name }}</a></div>' },
			             {field:'description', displayName:'Description'},
			             {field:'createdTimestamp', displayName:'Uploaded On', cellFilter: 'date:\'dd-MM-yyyy HH:mm\''}]
    };
});

app.controller('billGrdCtrl', ['$scope', '$http', '$location', function ($scope, $http, $location) {
	
	$scope.active='bill';
	
	$scope.gridOptions = {
			data: 'billsData',
			enableGridMenu: true,
			exporterCsvFilename: 'gme.csv',
			exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
			enableFiltering: true,
			paginationPageSizes: [25, 50, 75],
			paginationPageSize: 25,
			columnDefs: [
			             {field:'billID', displayName:'Bill ID', visible: false},
			             {field:'billNo', displayName:'Bill Number',
			            	 cellTemplate:'<div>' +
		                       '<a href="#payment?billID={{ row.entity.billID }}">{{ row.entity.billNo }}</a></div>' },
			             {field:'billDate', displayName:'Date', cellFilter: 'date:\'dd-MM-yyyy\''},
			             {field:'dsrName', displayName:'DSR'},
			             {field:'routeName', displayName:'Route'},
			             {field:'retailName', displayName:'Retail'},
			             {field:'netAmount', displayName:'Net Amount'},
			             {field:'balance', displayName:'Balance'}]
    };

	$scope.loadData = function() {
		$http.get('getBills?attachmentID='+$location.search().attachmentID)
		.success(function(arg) {
			$scope.billsData = arg;
		}).error(function(err) {
			$scope.billsData = err;
		});
	}
}]);

app.controller('payGrdCtrl', ['$scope', '$http', '$location', '$q', '$interval', function ($scope, $http, $location, $q, $interval) {

	$scope.active='payment';

	$scope.gridOptions = {
			data: 'paymentData',
			enableFiltering: true,
			enableCellEditOnFocus: true,
			columnDefs: [
			             {field:'paymentID', displayName:'Payment ID', visible: false},
			             {field:'billID', displayName:'Bill ID', visible: false},
			             {field:'amount', displayName:'Amount', enableCellEdit: true},
			             {field:'amountType', displayName:'Type', enableCellEdit: true},
			             {field:'chequeNo', displayName:'Cheque No', enableCellEdit: true},
			             {field:'chequeIssueDate', displayName:'Cheque Issue Date', cellFilter: 'date:\'dd-MM-yyyy\'', enableCellEdit: true},
			             {field:'receivedDate', displayName:'Received Date', cellFilter: 'receivedDate:\'dd-MM-yyyy\'', enableCellEdit: true,
			            	 editableCellTemplate: '<div><form name="inputForm"><div ui-grid-edit-datepicker ng-class="\'colt\' + col.uid"></div></form></div>'},
			             {field:'receiver', displayName:'Receiver', enableCellEdit: true}]
    };

	$scope.loadData = function() {
		$http.get('getPayments?billID='+$location.search().billID)
		.success(function(arg) {
			$scope.paymentData = arg.payments;
			$scope.billData = arg;
		}).error(function(err) {
			$scope.paymentData = err;
		});
	}

    var config = {
        headers : {
        	'contentType': 'application/json'
        }
    }
    $scope.saveRow = function(rowEntity) {
        // create a fake promise - normally you'd use the promise returned by $http or $resource
        var promise = $q.defer();
        $scope.gridApi.rowEdit.setSavePromise(rowEntity, promise.promise);
        rowEntity.balance = rowEntity.netAmount - rowEntity.amount;
        $scope.updateRow(rowEntity);

        // fake a delay of 2 micro seconds whilst the save occurs
        $interval( function() {
        	promise.resolve();
        }, 20, 1);
	};

	$scope.gridOptions.onRegisterApi = function(gridApi) {
		//set gridApi on scope
		$scope.gridApi = gridApi;
		gridApi.rowEdit.on.saveRow($scope, $scope.saveRow);
	};

	$scope.updateRow = function(row) {
	    $http.post('updatePayment', row, config)
	    .success(function() {
        });
	}

	$scope.addRow = function() {
      $scope.paymentData.push({billID:$location.search().billID, amount: '0'});
    };
}]);

app.filter('receivedDate', ['$filter', function ($filter) {
    return function (input, format) {
        var date = new Date(input);
        return $filter('date')(date, format);
    };
}]);
