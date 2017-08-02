<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>GM Enterprises</title>

	<spring:url value="/resources/css/gme.css" var="coreCss" />
	<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${coreCss}" rel="stylesheet" />
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	  <div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">GM Enterprises</a>
		</div>
	  </div>
	</nav>

	<div class="jumbotron">
	  <div class="container">
			<h1>${title}</h1>
			<p>
				<c:if test="${not empty msg}">
					Hello ${msg}
				</c:if>
				<c:if test="${empty msg}">
					Welcome Welcome!
				</c:if>
			</p>
			<p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
		</div>
	</div>

	<div class="container">
  <div class="row">
		<div class="col-md-4">
			<h2>Upload Bills</h2>
			<p>To upload bills</p>
			<p>
				<a class="btn btn-default" href="uploadBill" role="button">View details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Bills</h2>
			<p>To view bills</p>
			<p>
				<a class="btn btn-default" href="billBoot" role="button">View details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Reports</h2>
			<p>To view reports</p>
			<p>
				<a class="btn btn-default" href="report" role="button">View details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Angular</h2>
			<p>To view angular application</p>
			<p>
				<a class="btn btn-default" href="gme" role="button">View details</a>
			</p>
		</div>
  </div>
  	<hr>
	  <footer>
		<p>© Vaayu 2017</p>
	  </footer>
	</div>

	<spring:url value="/resources/js/gme.js" var="coreJs" />
	<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />

	<script src="${coreJs}"></script>
	<script src="${bootstrapJs}"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>