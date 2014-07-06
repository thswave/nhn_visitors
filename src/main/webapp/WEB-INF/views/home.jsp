<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="VisitorBookApp">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>NHN Entertainment TOAST ROOKIE Changwon Son's Visitor Book</title>

	<link rel="stylesheet"href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="/resources/css/bootstrapValidator.min.css">
	<link rel="stylesheet" href="/resources/css/home.css">
	
	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.min.js"></script>
	<script	src="/resources/js/home.js"></script>
	
</head>
<body>
	<header class="navbar navbar-inverse navbar-static-top " role="banner">
		<div class="container">
			<a href="/" class="navbar-brand logo">NHN
				ENTERTAINMENT</a>
		</div>
	</header>
	
	
	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
			<div class="container">
				<h3>TOAST ROOKIE 손창원의 방명록</h3>
				<p>방명록 남겨주세요~ 굽신굽신!</p>
				<p>
					<button class="btn btn-primary btn-lg" id="new-visitorbook" data-toggle="modal" data-target="#visitorBookModal">
	  					방명록 작성
					</button>
				</p>
			</div>
		</div>
	</div>

	<div class="container" ng-controller="VisitorBookListController as visitorBook">
		<div ng-repeat="visitorBook in visitorBooks">
		  <div class="row">
			  <div class="col-md-4">
	        <h4>{{visitorBook.name}} / {{visitorBook.email}}</h4>
	        <p>{{visitorBook.content}}</p>
	        <p>{{visitorBook.created_at}}</p>
	        <button ng-click="showModal(visitorBook.id)" 
	        	class="btn btn-primary btn visitorbook-modify" 
	        	data-toggle="modal" data-id="{{visitorBook.id}}">
	          수정
	        </button>
	      </div>
      </div>
		</div>
		<hr>

		<footer>
			<p>© NHN Entertainment 2014</p>
		</footer>
	</div>
	
	
	<div class="modal fade" id="visitorBookModal" tabindex="-1" role="dialog" 
		aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	    	<!-- input form -->
	        <form class="form-horizontal" role="form" id="registerForm" action="/add" method="POST"
		        data-bv-message="This value is not valid"
				    data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
				    data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
				    data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
				  
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">
	        	<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	        </button>
	        <h4 class="modal-title">방명록</h4>
	      </div>
	      <div class="modal-body">
	      	  
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
					      
					    </div>
					  </div>
					  <input type="hidden" name="id" id="inputId" value="" />
					  <input type="hidden" name="created_at" id="asdfec" value="" />
				  <input type="hidden" name="updated_at" id="asdf" value="" />
					
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
	        <button type="submit" class="btn btn-primary btn-default">확인</button>
	        <!-- <button type="button" class="btn btn-primary">확인</button> -->
	      </div>
	      </form>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
	<div class="modal fade" id="passwordConfirmModal" tabindex="-1" role="dialog" 
		aria-labelledby="passwordComfirm" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
	        <span class="sr-only">Close</span></button>
	        <h4 class="modal-title">비밀번호 확인</h4>
	      </div>
	      <div class="modal-body">
	        <p class="bg-danger" id="passwordConfirmResult"></p>
				  <div class="form-group" id="password-group">
				    <label for="inputPasswordConfirm" class="col-sm-2 control-label">Password</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" name="passwordConfirm" id="inputPasswordConfirm" placeholder="Password">
				    </div>
				  </div>
				  <br/><br/>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" id="confirmPasswordBtn" class="btn btn-primary btn-default">확인</button>
				    </div>
				  </div>
				  <br/>
				  <input type="hidden" name="id" id="inputConfirmId" value="" />
				  <input type="hidden" name="created_at" id="asdfec" value="" />
				  <input type="hidden" name="updated_at" id="asdf" value="" />
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
$(document).ready(function(){
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

	$("#confirmPasswordBtn").click(function(){
	    $.ajax({
	        type: "POST",
	      url: "/check",
	      data: { "id": $("#inputConfirmId").val(),
	          "passwordConfirm": $("#inputPasswordConfirm").val()},
	      dataType: 'json',
	      success: function(resultData){
	          if(resultData === true){
	              $.setRegisterFormModal($("#inputConfirmId").val());
	          } else{
	              $("#passwordConfirmResult").text("비밀번호가 잘못됬습니다.");
	              $('#passwordConfirmResult').delay(1000).fadeOut('slow');
	          }
	      },
	      error: function(){
	   console.log("something went wrong");
	    }
	    });
	});
	    
	$('.modal').on('hidden.bs.modal', function(){
	    $(this).find('form')[0].reset();
	    $("#registerForm").attr("action","/add");
	});
	    
	$.setRegisterFormModal = function(id){
	    $('#passwordConfirmModal').modal('hide');
	    $("#inputId").val(id);
	    $("#registerForm").attr("action","/update");
	    $("#visitorBookModal").modal("show");
	    
	    $.ajax({
	      url: "/get/" + id,
	      dataType: 'json',
	      success: function(resultData){
	          console.log(resultData);
	          $("#inputName").val(resultData.name);
	            $("#inputPassword").val(resultData.password);
	            $("#inputContent").val(resultData.content);
	            $("#inputEmail").val(resultData.email);
	      },
	      error: function(){
	        console.log("something went wrong");
	      }
	    });
	};
});

	</script>
</body>
</html>