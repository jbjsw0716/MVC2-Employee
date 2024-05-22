package com.keduit.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keduit.dao.EmployeeDAO;
import com.keduit.dto.EmployeeVO;

//EmployeeWriteFormAction은 폼을 표시하는 역할을 하고, EmployeeWriteAction은 사용자가 입력한 데이터를 처리하는 역할//

//직원 정보를 입력받아 데이터베이스에 추가하는 액션 클래스
public class EmployeeWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//객체를 생성하여 입력받은 직원 정보를 저장할 준비
		EmployeeVO eVO = new EmployeeVO();
		
		//요청에서 지정된 매개변수의 값을 반환. 폼을 통해 전송된 직원 정보를 가져옴.
		eVO.setId(request.getParameter("id"));
		eVO.setPass(request.getParameter("pass"));
		eVO.setName(request.getParameter("name"));
		eVO.setLev(request.getParameter("lev"));
		eVO.setGender(request.getParameter("gender"));
		eVO.setPhone(request.getParameter("phone"));
		
		//EmployeeDAO 클래스의 싱글톤 인스턴스를 가져옴
		EmployeeDAO eDAO = EmployeeDAO.getInstance();
		//EmployeeDAO를 사용하여 데이터베이스에 새로운 직원 정보를 추가
		eDAO.insertEmployee(eVO);
		
		//새로운 EmployeeListAction을 생성하고 execute() 메서드를 호출하여 직원 목록 페이지로 이동
		new EmployeeListAction().execute(request, response);
	}

}
