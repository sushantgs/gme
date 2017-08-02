
<div>
	<div ng-controller="fileCtrl">
		<input ng-model="fileName" type="text">
		<input ng-model="fileDescription" type="text">
		<input type="file" file-model="billFile"/>
		<button ng-click="uploadFile()">Upload</button>
	</div>

	<div ng-controller="attachGrdCtrl">
		<md-button class="md-raised md-primary" ng-click='loadData();'>Refreh</md-button>
		<div ui-grid="gridOptions" class="grid" ng-init='loadData();'></div>
	</div>
</div>
