
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

$(".visitorbook-modify").click(function(){
	$("#inputConfirmId").val($(this).data("id"));
	$('#passwordConfirmModal').modal('show');
	$("#inputPasswordConfirm").val("");
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