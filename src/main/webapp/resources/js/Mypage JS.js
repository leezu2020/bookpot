function readImage(input) {
    // 인풋 태그에 파일이 있는 경우
    if(input.files && input.files[0]) {
        // 이미지 파일인지 검사 (생략)
        // FileReader 인스턴스 생성
        const reader = new FileReader()
        // 이미지가 로드가 된 경우
        reader.onload = e => {
            const previewImage = document.getElementById("preview-image")
            previewImage.src = e.target.result
        }
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0])
    }
}
// input file에 change 이벤트 부여
const inputImage = document.getElementById("input-image")
inputImage.addEventListener("change", e => {
    readImage(e.target)
})

function fn_checkNickname(){
// 기존 닉네임일때에는 그대로 적용하도록 설정
	$.ajax({
		url : "/join/nickname/" + $('#nickname').val(),
		type : "get",
		success : function(result){
			if(result == 'exist'){
				alert('사용중인 닉네임입니다.');
				$('#nickname').focus();
				$('#nickname').val('');
			} else if(result == 'notmatch'){
				alert('닉네임을 확인해주세요.');
				$('#nickname').focus();
				$('#nickname').val('');
			} else {
				alert('사용가능한 닉네임입니다.');
			}
		},
		error: function(e){
			alert("닉네임 값을 가져오지 못했습니다.");
		}
	});
};