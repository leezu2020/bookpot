<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>책단지: 회원가입 완료</title>
    <link rel="stylesheet" href="/resources/css/Sign-up complete CSS.css">
</head>

<body>
    <div class="container">
        <div class="header">
            <div id="logo">
                <!-- 책 아이콘 -->
                <img src="/resources/icon/logo.svg" alt="logo-icon"> <!--로고 이미지 작업 필요-->
                <!--책단지 아이콘-->
                <img class="site-name" src="/resources/icon/책단지.svg" alt="책단지-icon">
            </div>
            
        </div>

        <div class="main">

            <!--로고 이미지-->
            <img id="main-logo" src="/resources/icon/logo.svg" alt="logo-icon">

            <!--회원가입 완료 인사말-->
            <div id="welcome"> " 책단지에 어서오세요! :D "</div>
            <div id="celebrate">
                회원가입을 축하합니다.<br>
                로그인 후 책단지의 모든 서비스를 이용하실 수 있습니다.<br>
            </div>
        
            
            
            <!--버튼 위 회색 라인 모양-->
            <div id="grey-line"></div>
            <div class="buttons">
                <!--홈으로 이동 버튼-->
                <button id="home-page" onClick="location.href='/'">홈으로</button>
            </div>
           

        </div>
        
   
       
        
     
    </div>

</body>

</html>