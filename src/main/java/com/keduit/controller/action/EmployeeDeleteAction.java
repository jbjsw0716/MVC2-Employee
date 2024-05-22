package com.keduit.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keduit.dao.EmployeeDAO;

//직원 정보를 삭제하는 기능을 수행하는 EmployeeDeleteAction 클래스
public class EmployeeDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트로부터 전달된 직원의 아이디(ID)를 파라미터로 가져오기.
		String id = request.getParameter("id");
		
		//EmployeeDAO 클래스의 인스턴스를 생성하여 직원 정보를 삭제하는 deleteEmployee() 메서드를 호출.
		EmployeeDAO eDAO = EmployeeDAO.getInstance();
		//DAO에 만들어 놓은 deleteEmployee 메소드를 이용하여 데이터베이스에서 해당 직원의 정보 삭제.
		eDAO.deleteEmployee(id);
		
		//삭제가 완료된 후 EmployeeListAction 클래스의 execute 메서드를 호출하여 직원 리스트 다시 보여주기.
		//삭제된 직원은 목록에서 제거. 수정된 목록은 화면에 출력.
		new EmployeeListAction().execute(request, response);
	}

}
