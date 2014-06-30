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

<link rel="stylesheet"href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/resources/css/bootstrapValidator.min.css">
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
				<h3>TOAST ROOKIE 손창원에게 방명록 남기기!</h3>
				<p>방명록 남겨주세요~ 굽신굽신!</p>
				<p>
					<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#visitorBook">
	  					방명록 작성
					</button>
				</p>
			</div>
		</div>
	</div>

	<div class="container">
		<div id="notify" >
			<c:if test="${nofity == 'success'}" >
				<p class="bg-success"> 입력되었습니다. </p>
			</c:if>
			<c:if test="${nofity == 'fail'}" >
				<p class="bg-danger"> 이메일형식이 잘못되어 입력이 실패되었습니다. </p>
			</c:if>
		</div>
		
		
		<c:set var="rowCount" value="${0}" />
		<c:if test="${not empty visitorBookList}" >
			<c:forEach var="visitorBook" items="${visitorBookList}">
				<c:if test="${rowCount == 0}" >
					<c:set var="rowCount" value="${rowCount + 1}" />
					<div class="row">
				</c:if>
					<div class="col-md-4">
						<h4>${visitorBook.name} / ${visitorBook.email} ${visitorBook.email}</h4>
						<p>${visitorBook.content}</p>
						<p>${visitorBook.created_at}</p>
						<button class="btn btn-primary btn" data-toggle="modal" data-target="#visitorBook">
	  					수정
						</button>
					</div>
					
				<c:if test="${rowCount == 2}" >
					<c:set var="rowCount" value="${0}" />
					</div>
				</c:if>
			</c:forEach>
		</c:if>
		
		<hr>

		<footer>
			<p>© NHN Entertainment 2014</p>
		</footer>
	</div>
	
	
	<div class="modal fade" id="visitorBook" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title">방명록</h4>
	      </div>
	      <div class="modal-body">
	      	<!-- input form -->
	        <form class="form-horizontal" role="form" id="registerForm" action="/add" method="post"
		        data-bv-message="This value is not valid"
				    data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
				    data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
				    data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
				    
	        	<div class="form-group" id="id-group">
					    <label for="inputName" class="col-sm-2 control-label">Name</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" name="name" id="inputName" placeholder="Name">
					    </div>
					  </div>
					  <div class="form-group" id="email-group">
					    <label for="inputEmail" class="col-sm-2 control-label">Email</label>
					    <div class="col-sm-10">
					      <input type="email" class="form-control" name="email" id="inputEmail" placeholder="Email">
					    </div>
					  </div>
					  <div class="form-group" id="password-group">
					    <label for="inputPassword" class="col-sm-2 control-label">Password</label>
					    <div class="col-sm-10">
					      <input type="password" class="form-control" name="password" id="inputPassword" placeholder="Password">
					    </div>
					  </div>
					  <div class="form-group" id="content-group">
					    <label for="inputPassword" class="col-sm-2 control-label">Content</label>
					    <div class="col-sm-10">					      
					      <textarea id="inputContent" class="form-control" name="content" rows="4"></textarea>
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="submit" class="btn-primary btn-default">확인</button>
					    </div>
					  </div>
					</form>
					
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
	        <!-- <button type="button" class="btn btn-primary">확인</button> -->
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<script	src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script	src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script	src="/resources/js/bootstrapValidator.min.js"></script>
	
	
	<script>
	$(document).ready(function() {
		$('#notify').delay(1000).fadeOut('slow');
		
		$('#registerForm').bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				name : {
					validators : {
						notEmpty : {
							message : '이름을 입력해주세요.'
						}
					}
				},
				email : {
					validators : {
						notEmpty : {
							message : '이메일을 입력해주세요.'
						},
						emailAddress : {
							message : '이메일 형식이 잘못되었습니다.'
						}
					}
				},
				password : {
					validators : {
						notEmpty : {
							message : '비밀번호를 입력해주세요.'
						}
					}
				},
				content : {
					validators : {
						notEmpty : {
							message : '내용을 입력해주세요.'
						}
					}
				}
			}
		});
	});
	</script>
</body>
</html>