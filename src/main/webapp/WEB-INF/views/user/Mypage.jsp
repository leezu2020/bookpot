<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html lang="ko"><head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>책단지: 마이페이지</title>
    <link rel="stylesheet" href="/resources/css/Mypage CSS.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
    <script src="/resources/js/Mypage JS.js"></script>
    

</head>

<!-- 중복확인 처리를 위한 script -->


<!--css 작업 후 이미지 파일 앞에 /resources 다시 붙이기 -->


<body>
<!-- 로그인한 사용자 -->
<sec:authorize access="isAuthenticated()">
	<!-- 로그인 정보 -->
	<sec:authentication var="userName" property="principal.username" />
	<sec:authentication var="userEmail" property="principal.name" />

        <div class="header">
            <div id="logo">
                <!-- 책 아이콘 -->
                <img src="/resources/icon/logo.svg" alt="logo-icon"> <!--로고 이미지 작업 필요-->
                <!--책단지 아이콘-->
                <a href="/">
                	<img class="site-name" src="/resources/icon/책단지.svg" alt="책단지-icon" >
                </a>
            </div>
            <nav><!--write버튼, 회원가입버튼, login버튼 묶음-->
                <button type="button" class="login">로그인</button>
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
                <button type="sign-up" class="sign-up">회원가입</button>
                <!--회원가입 페이지 이동-->
                <button type="write" id="write-button">글쓰기</button>
                <!--글쓰기 페이지 이동-->
            </nav>
        </div>



<div class="side-container">
        <!--side-->  
        <div class="side">

            <!--프로필 이미지 수정-->
            <div class="img-container">
                <!--기본 프로필 이미지-->
                <div id="profile-img-box"> 
                    <img id="profile-img" src="/resources/icon/profile.png">
                </div>


                <!--이미지 수정 버튼(파일 업로드)-->
                <div class="img-change">              
                    <!--이미지 수정 버튼 라벨-->
                    <label for="input-img">이미지 수정</label>
                    <!--이미지 수정용 input인데 숨겨서 안보이게 하고 대신 위 라벨 눌러서 사용하도록 함-->
                    <input id="input-img" class="hidden-button" type="file">
                </div>

                 <!--아이콘들-->
                 <div id="mypage-icon">

                    <!--내가 쓴 글 아이콘-->
                    <div id="what-i-wrote">
                       <a href="/users/writings">
                           <img id="pencil-img" src="/resources/icon/pencil.svg" alt="pencil-icon"><br>
                           <p id="pencil-letter">내가 쓴 글</p>
                       </a>
                   </div>
   
                   <!--스크랩 아이콘-->
                   <div id="scrap">
                       <a href="/users/scraps">
                           <img id="scrap-img" src="/resources/icon/scrap.svg" alt="scrap-icon"><br>
                           <p id="scrap-letter">스크랩</p>
                       </a>
                   </div>
   
                   <!--댓글 쓴 글 아이콘-->
                   <div id="comments">
                       <a href="/users/comments">
                           <img id="comments-img" src="/resources/icon/comments.svg" alt="comments-icon"><br>
                           <p id="comments-letter">댓글</p>
                       </a>
                   </div>

                </div>
            </div>
        </div>
            


        <!--오른쪽 내 정보 수정 부분-->
        <div class="main">
                <h2>내 정보</h2>
                <div id="green-box"></div>

                <!--닉네임-->
                <p>
                    <label for="nickname">닉네임</label><br>
                    <!--회원정보 수정시 input 활성화-->
                    <input id="nickname-info" type="text" value="${userName}" >
                    <input type="button" id="nickname-button" value="중복확인" onClick="fn_checkNickname()">
                </p><div class="alert-nickname" id="nickname-check"></div>
                <p>
                </p>

                <!--이메일-->
                <p>
                    <label for="email">이메일</label><br>
                    </p><input id="email-info" type="text" value="${userEmail}" disabled>
                <p></p>

                <!--비밀번호 수정-->
                
                <div id="password-set">
                    <p>
                        <label for="password">비밀번호</label><br>
                        <input id="password" type="password" placeholder="8~16자 영문 대 소문자, 숫자를 사용하세요.">
                        </p><div class="alert-pwd" id="pwd-check"></div>
                    <p></p>
                </div>

                <!--비밀번호 수정 확인-->
                <div id="password-2-set">
                    <p>
                        <label for="password-2">비밀번호 확인</label><br>
                        <input id="password-2" type="password" placeholder="비밀번호가 일치하지 않습니다.">
                        </p><div class="alert-pwd2" id="pwd2-check"></div>
                    <p></p>
                </div>

                
                <!--회원정보 수정 버튼-->
                <button type="button" class="changeButton" id="change-button">회원정보 수정</button>

                <!--적용&취소버튼-->
                <div class="upload-buttons">
                    <button type="button" id="update-button" onclick="updateInfo()">적용</button>
                    <button type="button" id="cancel-button">취소</button>
                </div>

                <!--비밀번호 재확인-->
                <div class="pwd-forchange-set" id="pwd-set">
                    <p>
                        <label for="pwd-forchange">비밀번호 재확인</label><br>
                        <input id="pwd-forchange" type="password" placeholder="회원정보를 안전하게 보호하기 위해 비밀번호를 한번 더 확인해 주세요.">
                        <button type="button" id="pwd-button">확인</button>
                    </p><div class="alertpwd-forchange" id="alertpwd-forcheck"></div>

                </div>


                <div id="grey-line"></div>


                <!--로그아웃 버튼-->

                <p>
                    <button type="button" id="logout-button" onclick="location.href='/logout'">로그아웃</button>
                </p>

                <!--회원탈퇴-->
                <p>
                    <button type="button" id="withdrawal-button" onclick="deleteInfo()">회원탈퇴</button>
                </p>
                


        </div>
    </div>
</sec:authorize>

</body></html>