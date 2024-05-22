<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 리스트</title>
<link rel="stylesheet" type="text/css" href="css/employee.css">
</head>
<body>
<div id="wrap">
	<h1>리스트</h1>
	<table class="list">
		<tr>
			<td colspan="5" class="td1">
			<!-- EmployeeServlet로의 요청을 보내는데, 이 때 command=employee_write_form 파라미터를 함께 전달 -->
			<!-- command는 해당 서블릿에서 요청을 어떻게 처리할지를 결정하는 매개변수. 해당 a태그는 GET 요청 방식이다. -->
				<a href="EmployeeServlet?command=employee_write_form">정보등록</a>
			</td>
		</tr>
		
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>권한</th>
			<th>성별</th>
			<th>전화번호</th>			
		</tr>
		
		<!-- employeeList에 저장된 항목을 반복하면서 각 항목을 employee라는 변수에 할당하여 처리하는 것을 의미 -->
		<c:forEach var="employee" items="${employeeList }">
			<tr>
				<td>${employee.id }</td>
				<td> <a href="EmployeeServlet?command=employee_view&id=${employee.id }">${employee.name }</a></td>
				<td>
				<!-- 권한이 A이면 운영자, B이면 일반 회원으로 표시하기 -->
					<c:choose>
						<c:when test="${employee.lev eq 'A' }">운영자</c:when>
						<c:when test="${employee.lev eq 'B' }">일반 회원</c:when>
					</c:choose>
				</td>
				<td>
				<!-- 성별이 1이면 남자, 2이면 여자로 표시하기 -->
					<c:choose>
						<c:when test="${employee.gender eq '1'}">남자</c:when>
						<c:when test="${employee.gender eq '2'}">여자</c:when>
					</c:choose>
				</td>
				<td>${employee.phone }</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>