<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">  
	<head>
		<title>GM Enterprises</title>

		<spring:url value="/resources/css/gme.css" var="gmeCSS" />
		<spring:url value="/resources/css/menu.css" var="menuCSS" />
		<spring:url value="/resources/css/ng/ui-grid.min.css" var="ngGridCSS" />

		<spring:url value="/resources/js/gme.js" var="gmeJS" />
		<spring:url value="/resources/js/ng/angular.min.js" var="ngJS" />
		<spring:url value="/resources/js/ng/angular-material.min.js" var="ngMatJS" />
		<spring:url value="/resources/js/ng/angular-route-1.4.2.js" var="ngRouteJS" />
		<spring:url value="/resources/js/ng/ui-grid.min.js" var="ngUIJS" />
		<spring:url value="/resources/js/jquery-2.0.3.min.js" var="jQueryJS" />

		<link href="${ngGridCSS}" rel="stylesheet" />
		<link href="${gmeCSS}" rel="stylesheet" />
		<link href="${menuCSS}" rel="stylesheet" />
		<script src="${ngJS}"></script>
		<script src="${ngMatJS}"></script>
		<script src="${ngRouteJS}"></script>
		<script src="${ngUIJS}"></script>
		<script src="${jQueryJS}"></script>
		<script src="${gmeJS}"></script>

		<script type="text/javascript">
		</script>
	</head>
	<body ng-app="gmeApp">
		<div >
			<p>GM Enterprises</p>
		</div>
		<div id="main">
			<nav class="{{active}}">
				<a href="#home" class="home" ng-click="active='home'">Home</a>
				<a href="#attachment" class="attachment" ng-click="active='attachment'">Attachments</a>
				<a href="#bill" class="bill" ng-click="active='bill'">Bills</a>
				<a href="#payment" class="payment" ng-click="active='payment'">Payments</a>
			</nav>
		</div>
		<ng-view></ng-view>
	</body>
</html>