package com.keduit.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keduit.dao.EmployeeDAO;
import com.keduit.dto.EmployeeVO;

//직원 정보를 업데이트하는 작업을 수행하는 서블릿 액션 클래스
public class EmployeeUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//EmployeeVO 객체를 생성. 이 객체는 업데이트할 직원 정보를 담을 수 있음.
		EmployeeVO eVO = new EmployeeVO();
		
		//요청 파라미터에서 직원의 ID, 비밀번호, 이름, 직급, 성별, 전화번호를 가져와서 EmployeeVO 객체에 설정
		eVO.setId(request.getParameter("id"));
		eVO.setPass(request.getParameter("pass"));
		eVO.setName(request.getParameter("name"));
		eVO.setLev(request.getParameter("lev"));
		eVO.setGender(request.getParameter("gender"));
		eVO.setPhone(request.getParameter("phone"));
		
		//EmployeeDAO 객체를 사용하여 업데이트된 직원 정보를 데이터베이스에 업데이트
		EmployeeDAO eDAO = EmployeeDAO.getInstance();
		eDAO.updateEmployee(eVO);
		
		//EmployeeViewAction 객체를 생성하여 직원 목록을 다시 보여줌. 이를 위해 EmployeeViewAction의 execute 메서드를 호출.
		new EmployeeViewAction().execute(request, response);
	}

}
