<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>책단지</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/Sign-up CSS.css" />">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="<c:url value="/resources/js/jquery-3.6.0.min.js" />"></script>
</head>

<!-- 중복확인 처리를 위한 script -->
<script type="text/javascript">
		var code = '';
		var setting = '';
			function fn_checkNickname(){
				$.ajax({
					url : "/join/nickname/" + $('#nickname').val(),
					type : "get",
					success : function(result){
						if(result == 'exist'){
							alert('사용중인 닉네임입니다.');
							$('#nickname').focus();
							$('#nickname').val('');
						} else if(result == 'notmatch'){
							alert('닉네임을 확인해주세요.');
							$('#nickname').focus();
							$('#nickname').val('');
						} else {
							alert('사용가능한 닉네임입니다.');
						}
					},
					error: function(e){
						alert("닉네임 값을 가져오지 못했습니다.");
					}
				});
			};
			
			function fn_sendEmail(){
				$.ajax({
					url : "/email/" + $('#email').val(),
					type : "get",
					success : function(result){
						if(result == 'exist'){
							alert('사용중인 이메일입니다.');
							$('#inputCode').attr("disabled",true);
							$('#code-button').attr("disabled",true);
							$('#email').focus();
							$('#email').val('');
						} else {
							alert('이메일이 전송되었습니다.');
							$('#inputCode').attr("disabled",false);
							$('#code-button').attr("disabled",false);
							code = result;
						}
					}
				});
			};
			
			function fn_checkCode(){
				var inputCode = $('#inputCode').val();
				if(inputCode == code){
					alert('인증되었습니다.');
					setting = 'finished';
				} else {
					$('#inputCode').focus();
					$('#inputCode').val('');
					alert('인증번호를 다시 확인해주세요.');
				}
			}
$(document).ready(function(){	
		$('#submit-button').click(function (){
			
			var params = $('#signup-form').serialize();		
			console.log(params);
			event.preventDefault();
			$.ajax({
				url : "/join",
				type : "post",
				dataType : "json",
				data : params,
				success : function(response){
					console.log(response);
				},
				error : function(request, status, error){
					$.each(request.responseJSON, function(i, error){
						console.log("에러 : " + error);
						switch(error){
						case "wrongpassword":
							$('#password').val('');
							$('#passwordCheck').val('');
							break;
						case "notmatchpassword":
							$('#password').val('');
							$('#passwordCheck').val('');
							break;
						case "existnickname":
							$('#password').val('');
							$('#passwordCheck').val('');
							$("#nickname").val('');
							break;
						case "existemail":
							$('#password').val('');
							$('#passwordCheck').val('');
							$("#email").val('');
							break;
						case "emptynickname":
							$('#password').val('');
							$('#passwordCheck').val('');
							break;
						case "emptypassword":
							$('#password').val('');
							$('#passwordCheck').val('');
							break;
						case "emptyemail":
							$('#password').val('');
							$('#passwordCheck').val('');
							break;
						}
					})
				}
			})
		})
})
		</script>
