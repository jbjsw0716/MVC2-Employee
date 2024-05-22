package com.keduit.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 요청을 처리하는 동작을 구현하는 데 사용
//이를 통해서블릿에서 비즈니스 로직을 별도의 클래스로 분리하여 코드를 구조화하고 유지보수성을 향상시킬 수 있음
public interface Action {
	//이 메서드는 매개변수를 받아서 클라이언트의 요청을 처리하고 응답을 생성하는 역할
	public void execute(HttpServletRequest request, HttpServletResponse response)
		//메서드가 예외를 발생시킬 수 있다는 것을 알리는 것. 일반적으로 서블릿에서는 이러한 예외를 처리할 수 있도록 구현.
		throws ServletException, IOException;
}
