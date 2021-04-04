<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bookpot Write Page</title>
    <link rel="stylesheet" href="/resources/css/write.css">
</head>

<body>
    <div class="header">
        <a href="/">
            <!--로고 이미지 눌렀을때 메인 페이지로 이동-->
            <button type="button">
                <img src="/resources/icon/logo.svg" alt="logo-img">
                <img src="/resources/icon/책단지.svg" alt="logo-text">
            </button>
        </a>
        <div class="header-right">
            <button type="button">Logout</button>
            <button type="button">
                <img src="/resources/icon/profile.png" alt="profile-img">
                <!--임시로 아무 사진이나 일단 넣었습니다-->
            </button>
        </div>
    </div>

    <div class="container">
        <!--디자인상 원래 임시저장/발행 버튼이 위에 위치해 있는데, 그 자리에 로그아웃/원형 프로필 이미지를 넣고
            대신 임시저장/발행 버튼을 작성하는 부분 아랫쪽에 두는게 어떨까 해서 우선 이렇게 바꿔보았습나다.-->
        <div>
            <div class="book-name">
                <label for="book-name" class="name">서명 :</label>
                <input type="text" class="name-area" placeholder="검색어를 입력하세요">
                <img src="/resources/icon/search.svg" class="search-img">
            </div>

            <div class="book-info">
                <label for="book-writer">저자 :</label>
                <input type="text" id="book-writer">

                <label for="book-publish">출판사 :</label>
                <input type="text" id="book-publish">
            </div>
            <label for="book-type" class="label">분야 :</label>
            <!--원래 input식이었는데 선택하도록 바꿔야할듯-->      

        </div>
        <div class="content">
            <input type="text" placeholder="제목을 입력하세요">
            <textarea placeholder="# 태그를 입력하세요 (최대 10개)" id="tag" onkeydown="enterTag()"></textarea>
        
            <!--<div class="plus-button-2">-->
            <div class="plus-1">
                <button type="button" id="plus-button-1" onclick="doDisplay1()">
                    <img src="/resources/icon/plus.svg" alt="icon">
                </button>
                <h4>읽은 기간</h4>       
            </div>
            <div class="period">
                <label for="first-date">시작일: </label>
                <form>     
                    <input type="text" placeholder="YYMMDD" id="first-date"> 
                </form>
                <span>~</span>
                <label for="last-date">종료일: </label>
                <form>
                    <input type="text" placeholder="YYMMDD" id="last-date">
                </form>
            </div>
            <div class="plus-2">
                <button type="button" id="plus-button-2" onclick="doDisplay2()">
                    <img src="/resources/icon/plus.svg" alt="icon">
                </button>
                <h4>인상깊은 구절</h4>
            </div>
            <form class="highlight">     
                <textarea placeholder="책을 읽으며 감명받았던 순간을 기록해보세요"></textarea>  
            </form>
            <textarea placeholder="감상을 자유롭게 적어보세요" class="text-area"></textarea>
        </div>

        <form class="write-buttons">
            <button type="submit" id="save-button">임시저장</button>
            <button type="submit" id="submit-button">발행</button>
        </form>
    </div>
    <script src="/resources/js/write.js"></script>
</body>

</html>