//정보 등록 페이지에서 확인 버튼 클릭 시 비밀번호가 적절한지 확인하는 함수
function employeeCheck() {
	//폼이 제출되기 전에 비밀번호의 길이를 확인하여 4글자 미만이면 알림을 표시하고 제출을 중지하는 역할
	if (document.frm.pass.value.length < 4) {
		alert("비밀번호는 4글자 이상입니다.")
		frm.pass.focus();
		
		return false;
	}
	
	return true;
}