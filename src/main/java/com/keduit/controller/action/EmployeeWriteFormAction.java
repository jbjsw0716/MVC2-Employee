package com.keduit.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//EmployeeWriteFormAction은 폼을 표시하는 역할을 하고, EmployeeWriteAction은 사용자가 입력한 데이터를 처리하는 역할//

//직원 정보를 입력하는 폼을 표시하는 액션 클래스
public class EmployeeWriteFormAction implements Action {
	//포워딩이란? 서버 측에서 요청을 다른 자원(페이지, 서블릿 등)으로 전달하는 기술이다.
	@Override
	//execute() 메서드는 사용자에게 직원 정보를 입력할 수 있는 폼을 표시하기 위해 employee/employeeWrite.jsp 페이지로 포워딩
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//포워딩할 페이지의 URL을 지정. 여기서는 직원 정보를 입력할 수 있는 폼을 표시하기 위해 employee/employeeWrite.jsp 페이지로 이동
		String url = "employee/employeeWrite.jsp";
		
		//요청을 처리하기 위해 지정된 URL에 대한 포워딩을 생성
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		//생성된 포워딩을 실행하여 요청을 employee/employeeWrite.jsp 페이지로 전달.
		dispatcher.forward(request, response);
	}

}
