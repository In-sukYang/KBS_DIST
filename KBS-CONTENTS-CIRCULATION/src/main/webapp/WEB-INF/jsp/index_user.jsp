<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>KBS 유통정보시스템</title>
<link rel="stylesheet" type="text/css" href="/resources/css/base.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/layout.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
<script type="text/javascript" src="/resources/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<!--[if lte IE 8]>
<script type="text/javascript" src="/resources/js/html5.js"></script>
<![endif]-->
</head>
<body>
	<div id="wrap" class="login">
		<!-- 콘텐츠 시작  -->
		<div id="container">
			<div id="contents">
				<div class="login-bx" id="login">
					<div class="login-box">
						<h2>
							<img src="/resources/img/common/logo.png" alt="KBS 유통정보시스템" />
						</h2>
						<div class="mb-bx">
							<h3 class="login-title">Login</h3>
							<div class="log-txt">
								<p class="title">사용자 로그인</p>
							</div>
							<div class="login-form">
								<div class="mb-id">
									<label for="mb-id">ID</label><input type="text" id="mbId" />
								</div>
								<div class="mb-pw">
									<label for="mb-pw">PW</label><input type="password" id="mbPw" />
								</div>
								<div class="mb-btn" id=loginBtn>
									<a href="#">LOGIN</a>
								</div>
								<div class="txt">* ID 와 Password를 입력하여 주시길 바랍니다.</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

	<script>
		$(document).ready(function() {
			
			$("#mbId").keypress(function(key) {
				if(key.keyCode == 13) {
					loginCheck();					
				}
				
			});
			
			$("#mbPw").keypress(function(key) {
				if(key.keyCode == 13) {
					loginCheck();					
				}
			});
			
			$('#loginBtn').click(function (){
				loginCheck();
			});
			
			function loginCheck() {
				if($("#mbId").val() == ''){
					alert('아이디를 입력해 주세요.');
					return;
				}
				
				if($("#mbPw").val() == ''){
					alert('비밀번호를 입력해 주세요.');
					return;
				}
				
				var loginType = '02';
				
				$.ajax({
					type :	"post",
					url  : 	"/login/loginCheck",
					datatype : "json",
					async: false,
					data : {
						userId 	: $("#mbId").val(),
						userPw	: $("#mbPw").val(),
						loginType : loginType
					},
					success : function(result){
						var code = result.loginAccount;
						if(code == 0 ){
							alert('아이디/비밀번를 확인하고 로그인해주세요!');
							return;
						}
						$(location).attr('href', result.firstPage);
						
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
		});
	</script>
</html>