<body>
    <div class="container">
        <div class="header">
            <div id="logo">
            	<!-- 클릭시 메인페이지 -->
                <!-- 책 아이콘 -->
                <img src="<c:url value="/resources/icon/logo.svg" />" alt="logo-icon"> <!--로고 이미지 작업 필요-->
                
            	<a href="/">
                <!--책단지 아이콘-->
                <img class="site-name" src="<c:url value="/resources/icon/책단지.svg" />" alt="책단지-icon">
           		</a>
            </div>
            <nav><!--write버튼, 회원가입버튼, login버튼 묶음-->
                <!-- 비로그인시 -->
           		<sec:authorize access="!isAuthenticated()">
	                <button type="button" class="login" onClick="location.href='/login'">로그인</button>
	                <!-- 
	                    <div class="login-info">
	                        <form action="">
	                            <label for="login-id">이메일로 로그인</label>
	                            <input type="email" id="login-id" placeholder="이메일을 입력하세요">
	                            <label for="login-password">비밀번호 입력</label>
	                            <input type="password" id="login-password" placeholder="비밀번호를 입력하세요">
	                            <button type="submit">로그인</button>
	                        </form>
	                    </div> //아이디,비밀번호 dropdown 입력창 묶음
	                -->
	                <!--로그인 버튼과 눌렀을때 dropdown되는 입력창들 묶음-->
	                <button type="sign-up" class="sign-up" onClick="location.href='/join/signup'">회원가입</button>
	                <!--회원가입 페이지 이동-->
                </sec:authorize>
                
	            <!-- 로그인시 (class와 onclick 링크 수정 필요-->
	            <sec:authorize access="isAuthenticated()">
	            	<button type="button"><sec:authentication property="principal.name" />님 환영합니다</button>
	                <button type="button" class="login" >회원정보</button>
	                <button type="sign-up" class="sign-up" onclick="location.href='/logout'">로그아웃</button>
	                <!--회원가입 페이지 이동-->
	                <button type="write" id="write-button" onclick="location.href='/writing/reg'">글쓰기</button>
	                <!--글쓰기 페이지 이동-->
	            </sec:authorize>
            </nav>
        </div>
        
        <div class="side">
                <div id="quote">
                    "<br>
                </div>
                누구에게나 그의 정신에<br>
                하나의 큰 획을 그어주는<br>
                책이 있다.<br>
                -<br>
                김유정<br><br><br>
                <img class="saying-logo" src="<c:url value="/resources/icon/logo.svg" />" alt="logo-icon">
        </div>
        
        
        <div class="main">
			<form id="signup-form" name="signupForm">
				<h2>회원가입</h2>
				<div id="green-box"></div>
				<!--닉네임 입력-->
				<p>
					<label for="nickname">닉네임</label><br>
					<input id="nickname" name="nickname" type="text" placeholder="한글과 영문 대 소문자를 사용하세요(특수기호, 공백 사용 불가)" />
					<input type="button" id="nickname-button" value="중복확인" onClick="fn_checkNickname()">
				</p>

				<!--이메일 입력-->
				<p>
					<label for="email">이메일</label><br>
					<input id="email" type="email" name="email" placeholder="인증번호를 받을 이메일을 입력해주세요." />
					<input type="button" id="email-button" onClick="fn_sendEmail()" value="인증번호 발송">
					<input type="text" id="inputCode" disabled="disabled" placeholder="인증번호 입력해주세요."/>
					<input type="button" id="code-button" disabled="disabled" onClick="fn_checkCode()" value="인증번호 확인">
				</p>

				<!--비밀번호 입력-->
				<p>
					<label for="password" >비밀번호</label><br>
					<input type="password" id="password" name="password" placeholder="8~16자 영문 대 소문자, 숫자를 사용하세요." />
				</p>
				
				<!--비밀번호 확인 입력-->
				<p>
					<label for="password">비밀번호 확인</label><br>
					<input type="password" id="passwordCheck" name="passwordCheck" placeholder="비밀번호가 일치하지 않습니다." />
				</p>
				<!--개인정보 동의 내용박스-->
				<label for="agreebox">개인정보 수집, 이용에 대한 동의</label>
				<br>
				<p id="agreebox">
					- 회원가입, 사이트 이용을 위해서는 아래와 같이 개인정보를 수집, 이용 합니다.<br><br>
					1. 개인정보 수집 목적: 회원가입, 사이트 이용<br>
					2. 회원정보 수집 항목: 아이디, 닉네임, 비밀번호<br>
					3. 보유 및 이용기간: 회원 탈퇴 시까지<br>
				</p>

				<p>
					<input type="button" id="submit-button" value="동의하고 회원가입" />
					<!--모든 항목 작성완료시 활성화되도록 해야함-->
				</p>
			</form>
		</div>
    </div>

</body>

</html>
