package com.keduit.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keduit.dao.EmployeeDAO;
import com.keduit.dto.EmployeeVO;

//직원 정보를 업데이트하기 위한 폼을 표시하는 기능을 담당
public class EmployeeUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "employee/employeeUpdate.jsp";
		//요청 파라미터에서 직원의 ID를 가져옴
		String id = request.getParameter("id");
		
		//EmployeeDAO 객체를 사용하여 해당 ID에 해당하는 직원의 정보를 데이터베이스에서 가져옴
		EmployeeDAO eDAO = EmployeeDAO.getInstance();
		EmployeeVO eVO = eDAO.selectOneEmployee(id);
		
		//가져온 직원 정보를 request 객체의 속성으로 설정. 이를 통해 JSP 페이지에서 해당 정보를 사용할 수 있게 됨.
		request.setAttribute("employee", eVO);
		
		//지정된 JSP 페이지로 포워드하여 클라이언트에게 보여줌
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
