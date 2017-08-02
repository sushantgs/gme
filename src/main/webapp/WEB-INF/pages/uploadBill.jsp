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

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">GM Enterprises</a>
	</div>
  </div>
</nav>

<div class="jumbotron">
  <div class="container">

	<form method="POST" action="upload" enctype="multipart/form-data">
	  <div class="row">
		<div class="col-md-1">
			<input id="fileName" name="fileName"  type="text">
			<input id="fileDescription" name="fileDescription"  type="text">
			<label class="control-label">Select File</label>
			<input id="file" name="file"  type="file" class="file">
			<p>
				<input type="submit" value="Upload">
			</p>
		</div>
	  </div>
  </form>

  <hr>
  <footer>
		<p>© Vaayu 2017</p>
  </footer>
	</div>
</div>

<spring:url value="/resources/js/gme.js" var="coreJs" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>