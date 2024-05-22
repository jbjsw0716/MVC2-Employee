<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC2를 이용한 게시판2</title>
</head>
<body>
<!-- Java 코드를 사용하여 HTTP 응답을 생성하는 부분
	 sendRedirect() 메서드를 사용하여 사용자를 다른 페이지로 리다이렉트함
	 리다이렉트란 클라이언트(웹 브라우저)의 요청을 받은 서버가 클라이언트에게 다른 URL로 이동하라는 지시를 내려, 클라이언트가 새로운 URL로 요청을 다시 보내도록 하는 것 -->
<% response.sendRedirect("http://localhost:8989/employees/EmployeeServlet?command=employee_list"); %>
</body>
</html>