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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/resources/css/Home CSS.css">
    <script defer src="/resources/js/Home JS.js"></script>
</head>

<script type="text/javascript">
	$(document).ready(function(){
		$("#searching").keyup(function(){
			$("#srch-result").empty();
			var keyword = $("#searching").val();
			if(keyword != ''){
				$.ajax({
					url : "/writing/title/" + keyword,
					type : "get",
					success : function(data){
						
						for(var i=0; i<data.length; i++){
							$("#srch-result").append("<li class='srchList' value=" + data[i] +">" + data[i] + "</li>");
							console.log(data[i]);
						}
					},
					error: function(e){
						console.log("DB를 가져오지 못했습니다.");
					}
				});
			} else {
				$("#srch-result").empty();
			}
		});
	});
	// 일회성 함수
	(function(){
		$.ajax({
			url: "/writing/search",
			type: "get",
			dataType: "json",
			success: function(data){
				console.log(data);
			},
			error: function(e){
				console.log("글 목록을 가져오지 못했습니다.");
			}
		})
	})();
</script>

<body>
    <div class="container">
        <header>
            <div class="logo">
                <!-- 책 아이콘 -->
                <img src="/resources/icon/logo.svg" alt="logo-icon"> <!--로고 이미지 작업 필요-->
                <!--책단지 아이콘-->
                <!--책단지 아이콘 클릭시 메인화면 이동-->
                <a href="/">
                <img class="site-name" src="/resources/icon/책단지.svg" alt="책단지-icon">
                </a>
            </div>

            <nav>
           	 	<!-- 비로그인시 -->
            	<sec:authorize access="!isAuthenticated()">
            	<!--write버튼, 회원가입버튼, login버튼 묶음-->
                <button type="button" class="login">로그인</button>
                <button type="button" class="sign-up" onclick="location.href='/join/signup'">회원가입</button>
                </sec:authorize>
                
                <!--회원가입 페이지 이동-->
					<div id="login-form-container">
						<form class="login-form" action="/login" method="post">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<button class="login-form-hide" type="button">X</button>
							<div class="logo">
								<!-- 책 아이콘 -->
								<img src="/resources/icon/logo.svg" alt="logo-icon">
								<!--로고 이미지 작업 필요-->
								<!--책단지 아이콘-->
								<img class="site-name" src="/resources/icon/책단지.svg" alt="책단지-icon">
							</div>
							<label for="login-email">이메일</label>
							<input type="email" id="login-email" name="username" placeholder="이메일을 입력하세요">
							<label for="login-password">비밀번호</label>
							<input type="password" id="login-password" name="password" placeholder="비밀번호를 입력하세요">
							<button id="login-form-submit" type="submit">로그인</button>
							<c:if test="${not empty errorMessage}">
								<p>${errorMessage}</p>
							</c:if>
						</form>
					</div>
					<!-- 아이디,비밀번호 dropdown 입력창 묶음 -->
					<!--로그인 버튼과 눌렀을때 dropdown되는 입력창들 묶음-->
				

				<!-- 로그인시 (class와 onclick 링크 수정 필요-->
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal.username" var="nickname" />
					<sec:authentication property="principal.no" var="userNo" />
					<button type="button">${nickname}님환영합니다</button>
					<!-- 본인 이름 클릭했을 때, 정보조회 링크 -->
					<a href="/users/${userNo}">
						<button type="button" class="login">회원정보</button>
					</a>
					<button class="logout" onclick="location.href='/logout'">로그아웃</button>
					<button id="write-button" onclick="location.href='/writing/reg'">글쓰기</button>
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
                <input type="text" id="searching" placeholder="찾는 책의 제목/키워드를 입력하세요">
                <button type="reset">X</button><!--쓴것 초기화 button-->
            </div>
       	 		<ul id="srch-result"></ul>
            <!--버튼 누르면 색 변하도록 js작업하기-->
            <div id="filter-menu">
                <img src="/resources/icon/filter.svg">
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
                <button id="list-view-selected" class="view-type-select"><img src="/resources/icon/list_black.svg"></button>
                <button id="grid-view-selected" class="view-type-select"><img src="/resources/icon/grid_black.svg"></button>
                <!--따로 버튼 없이 선택하자마자 적용되는 방법은?-->
            </div>
        </div>
        
        <section class="main">
            <div id="grid-view">
                <div class="grid-view-content">      
                    <div class="grid-view-content-img">
                        <img src="https://t1.daumcdn.net/cfile/tistory/99D20C355C94394109"
                            alt="book image">
                    </div>
                    <div class="grid-view-content-like">
                        <img src="/resources/icon/like_white.svg">
                        <span class="like-number">256</span> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
                    </div>
                    <div class="grid-book-info">
                        <h1 class="grid-content-title">엔지니어도 인문학이 필요할까?</h1>
                        <h3 class="grid-book-title">엔지니어를 위한 인문학 수업</h3>
                        <p class="summary">
                            바야흐로 융합의 시대다. 공학과 인문학을 융합하는 교육은 엔지니어의 시야를 넓히고, 문제 해결의 새로운 실마리를 제공해 줄 것이다. 공학교 교육은..
                        </p>
                    </div>
                    <div class="write-info">
                        <div class="register-profile"><!--작성자 옆 프로필사진-->
                            <img src="#">
                            <span class="profile-nickname">Yunji Jeong</span>
                        </div>  
                        <span class="register-date">2020.02.14</span><!--작성일-->    
                    </div>                   
                </div>
                <!--이하 반복-->
                <div class="grid-view-content">      
                    <div class="grid-view-content-img">
                        <img src="https://t1.daumcdn.net/cfile/tistory/99D20C355C94394109"
                            alt="book image">
                    </div>
                    <div class="grid-view-content-like">
                        <img src="/resources/icon/like_white.svg">
                        <span class="like-number">256</span> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
                    </div>
                    <div class="grid-book-info">
                        <h1 class="grid-content-title">엔지니어도 인문학이 필요할까?</h1>
                        <h3 class="grid-book-title">엔지니어를 위한 인문학 수업</h3>
                        <p class="summary">
                            바야흐로 융합의 시대다. 공학과 인문학을 융합하는 교육은 엔지니어의 시야를 넓히고, 문제 해결의 새로운 실마리를 제공해 줄 것이다. 공학교 교육은..
                        </p>
                    </div>
                    <div class="write-info">
                        <div class="register-profile"><!--작성자 옆 프로필사진-->
                            <img src="#">
                            <span class="profile-nickname">Yunji Jeong</span>
                        </div>  
                        <span class="register-date">2020.02.14</span><!--작성일-->    
                    </div>                   
                </div>
                <!--이하 반복-->
                <div class="grid-view-content">      
                    <div class="grid-view-content-img">
                        <img src="https://t1.daumcdn.net/cfile/tistory/99D20C355C94394109"
                            alt="book image">
                    </div>
                    <div class="grid-view-content-like">
                        <img src="/resources/icon/like_white.svg">
                        <span class="like-number">256</span> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
                    </div>
                    <div class="grid-book-info">
                        <h1 class="grid-content-title">엔지니어도 인문학이 필요할까?</h1>
                        <h3 class="grid-book-title">엔지니어를 위한 인문학 수업</h3>
                        <p class="summary">
                            바야흐로 융합의 시대다. 공학과 인문학을 융합하는 교육은 엔지니어의 시야를 넓히고, 문제 해결의 새로운 실마리를 제공해 줄 것이다. 공학교 교육은..
                        </p>
                    </div>
                    <div class="write-info">
                        <div class="register-profile"><!--작성자 옆 프로필사진-->
                            <img src="#">
                            <span class="profile-nickname">Yunji Jeong</span>
                        </div>  
                        <span class="register-date">2020.02.14</span><!--작성일-->    
                    </div>                   
                </div>
                <!--이하 반복-->
                <div class="grid-view-content">      
                    <div class="grid-view-content-img">
                        <img src="https://t1.daumcdn.net/cfile/tistory/99D20C355C94394109"
                            alt="book image">
                    </div>
                    <div class="grid-view-content-like">
                        <img src="/resources/icon/like_white.svg">
                        <span class="like-number">256</span> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
                    </div>
                    <div class="grid-book-info">
                        <h1 class="grid-content-title">엔지니어도 인문학이 필요할까?</h1>
                        <h3 class="grid-book-title">엔지니어를 위한 인문학 수업</h3>
                        <p class="summary">
                            바야흐로 융합의 시대다. 공학과 인문학을 융합하는 교육은 엔지니어의 시야를 넓히고, 문제 해결의 새로운 실마리를 제공해 줄 것이다. 공학교 교육은..
                        </p>
                    </div>
                    <div class="write-info">
                        <div class="register-profile"><!--작성자 옆 프로필사진-->
                            <img src="#">
                            <span class="profile-nickname">Yunji Jeong</span>
                        </div>  
                        <span class="register-date">2020.02.14</span><!--작성일-->    
                    </div>                   
                </div>
                <!--이하 반복-->
            </div>
			<div id="list-view">
				<div class="classification">
					<div class="classification-book-type">분야</div>
					<div class="classification-content-title">제목</div>
					<div class="classification-book-title">책 제목</div>
					<div class="classification-profile-nickname">작성자</div>
				</div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
				<div class="list-view-content"></div>
			</div>
		</section>
    </div>
</body>

</html>