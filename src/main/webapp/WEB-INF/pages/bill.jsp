
<div ng-controller="billGrdCtrl">
	<md-button class="md-raised md-primary" ng-click='loadData();'>Refresh</md-button>
	<div ui-grid="gridOptions" class="grid" ng-init='loadData();' ui-grid-pagination ui-grid-exporter></div>
</div>
