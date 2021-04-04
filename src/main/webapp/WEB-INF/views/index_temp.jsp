<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>책단지</title>
    <link rel="stylesheet" href="/resources/css/Home CSS.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script defer src="/resources/js/Home JS.js"></script>
</head>

<script type="text/javascript">
	$(document).ready(function(){
		
		$.ajax({
			url : "/writing/view/grid",
			type : "get",
			dataType : "json",
			success : function(data){
				console.log(data);
			}
		});
	});
</script>

<body>
    <div class="container">
        <header>
            <div id="logo">
                <!-- 책 아이콘 -->
                <img src="/resources/icon/logo.svg" alt="logo-icon"> <!--로고 이미지 작업 필요-->
                <!--책단지 아이콘-->
                <!--책단지 아이콘 클릭시 메인화면 이동-->
                <a href="/">
                <img class="site-name" src="/resources/icon/책단지.svg" alt="책단지-icon">
          		</a>
            </div>
            <nav><!--write버튼, 회원가입버튼, login버튼 묶음-->
            
            <!-- 비로그인시 -->
            <sec:authorize access="!isAuthenticated()">
                <button type="button" class="login" name="login">로그인</button>
				<button type="sign-up" class="sign-up" onclick="location.href='/join/signup'">회원가입</button>
                <!--회원가입 페이지 이동-->
            </sec:authorize>
            
            <div id="login-form-container">
					<form class="login-form" action="" method="">
						<button class="login-form-hide" type="button">X</button>
						<div class="logo">
							<!-- 책 아이콘 -->
							<img src="/resources/icon/logo.svg" alt="logo-icon">
							<!--로고 이미지 작업 필요-->
							<!--책단지 아이콘-->
							<img class="site-name" src="/resources/icon/책단지.svg" alt="책단지-icon">
						</div>
						<label for="login-email">이메일</label>
						<input type="email"id="login-email" placeholder="이메일을 입력하세요">
						<label for="login-password">비밀번호</label>
						<input type="password" id="login-password" placeholder="비밀번호를 입력하세요">
						<button id="login-form-submit" type="submit">로그인</button>
					</form>
				</div>
				<!-- 아이디,비밀번호 dropdown 입력창 묶음 -->
				<!--로그인 버튼과 눌렀을때 dropdown되는 입력창들 묶음-->
            <!-- 로그인시 (class와 onclick 링크 수정 필요-->
            
            <sec:authorize access="isAuthenticated()">
            	<sec:authentication property="principal.username" var="nickname"/>
            	<sec:authentication property="principal.no" var="userNo"/>
            	<button type="button">${nickname}님 환영합니다</button>
            	<!-- 본인 이름 클릭했을 때, 정보조회 링크 -->
            	<a href="/users/${userNo}">
                <button type="button" class="login" >회원정보</button>
                </a>
                <button type="sign-up" class="sign-up" onclick="location.href='/logout'">로그아웃</button>
                <!--회원가입 페이지 이동-->
                <button type="write" id="write-button" onclick="location.href='/writing/reg'">글쓰기</button>
                <!--글쓰기 페이지 이동-->
            </sec:authorize>
            </nav>
        </header>
        
        <div id="category">
            <div id="searching-menu">
                <img src="/resources/icon/search.svg">
                <label for="searching" class="search">책 찾아보기</label>
            </div>     
            <div class="searching-input">
                <input type="" id="searching" placeholder="찾는 책의 제목/키워드를 입력하세요">
                <button type="reset">X</button><!--쓴것 초기화 button-->
            </div>
            <!--버튼 누르면 색 변하도록 js작업하기-->
            <div id="filter-menu">
                <img src="/resources/icon/filter.svg" >
                <div class="filter">필터</div>
            </div>
            <span class="category-menu">국내/외국</span>
            <div class="book-from">
                <button class="domestic" type="button">국내도서</button>
                <button class="overseas" type="button">외국도서</button>
            </div>
            <span class="category-menu">분야</span>
            <div class="book-type">
                <button class="field" type="button">소설</button>
                <button class="field" type="button">예술</button>
                <button class="field" type="button">역사/문화</button>
                <button class="field" type="button">인문</button>
                <button class="field" type="button">비문학</button>
                <button class="field" type="button">경제/경영</button>
                <button class="field" type="button">정치</button>
                <button class="field" type="button">영어</button>
                <button class="field" type="button">과학</button>
                <button class="field" type="button">여성학</button>
                <button class="field" type="button">고전</button> 
                <button class="field" type="button">자기계발</button> 
                <button class="field" type="button">취미/실용</button> 
                <button class="field" type="button">컴퓨터</button> 
                <button class="field" type="button">어린이</button> 
                <!--더 적어야함-->
            </div>
            <div class="category-action">
                <button class="filter-search" type="button">찾기</button>
                <button class="initialize" type="reset">초기화</button> <!--위에서 누른것들 초기화되도록-->
            </div>
        </div>
        
        <!--인기글, 최신순 탭 + 그리드&리스트 뷰-->
        <div id="array">
            <div class="content-array">
                <div class="best">인기</div>
                <div class="latest">최신</div>
            </div>
            <div class="view-type">
                <img src="/resources/icon/list_black.svg">
                <img src="/resources/icon/grid_black.svg">
                <!--따로 버튼 없이 선택하자마자 적용되는 방법은?-->
            </div>
        </div>
        
        <section class="main">
<!--             글 목록 출력              -->
            <div class="grid-view-contents">
                <div class="grid-view-content">
                    <img src="https://t1.daumcdn.net/cfile/tistory/99D20C355C94394109"
                    	onclick="location.href='/writing/1'"
                        alt="book image">
                    <button type="button" class="like-button" id="good"></button> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
                    <div class="book-content">
                    	<ul id="book"></ul>
                        <h1 id="booktitle"></h1>
                        <h3 id="author"></h3>
                        <h3 id="regDate"></h3>
                        <p id="content"></p>
                    </div>
                    <div class="content-info">
                        <div class="profile-img"><!--작성자 옆 프로필사진-->
                            <img src="#" alt="profile image">
                        </div>              
                        <strong id="nickname"></strong>
                        <strong id="regDate"></strong><!--작성일-->              
                    </div>
                </div>
                <!--이하 반복-->
            </div>
        </section>
    </div>


    <script src=""></script>
</body>

</html>