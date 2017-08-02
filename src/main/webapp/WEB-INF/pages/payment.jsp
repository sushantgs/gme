
<div ng-controller="payGrdCtrl">
	<h2>Payments</h2>
	<div class="payLblCls">
		<p>Bill No: {{ billData.billNo }}</p>
		<p>Bill Date: {{ billData.billDate }}</p>
		<p>Net Amount: {{ billData.netAmount }}</p>
		<p>Balance: {{ billData.balance }}</p>
	</div>
	<button ng-click="addRow()">Add Row</button>
	<md-button class="md-raised md-primary" ng-click='loadData();'>Refresh</md-button>
	<div ui-grid="gridOptions" class="grid" ng-init='loadData();' ui-grid-edit ui-grid-row-edit ui-grid-cellnav></div>
</div>
