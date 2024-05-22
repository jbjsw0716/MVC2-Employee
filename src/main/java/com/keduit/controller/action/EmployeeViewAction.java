package com.keduit.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keduit.dao.EmployeeDAO;
import com.keduit.dto.EmployeeVO;

//직원 정보를 보기 위한 서블릿 액션. 클라이언트가 특정 직원의 아이디를 요청하면, 해당 아이디에 대한 정보를 데이터베이스에서 가져와서 JSP 페이지에 표시.
public class EmployeeViewAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "employee/employeeView.jsp";
		//클라이언트로부터 요청된 직원의 아이디 가져오기.
		String id = request.getParameter("id");
		
		//EmployeeDAO를 사용하여 데이터베이스에서 해당 아이디에 대한 직원 정보 가져오기.
		EmployeeDAO eDAO = EmployeeDAO.getInstance();
		
		EmployeeVO eVO = eDAO.selectOneEmployee(id);
		//가져온 직원 정보를 request의 속성에 설정.
		request.setAttribute("employee", eVO);
		
		//요청된 JSP 페이지로 포워딩하여, 직원 정보를 클라이언트에게 표시.
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
