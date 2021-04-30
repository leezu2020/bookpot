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
<!-- 글 검색 목록 가져오기 -->
	$(document).ready(function(){
		$("#searching").keyup(function(){
			$("#srch-result").empty();
			var keyword = $("#searching").val();
			if(keyword != ''){
				$.ajax({
					url : "/writings/title/" + keyword,
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
<!-- 첫 화면 글 가져오기 -->
	(function(){
		$.ajax({
			url: "/writings/search?keyword=&division=&categories=&sort=",
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
	
<!-- 좋아요 기능 -->
	function goodup(){
		$.ajax({
			url: "/writings/good/3",
			type: "post",
			dataType: "json",
			success: function(response){
				console.log("좋아요가 정상 처리되었습니다. " + response.message);
			},
			error : function(xhr, status, error){
				var msg = xhr.responseText;
				var parseMsg = JSON.parse(msg);
				if(xhr.status == 400){
					console.log("이미 좋아요를 누른 상태입니다.");
					console.log(parseMsg.message);
				} else if(xhr.status == 503){
					console.log("좋아요 서비스를 사용할 수 없습니다.");
					console.log(parseMsg.message);
				} else {
					console.log(xhr.status + " 의 에러입니다.");
				}
			}
		})
	}
	
	function gooddown(){
		$.ajax({
			url: "/writings/good/3",
			type: "delete",
			dataType: "json",
			success: function(response){
				console.log("좋아요가 정상 취소되었습니다. " + response.message);
			},
			error: function(xhr, status, error){
				var msg = xhr.responseText;
				var parseMsg = JSON.parse(msg);
				if(xhr.status == 400){
					console.log("이미 좋아요가 취소된 상태입니다.");
					console.log(parseMsg.message);
				} else if(xhr.status == 503){
					console.log("좋아요 서비스를 사용할 수 없습니다.");
					console.log(parseMsg.message);
				} else {
					console.log(xhr.status + " 의 에러입니다.");
				}
			}
		})
	}
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
                
				<div id="login-form-container">
					<form class="login-form">
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
						<input id="login-form-submit" type="button" value="로그인">
						<div id="login-form-error-message">가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.</div>
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
					<a href="/users">
						<button type="button" class="login">회원정보</button>
					</a>
					<button class="logout" onclick="location.href='/logout'">로그아웃</button>
					<button id="write-button" onclick="location.href='/writings/reg'">글쓰기</button>
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
 				<button class="field" type="button" onclick="bookTypeSelected(0)">예술</button>
                <button class="field" type="button" onclick="bookTypeSelected(1)">소설</button>
                <button class="field" type="button" onclick="bookTypeSelected(2)">역사/문화</button>
                <button class="field" type="button" onclick="bookTypeSelected(3)">인문</button>
                <button class="field" type="button" onclick="bookTypeSelected(4)">비문학</button>
                <button class="field" type="button" onclick="bookTypeSelected(5)">경제/경영</button>
                <button class="field" type="button" onclick="bookTypeSelected(6)">정치</button>
                <button class="field" type="button" onclick="bookTypeSelected(7)">영어</button>
                <button class="field" type="button" onclick="bookTypeSelected(8)">과학</button>
                <button class="field" type="button" onclick="bookTypeSelected(9)">여성학</button>
                <button class="field" type="button" onclick="bookTypeSelected(10)">고전</button> 
                <button class="field" type="button" onclick="bookTypeSelected(11)">자기계발</button> 
                <button class="field" type="button" onclick="bookTypeSelected(12)">취미/실용</button> 
                <button class="field" type="button" onclick="bookTypeSelected(13)">컴퓨터</button> 
                <button class="field" type="button" onclick="bookTypeSelected(14)">어린이</button> 
                <!--더 적어야함-->
            </div>
            <div class="category-action">
                <button id="filter-search" type="button">찾기</button>
                <button id="initialize" type="reset">초기화</button> <!--위에서 누른것들 초기화되도록-->
            </div>
        </div>
        
        <!--인기글, 최신순 탭 + 그리드&리스트 뷰-->
        <div id="array">
            <div class="content-array">
                <div class="best array-selected">인기</div>
                <div class="latest">최신</div>
            </div>
            <div class="view-type">
                <button id="list-view-selected" class="view-type-select"><img src="/resources/icon/list_green.svg" id="list-view-icon"></button>
                <button id="grid-view-selected" class="view-type-select"><img src="/resources/icon/grid_black.svg" id="grid-view-icon"></button>
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
                        <span class="like-number" onclick="gooddown()">256</span> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
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
                        <span class="like-number" onclick="goodup()">256</span> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
                    </div>
             <!--  xpppppppppppppppppppppppppppppppppppp스트 -->
                    <div class="grid-book-info" onclick="location.href='/writings/3'">
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
                    <div class="content-number">번호</div>
					<div class="classification-content-title">제목</div>
					<div class="classification-book-title">책 제목</div>
					<div class="classification-profile-nickname">작성자</div>
                    <div class="classification-register-date">작성일</div>
                </div>
                <div class="list-view-content">
                    <div class="list-content-number">1</div>
                    <div class="list-content-title">엔지니어도 인문학이 필요할까?</div>
                    <div class="list-book-title">엔지니어를 위한 인문학 수업</div>
                    <div class="list-profile-nickname">Yunji Jeong</div>
                    <div class="list-register-date">2021. 02. 02</div>
                </div>
                <div class="list-view-content">
                    <div class="list-content-title">행복은 제 발로 걸어오지 않아. 그러니 내 발로 찾아가야지</div>
                    <div class="list-book-title">또다시 같은 꿈을 꾸었어</div>
                    <div class="list-profile-nickname">yujung7768903</div>
                    <div class="list-register-date">2021. 03. 22</div>
                </div>
                <div class="list-view-content">
                    <div class="list-content-title"></div>
                    <div class="list-book-title"></div>
                    <div class="list-profile-nickname"></div>
                    <div class="list-register-date"></div>
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
			</div>
		</section>
    </div>
</body>

</html>