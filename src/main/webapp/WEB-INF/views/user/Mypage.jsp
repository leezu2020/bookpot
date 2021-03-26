<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>책단지: 마이페이지</title>
<link rel="stylesheet" href="/resources/css/Mypage CSS.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/resources/js/Mypage JS.js" defer></script>

</head>


<!--css 작업 후 이미지 파일 앞에 /resources 다시 붙이기 -->


<!-- 로그인시 (class와 onclick 링크 수정 필요-->
<sec:authentication property="principal.name" var="email" />
<sec:authentication property="principal.username" var="nickname" />
<sec:authentication property="principal.no" var="userNo" />
<body>
	<div class="container">
		<div class="header">
			<div id="logo">
				<!-- 책 아이콘 -->
				<img src="/resources/icon/logo.svg" alt="logo-icon">
				<!--로고 이미지 작업 필요-->
				<!--책단지 아이콘 클릭시 메인화면 이동-->
				<a href="/"> <img class="site-name"
					src="/resources/icon/책단지.svg" alt="책단지-icon">
				</a>
			</div>
			<nav>
				<sec:authorize access="isAuthenticated()">
					<!--write버튼, 회원가입버튼, login버튼 묶음-->
					<button type="button">${nickname}님환영합니다</button>
					<!-- 본인 이름 클릭했을 때, 정보조회 링크 -->
					<a href="/users/${userNo}">
						<button type="button" class="login">회원정보</button>
					</a>
					<button type="sign-up" class="sign-up"
						onclick="location.href='/logout'">로그아웃</button>
					<!--회원가입 페이지 이동-->
					<button type="write" id="write-button"
						onclick="location.href='/writing/reg'">글쓰기</button>
					<!--글쓰기 페이지 이동-->
				</sec:authorize>
			</nav>
		</div>




		<!--side-->
		<div class="side">

			<!--이미지 수정-->
			<div class="image-container">
				<img id="preview-image"
					src="https://dummyimage.com/500x500/ffffff/000000.png&text=preview+image">
				<input style="display: block;" type="file" id="input-image">
			</div>



			<div id="mypage-icon">

				<!--내가 쓴 글 아이콘-->
				<div id="what-i-wrote">
					<a href="https://www.naver.com"> <img id="pencil-img"
						src="/resources/icon/pencil.svg" alt="pencil-icon"><br>
						<p id="pencil-letter">내가 쓴 글</p>
					</a>
				</div>

				<!--스크랩 아이콘-->
				<div id="scrap">
					<a href="https://www.naver.com"> <img id="scrap-img"
						src="/resources/icon/scrap.svg" alt="scrap-icon"><br>
						<p id="scrap-letter">스크랩</p>
					</a>
				</div>

				<!--댓글 쓴 글 아이콘-->
				<div id="comments">
					<a href="https://www.naver.com"> <img id="comments-img"
						src="/resources/icon/comments.svg" alt="comments-icon"><br>
						<p id="comments-letter">댓글</p>
					</a>
				</div>



			</div>

		</div>





		<!--오른쪽 내 정보 수정 부분-->
		<div class="main">
			<form action="">
				<h2>내 정보</h2>
				<div id="green-box"></div>

				<!--닉네임 수정-->
				<p>
					<label for="nickname">닉네임</label><br> <input id="nickname"
						type="text" placeholder="${nickname}">
					<input type="button" id="nickname-button" value="중복확인" onClick="fn_checkNickname()">
				</p>

				<!--이메일-->
				<p>
					<label for="email">이메일</label><br>${email}
				</p>

				<!--비밀번호 수정-->
				<p>
					<label for="password">비밀번호</label><br> <input id="password"
						type="password" placeholder="8~16자 영문 대 소문자, 숫자를 사용하세요.">
				</p>
				<!--비밀번호 수정 확인-->

				<p>
					<label for="repassword">비밀번호 확인</label><br> <input
						id="repassword" type="text" placeholder="비밀번호가 일치하지 않습니다.">
				</p>

				<!--적용 & 취소 버튼-->
				<div class="upload-buttons">
					<button id="update-button">적용</button>
					<button id="cancel-button">취소</button>
				</div>

				<div id="grey-line"></div>

				<p>
					<button id="logout-button">로그아웃</button>
				</p>

				<p>
					<button id="withdrawal-button">회원탈퇴</button>
				</p>



			</form>
		</div>
	</div>

</body>


</html>