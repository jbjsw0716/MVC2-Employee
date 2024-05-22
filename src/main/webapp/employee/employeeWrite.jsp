<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 등록</title>
<link rel="stylesheet" href="css/employee.css">
<script type="text/javascript" src="script/employee.js"></script>
</head>
<body>
<div id="wrap">
<h1>정보 등록</h1>
<form name="frm" method="post" action="EmployeeServlet">
	<!-- 사용자에게 보이지 않지만, 폼을 제출할 때 함께 서버로 전송. ActionFactory에서 사용하기 위한 것으로 아주 중요하게 활용됨.
		 서버는 해당 요청이 어떤 작업을 수행해야 하는지 식별 가능. 일반적으로 이러한 작업 식별을 위해 숨겨진 입력 필드를 사용.
		 클라이언트와 서버 간의 통신을 보다 효율적으로 관리 가능. -->
	<input type="hidden" name="command" value="employee_write">
	<table>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="id" placeholder="아이디를 입력하세요" required> * 필수(등록 후 수정 불가)</td>
		</tr>
		
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="pass" placeholder="비밀번호를 입력하세요" required> * 필수(수정 시 필요 / 4글자 이상)</td>
		</tr>
		
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" placeholder="이름을 입력하세요" required> * 필수(등록 후 수정 불가)</td>
		</tr>
		
		<tr>
			<th>권한</th>
            <td>
            <!-- 드롭다운 목록을 사용하여 권한을 선택할 수 있는 부분 -->
                <select name="lev">
                    <option value="A">운영자</option>
                    <option value="B">일반 회원</option>
                </select>
                 * 해당되는 권한을 선택하세요
            </td>
		</tr>
		
		<tr>
			<th>성별</th>
			<td>
				<select name="gender">
                    <option value="1">남자</option>
                    <option value="2">여자</option>
                </select>
                 * 성별을 선택하세요
			</td>
		</tr>
		
		<tr>
			<th>전화번호</th>
			<td><input type="tel" name="phone" placeholder="전화번호를 입력하세요"> * 생략 가능</td>
		</tr>
	</table>
	
	<br>
	
	<div class="button">
		<!-- 확인 버튼 클릭 시 자바스크립트의 employeeCheck() 함수를 통해 비밀번호 확인하고 직원 리스트에 등록 -->
		<input type="submit" value="확인" onclick="return employeeCheck()">
		<input type="reset" value="취소">
		<!-- 목록 버튼 클릭 시 하던 작업 멈추고 모든 직원 리스트 페이지로 이동 -->
		<!-- location.href는 JavaScript에서 현재 페이지의 URL을 나타내는 속성.
			 이 속성을 사용하면 현재 페이지의 URL을 변경하고 새로운 페이지로 이동. 즉, 직접 자바스크립트에서 함수를 만들 필요x-->
		<input type="button" value="목록" onclick="location.href='EmployeeServlet?command=employee_list'">
	</div>
</form>
</div>
</body>
</html>