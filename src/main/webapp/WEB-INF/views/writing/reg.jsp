<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bookpot Write Page</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>

<script type="text/javascript">
	var page;
	$(document).ready(function(){
		searchbook();
	});
	function searchbook(){
		$("#book-name").keyup(function(){
			$("#srch-result").empty();
			var keyword = $("#book-name").val();
			if(keyword != ''){
				page = 1;
				$.ajax({
					url : "/writing/search/" + keyword +"/" + page,
					type : "get",
					dataType : "xml",
					success : function(data){
						var cnt = $(data).find('total').text();
						$("#cntAll").val(cnt);
						$(data).find('item').each(function(){
							var title = $(this).find("title").text();
							var author = $(this).find("author").text();
							var img = $(this).find("image").text();
							
							
							$("#srch-result").append("<li class='srchList' value=" + title + " 저자 : " + author
									+"> <img src='" + img + "' height='50'> </li>");
						});
					},
					error: function(e){
						alert("도서명을 가져오지 못했습니다.");
					}
				});
			} else {
				$("#srch-result").empty();
			}
		});
	}
	
	function prePage(){
		page--;
		$("#srch-result").empty();
		movepage();
	}
	function nextPage(){
		page++;
		$("#srch-result").empty();
		movepage();
	}
	
	function movepage(){
		var keyword = $("#book-name").val();
		$.ajax({
			url : "/writing/search/" + keyword +"/" + page,
			type : "get",
			dataType : "xml",
			success : function(data){
				var cnt = $(data).find('total').text();
				$("#cntAll").val(cnt);
				$(data).find('item').each(function(){
					var title = $(this).find("title").text();
					var author = $(this).find("author").text();
					var img = $(this).find("image").text();
					
					
					$("#srch-result").append("<li class='srchList' value=" + title + " 저자 : " + author
							+"> <img src='" + img + "'> </li>");
				});
			},
			error: function(e){
				alert("도서명을 가져오지 못했습니다.");
			}
		});
	}
</script>

<body>
    <div class="page-head">
        <a href="#"> <!--로고 이미지 눌렀을때 메인 페이지로 이동-->
            <button type="button">
                <img src="" alt="logo-img">
            </button>
        </a>     
        <button type="button">로그아웃</button>
        <button type="button">
            <img src="#" alt="profile-img">
        </button> 
    </div>
    <!--디자인상 원래 임시저장/발행 버튼이 위에 위치해 있는데, 그 자리에 로그아웃/원형 프로필 이미지를 넣고
        대신 임시저장/발행 버튼을 작성하는 부분 아랫쪽에 두는게 어떨까 해서 우선 이렇게 바꿔보았습나다.-->
    <div>
        <label for="book-name">도서명</label>
        <input type="text" id="book-name">

        <button type="button" onClick="findName()">검색하기</button>
        <input type="text" id="cntAll">
        <ul id="srch-result"></ul>
        <button type="button" onClick="prePage()">이전</button>
        <button type="button" onClick="nextPage()">다음</button>

        <label for="book-type">분야</label>
        <input type="text" id="book-type">

        <label for="book-writer">저자</label>
        <input type="text" id="book-writer">

        <label for="book-publish">출판사</label>
        <input type="text" id="book-publish">      
    </div>
    
    <input type="text" placeholder="제목을 입력하세요">
    <p><textarea placeholder="# 태그를 입력하세요 (최대 30개)"></textarea></p>
    <p><textarea placeholder="감상을 자유롭게 적어보세요"></textarea></p>

    <form>
        <button type="submit">임시저장</button>
        <button type="submit">발행</button>
    </form>

</body>
</html>