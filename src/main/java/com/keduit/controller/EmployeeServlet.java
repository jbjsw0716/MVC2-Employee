package com.keduit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keduit.controller.action.Action;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/* 클라이언트의 요청을 받아서 적절한 Action 객체를 실행하는 역할. 서블릿의 역할을 액션 기반으로 분리하여 코드의 유지보수성을 향상시킴 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 요청 파라미터에서 "command" 값을 가져오기.
		   이 값은 클라이언트가 요청한 작업의 종류를 나타내는 것으로, 보통 서블릿에서 어떤 동작을 수행할지 결정하는 데 사용 */
		String command = request.getParameter("command");
		System.out.println("EmployeeServlet에서 요청을 받음 : " + command);
		
		/* "command" 값을 기반으로 ActionFactory를 사용하여 해당하는 Action 객체를 가져오기.
		   Action 객체는 실제로 클라이언트 요청을 처리하는 데 사용 */
		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(command);
		
		/* 가져온 Action 객체가 null이 아닌 경우에는 해당 Action 객체의 execute() 메서드를 호출하여 클라이언트 요청을 처리.
		   이를 통해 실제 비즈니스 로직을 수행하고, 그에 따른 결과를 클라이언트에게 응답할 수 있음 */
		if (action != null) {
			action.execute(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트의 요청에 대한 문자 인코딩을 UTF-8로 설정.
		request.setCharacterEncoding("UTF-8");
		
		//doGet() 메스드를 호출하여 클라이언트의 요청을 처리. GET 요청을 처리하는 doGet() 메소드에서 처리되는 동작을 동일하게 적용하기 위한 것
		doGet(request, response); //doGet() 메소드에서 사용해도됨. 코드의 재사용성과 유지보수성을 높이기 위해 여기서 사용.
	}
}
