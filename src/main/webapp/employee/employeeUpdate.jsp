<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 수정</title>
<link rel="stylesheet" href="css/employee.css">
<script type="text/javascript" src="script/employee.js"></script>
</head>
<body>
<div id="wrap">
<h1>정보 수정</h1>
<form name="frm" method="post" action="EmployeeServlet">
	<input type="hidden" name="command" value="employee_update">
	<input type="hidden" name="id" value="${employee.id }">
	<table>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="id" value="${employee.id }" required readonly>
				 * 수정 불가</td>
		</tr>
		
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="pass" placeholder="비밀번호를 입력하세요" required>
				 * 필수(수정하려면 비밀번호를 입력하세요)</td>
		</tr>
		
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" value="${employee.name }" required readonly>
				 * 수정 불가</td>
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
			<td><input type="tel" name="phone" value="${employee.phone }"> * 수정할 전화번호를 입력하세요</td>
		</tr>
	</table>
	
	<br>
	
	<input type="submit" value="확인" onclick="return employeeCheck()">
	<input type="reset" value="취소">
	<input type="button" value="목록" onclick="location.href='EmployeeServlet?command=employee_list'">
</form>
</div>
</body>
</html>