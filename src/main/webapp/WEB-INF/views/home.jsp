<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NHN Entertainment TOAST ROOKIE Changwon Son's Visitor
	Book</title>

<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

</head>
<body>
	<header class="navbar navbar-inverse navbar-static-top " role="banner">
		<div class="container">
			<a href="#" class="navbar-brand"
				style="margin-top: 9px; background: url(/resources/images/sp_common_v3.png) no-repeat; line-height: 999px; overflow: hidden;">NHN
				ENTERTAINMENT</a>
		</div>
	</header>
	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
			<div class="container">
				<h3>방명록 남기기!</h3>
				<p>방명록 남겨주세요~ 굽신굽신!</p>
				<p>
					<a class="btn btn-primary btn-lg" role="button">방명록 작성</a>
				</p>
			</div>
		</div>
	</div>
	<% int rowCount = 0; %>

	<div class="container">
		<c:if test="${not empty visitorBookList}" >
			<c:forEach var="visitorBook" items="${visitorBookList}">
			<% if (rowCount == 0 ){  
						++rowCount;
			%>
				<div class="row">
			<% } %>
			
				<div class="col-md-4">
					<h4>${visitorBook.name} / ${visitorBook.email} ${visitorBook.email}</h4>
					<p>${visitorBook.content}</p>
					<p>${visitorBook.created_at}</p>
					<p><a class="btn btn-default" href="#" role="button">수정</a>	</p>
				</div>
				
			<% if (rowCount == 2 ){  
						rowCount = 0;
			%>
				</div>
			<% } %>	
			</c:forEach>
		</c:if>
		
		<hr>

		<footer>
			<p>© NHN Entertainment 2014</p>
		</footer>
	</div>

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>