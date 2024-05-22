<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 보기</title>
<link rel="stylesheet" href="css/employee.css">
<script type="text/javascript" src="script/employee.js"></script>
</head>
<body>
<div id="wrap">
<h1>정보 보기</h1>
<form name="frm" method="post" action="EmployeeServlet">
	<table>
		<tr>
			<th>아이디</th>
			<!-- employee 유무 차이 : 있으면 employee 속성의 전체 경로를 지정해줌. 없다면 현재 페이지에서 id 속성을 찾아서 지정. -->
			<td>${employee.id }</td>
		</tr>
		
		<tr>
			<th>비밀번호</th>
			<td>${employee.pass }</td>
		</tr>
		
		<tr>
			<th>이름</th>
			<td>${employee.name }</td>
		</tr>
		
		<tr>
			<th>권한</th>
			<td>
				<c:choose>
					<c:when test="${employee.lev eq 'A' }">운영자</c:when>
					<c:when test="${employee.lev eq 'B' }">일반 회원</c:when>
				</c:choose>
			</td>
		</tr>
		
		<tr>
			<th>성별</th>
			<td>
				<c:choose>
					<c:when test="${employee.gender eq '1'}">남자</c:when>
					<c:when test="${employee.gender eq '2'}">여자</c:when>
				</c:choose>
			</td>
		</tr>
		
		<tr>
			<th>전화번호</th>
			<td>${employee.phone }</td>
		</tr>
		
		<tr>
			<th>가입일</th>
			<!-- 00시 00분 00.0초로 표현하려면 ss뒤에 .S까지 붙임. 여기서 S는 소수점 이하의 초를 나타냄 -->
			<td><fmt:formatDate value="${employee.enter }" pattern="yy-MM-dd HH:mm:ss"/></td>
		</tr>
	</table>
	
	<br>
	
	<!-- 직원 정보를 수정하기 위한 폼을 보여주는 작업
		 id=${param.id }가 없으면 EmployeeUpdateFormAction에서 id를 불러 올 수 없으며, null을 가지게 된다. -->
	<input type="button" value="수정" onclick="location.href='EmployeeServlet?command=employee_update_form&id=${param.id}'">
	<!-- command는 employee_delete로 설정되고, id는 ${param.id}로 설정.
		 ${param.id}는 서버에서 파라미터로 전달된 id 값을 사용.
		 버튼을 클릭하면 EmployeeServlet에 employee_delete 명령과 해당하는 ID를 전달하여 삭제 작업을 수행. -->
	<input type="button" value="삭제" onclick="location.href='EmployeeServlet?command=employee_delete&id=${param.id}'">
	<input type="button" value="목록" onclick="location.href='EmployeeServlet?command=employee_list'">
	<input type="button" value="등록" onclick="location.href='EmployeeServlet?command=employee_write_form'">
</form>
</div>
</body>
</html>