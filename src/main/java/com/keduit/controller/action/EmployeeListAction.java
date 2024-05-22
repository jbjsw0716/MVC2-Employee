package com.keduit.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keduit.dao.EmployeeDAO;
import com.keduit.dto.EmployeeVO;

public class EmployeeListAction implements Action {
	//execute() 메서드를 사용하여 사원 목록을 요청하고, 해당 목록을 JSP 페이지로 전달하여 표시하는 기능을 수행
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String url = "/employee/employeeList.jsp";
		
		//EmployeeDAO 클래스의 인스턴스를 얻고, 데이터베이스와 상호작용하여 사원 목록을 가져옴.
		EmployeeDAO eDAO = EmployeeDAO.getInstance();
		//EmployeeDAO를 사용하여 모든 사원의 목록을 가져옴
		List<EmployeeVO> employeeList = eDAO.selectAllEmployee();
		
		//JSP 페이지에서 사용할 사원 목록을 request 객체에 속성으로 설정. 이를 통해 JSP페이지에서 해당 목록에 접근.
		request.setAttribute("employeeList", employeeList);
		
		//요청을 전달할 Dispatch 객체를 생성. 지정된 URL에 대한 요청을 처리할 수 있는 Dispatch 객체를 생성.
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		//요청을 전달. 지정된 URL에 대한 요청을 전달하고, 해당 페이지의 내용을 현재 응답으로 출력
		dispatcher.forward(request, response);
	}

}
