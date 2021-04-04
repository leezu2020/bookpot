<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bookpot Detail Page</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/detail.css" />" >
</head>
<body>
    <div class="page-head">
        <div class="logo-image-text">
            <img src="<c:url value="/resources/icon/logo.svg" />" alt="logo image">
            <a href="/">
           	 <img src="<c:url value="/resources/icon/책단지.svg" />" alt="logo text">
            </a>
        </div>

        <form action="" method="GET">     
            <button type="submit">임시저장</button>
            <button type="submit">발행</button>
        </form>
    </div>
    <div class="book-info">
        <div>
            <strong>서명</strong>
            <h2>엔지니어를 위한 인문학 수업</h2>
        </div>
        <div>
            <div>
                <strong>분야</strong>
                <span>국내서적</span>
                <span>프로그래밍</span>
            </div>
            <div>
                <strong>저자</strong>
                <h3>새뮤얼 플러먼</h3>
            </div>
            <div>
                <strong>출판사</strong>
                <h3>작은숲</h3>
            </div>
        </div>
    </div>

    <div class="content">
        <h1>인문학을 아는 엔지니어가 되어야 한다</h1>
        <div class="content-info">
            <img src="#" alt="profile-img">
            <h3>Yunji jeong</h3>
            <h4>2021.02.23</h4>
            <button type="button">
                <img src="<c:url value="/resources/icon/star_black.svg" />" alt="bookmark">
            </button>
            <span>10</span>
            <button type="button">
                <img src="<c:url value="/resources/icon/like_green.svg" />" alt="like">     
            </button>   
            <span>256</span>
        </div>
        <span>#인문학 #엔지니어 #프로그래밍</span>
        <p>
            바야흐로 융합의 시대다. 공학과 인문학을 융합하는 교육은 엔지니어의 시야를 넓히고, 문제 해결의 새로운 실마리를 제공해 줄 것이다. 공학 교육은 산업계와..
        </p>
    </div>
    <div class="comments">
        <h3>3개의 댓글</h3>
        <form action="" method="GET">
            <input type="text" placeholder="댓글을 남겨주세요"/>
            <button type="submit">댓글달기</button>
        </form>

        <div class="">
            <img src="#" alt="profile-img">
            <h3>Yujeong Kim</h3>
            <h4>2021.02.24</h4>
            <p>너무 좋은 글이네요! 덕분에 많이 배워갑니다. 감사합니다~!</p>
        </div>
        <div class="div">
            <img src="#" alt="profile-img">
            <h3>Seunghyun Oh</h3>
            <h4>2021.02.24</h4>
            <p>글 내용에 깊이 공감하고 갑니다. 자주 올려주세요~^^</p>
        </div>
        <div class="div">
            <img src="#" alt="profile-img">
            <h3>Soyoung Choi</h3>
            <h4>2021.02.24</h4>
            <p>메인에 노출되셨네요. 축하드립니다~</p>
        </div>
    </div>

</body>
</html> 