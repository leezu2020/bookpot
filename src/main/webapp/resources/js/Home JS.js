'use strict'
const body = document.getElementsByTagName("body");
const loginForm = document.getElementsByClassName("login-form");
const loginFormContainer = document.getElementById("login-form-container");
let gridContentSummary = document.getElementsByClassName("summary");
let bookTypeField = document.getElementsByClassName("field");
let viewType = document.getElementsByClassName("view-type");
let viewTypeSelect = document.getElementsByClassName("view-type-select");
let listViewSelected = document.getElementById("list-view-selected");
let gridViewSelected = document.getElementById("grid-view-selected");
let listView = document.getElementById("list-view");
let gridView = document.getElementById("grid-view");
let indexOfView
//view 인덱스 변수 선언하기

// 로그인 창 띄우고&닫기
$(document).ready(function() {
	
	$(".login").click(function() {
		$("body").toggleClass("login-form-show");
		$("#login-form-container").show();
		$(".login-form").show();
	})

	$(".login-form-hide").click(function() {
		$("body").removeClass("login-form-show");
		$("#login-form-container").hide();
		$(".login-form").hide();
	})
	
	$("#login-form-submit").click(function(){
		var token = $("meta[name='_csrf']").attr('content');
		var header = $("meta[name='_csrf_header']").attr('content');
		$.ajax({
			url:"/login",
			type: "post",
			dataType: "json",
			data : {
				username : $("#login-email").val(),
				password : $("#login-password").val()
				},
			beforeSend : function(xhr){
				if(token && header)
					xhr.setRequestHeader(header, token);
			},
			success : function(result){
				if(result.success){
					console.log("로그인 성공");
					window.location.href = result.returnUrl;
				} else{
					console.log("로그인 실패");
					console.log(result.message);
					$("#login-email").val('');
					$("#login-password").val('');
				}
			},
			error : function(a, b, c){
				console.log("로그인 에러 " + a + b + c);
			}
			
		})
	})
})

//분야 선택
function bookTypeSelected(iOfField) {
    console.log(bookTypeField[iOfField].style.backgroundColor);
    if (bookTypeField[iOfField].style.backgroundColor == "#FFFFFF") {
        console.log("하이라이트로 변경");
        bookTypeField[iOfField].style.backgroundColor = "#4FBA80";
        bookTypeField[iOfField].style.color = "#FFFFFF";
    }
    else{
        console.log("기본으로 변경");
        bookTypeField[iOfField].style.backgroundColor = "#FFFFFF";
        bookTypeField[iOfField].style.color = "#000000";
    }
}

// grid view content의 내용 요약
function contentSummaryPrint() {
	for (let i = 0; i < gridContentSummary.length; i++) {
		console.log(gridContentSummary[i].innerText);
		gridContentSummary[i].innerText = gridContentSummary[i].innerText.slice(0, 46) + "...";
	}
}
contentSummaryPrint();
// view 전환
listViewSelected.addEventListener("click", function() { listView.style.display = "flex"; gridView.style.display = "none" });
gridViewSelected.addEventListener("click", function() { listView.style.display = "none"; gridView.style.display = "flex" });