<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">
<!-- 내가 쓴 글/스크랩/댓글 쓴 글 -->
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="_csrf" th:content="${_csrf.token}"/>
    <meta name="_csrf_header" th:content="${_csrf.headerName}"/>
    <title>책단지</title>
    <link rel="stylesheet" href="/resources/css/show CSS.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script defer src="/resources/js/Home JS.js"></script>
</head>

<body>
    <div class="container">
        <header>
            <div class="logo">
                <!-- 책 아이콘 -->
                <img src="/resources/icon/logo.svg" alt="logo-icon"> <!--로고 이미지 작업 필요-->
                <!--책단지 아이콘-->
                <a href="/">
                	<img class="site-name" src="/resources/icon/책단지.svg" alt="책단지-icon" >
                </a>
            </div>
            <nav><!--write버튼, 회원가입버튼, login버튼 묶음-->
                <button type="button" class="login">로그인</button>
                <button type="button" class="sign-up">회원가입</button>
                <!--회원가입 페이지 이동-->
                <button type="button" id="write-button">글쓰기</button>
                <!--글쓰기 페이지 이동-->
            </nav>
            <div id="login-form-container">
                <form class="login-form" action="" method="">
                    <button class="login-form-hide" type="button">X</button>
                    <div class="logo">
                        <!-- 책 아이콘 -->
                        <img src="/resources/icon/logo.svg" alt="logo-icon"> <!--로고 이미지 작업 필요-->
                        <!--책단지 아이콘-->
                        <img class="site-name" src="/resources/icon/책단지.svg" alt="책단지-icon">
                    </div>
                    <label for="login-email">이메일</label>
                    <input type="email" id="login-email" placeholder="이메일을 입력하세요">
                    <label for="login-password">비밀번호</label>
                    <input type="password" id="login-password" placeholder="비밀번호를 입력하세요">
                    <div id="login-form-error-message">가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.</div>
                    <button id="login-form-submit" type="submit">로그인</button>
                </form>
            </div> <!-- 아이디,비밀번호 dropdown 입력창 묶음 -->
            <!--로그인 버튼과 눌렀을때 dropdown되는 입력창들 묶음-->
        </header>
        
        <!--인기글, 최신순 탭 + 그리드&리스트 뷰-->
        <div class="view-type">
            <button id="list-view-selected" class="view-type-select"><img src="/resources/icon/list_green.svg" id="list-view-icon"></button>
            <button id="grid-view-selected" class="view-type-select"><img src="/resources/icon/grid_black.svg" id="grid-view-icon"></button>
            <!--따로 버튼 없이 선택하자마자 적용되는 방법은?-->
        </div>
        
        <section class="main">
            <div id="grid-view">
                <div class="grid-view-content">
                    <div class="grid-view-content-img">
                        <img src="/resources/icon/scrap_white.svg" class="scrap-icon">
                        <img src="https://t1.daumcdn.net/cfile/tistory/99D20C355C94394109" alt="book image">
                    </div>
                    <div class="grid-view-content-like">
                        <img src="/resources/icon/like_white.svg">
                        <span class="like-number">256</span> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
                    </div>
                    <div class="grid-book-info">
                        <h1 class="grid-content-title">엔지니어도 인문학이 필요할까?</h1>
                        <h3 class="grid-book-title">엔지니어를 위한 인문학 수업</h3>
                        <p class="summary">바야흐로 융합의 시대다. 공학과 인문학을 융합하는 교육은 엔지니어의 시야를 넓히고, 문제 해결의 새로운 실마리를 제공해 줄 것이다. 공학교 교육은..</p>
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
                        <img src="/resources/icon/scrap_white.svg" class="scrap-icon">
                        <img src="https://t1.daumcdn.net/cfile/tistory/99D20C355C94394109" alt="book image">
                    </div>
                    <div class="grid-view-content-like">
                        <img src="/resources/icon/like_white.svg">
                        <span class="like-number">256</span> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
                    </div>
                    <div class="grid-book-info">
                        <h1 class="grid-content-title">행복은 제 발로 걸어오지 않아. 그러니 내 발로 찾아가야지</h1>
                        <h3 class="grid-book-title">또다시 같은 꿈을 꾸었어</h3>
                        <p class="summary">바야흐로 융합의 시대다. 공학과 인문학을 융합하는 교육은 엔지니어의 시야를 넓히고, 문제 해결의 새로운 실마리를 제공해 줄 것이다. 공학교 교육은..</p>
                    </div>
                    <div class="write-info">
                        <div class="register-profile"><!--작성자 옆 프로필사진-->
                            <img src="#">
                            <span class="profile-nickname">Yunji Jeong</span>
                        </div>              
                        <span class="register-date">2020.02.14</span><!--작성일-->              
                    </div>
                </div>
    
                <div class="grid-view-content">
                    <div class="grid-view-content-img">
                        <img src="/resources/icon/scrap_white.svg" class="scrap-icon">
                        <img src="https://t1.daumcdn.net/cfile/tistory/99D20C355C94394109" alt="book image">
                    </div>
                    <div class="grid-view-content-like">
                        <img src="/resources/icon/like_white.svg">
                        <span class="like-number">256</span> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
                    </div>
                    <div class="grid-book-info">
                        <h1 class="grid-content-title">엔지니어도 인문학이 필요할까?</h1>
                        <h3 class="grid-book-title">엔지니어를 위한 인문학 수업</h3>
                        <p class="summary">바야흐로 융합의 시대다. 공학과 인문학을 융합하는 교육은 엔지니어의 시야를 넓히고, 문제 해결의 새로운 실마리를 제공해 줄 것이다. 공학교 교육은..</p>
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
                        <img src="/resources/icon/scrap_white.svg" class="scrap-icon">
                        <img src="https://t1.daumcdn.net/cfile/tistory/99D20C355C94394109" alt="book image">
                    </div>
                    <div class="grid-view-content-like">
                        <img src="/resources/icon/like_white.svg">
                        <span class="like-number">256</span> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
                    </div>
                    <div class="grid-book-info">
                        <h1 class="grid-content-title">엔지니어도 인문학이 필요할까?</h1>
                        <h3 class="grid-book-title">엔지니어를 위한 인문학 수업</h3>
                        <p class="summary">바야흐로 융합의 시대다. 공학과 인문학을 융합하는 교육은 엔지니어의 시야를 넓히고, 문제 해결의 새로운 실마리를 제공해 줄 것이다. 공학교 교육은..</p>
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
                        <img src="/resources/icon/scrap_white.svg" class="scrap-icon">
                        <img src="https://t1.daumcdn.net/cfile/tistory/99D20C355C94394109" alt="book image">
                    </div>
                    <div class="grid-view-content-like">
                        <img src="/resources/icon/like_white.svg">
                        <span class="like-number">256</span> <!--좋아요 버튼.(하트 이모티콘 넣어야함)--> 
                    </div>
                    <div class="grid-book-info">
                        <h1 class="grid-content-title">엔지니어도 인문학이 필요할까?</h1>
                        <h3 class="grid-book-title">엔지니어를 위한 인문학 수업</h3>
                        <p class="summary">바야흐로 융합의 시대다. 공학과 인문학을 융합하는 교육은 엔지니어의 시야를 넓히고, 문제 해결의 새로운 실마리를 제공해 줄 것이다. 공학교 교육은..</p>
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
                    <div class="list-content-number">1</div>
                    <div class="list-content-title">행복은 제 발로 걸어오지 않아. 그러니 내 발로 찾아가야지</div>
                    <div class="list-book-title">또다시 같은 꿈을 꾸었어</div>
                    <div class="list-profile-nickname">yujung7768903</div>
                    <div class="list-register-date">2021. 03. 22</div>
                </div>
                <div class="list-view-content">
                    <div class="list-content-number">1</div>
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
        <div class="page">
            <div class="page-number">1</div>
            <div class="page-number">2</div>
            <div class="page-number">3</div>
            <div class="page-number">4</div>
            <div class="page-number">5</div>
            <div class="page-number">6</div>
            <div class="page-number">7</div>
            <div class="page-number">8</div>
            <div class="page-number">9</div>
            <div class="page-number">10</div>
        </div>
    </div>
</body>

</html>