package com.keduit.controller;

import com.keduit.controller.action.Action;
import com.keduit.controller.action.EmployeeDeleteAction;
import com.keduit.controller.action.EmployeeListAction;
import com.keduit.controller.action.EmployeeUpdateAction;
import com.keduit.controller.action.EmployeeUpdateFormAction;
import com.keduit.controller.action.EmployeeViewAction;
import com.keduit.controller.action.EmployeeWriteAction;
import com.keduit.controller.action.EmployeeWriteFormAction;

public class ActionFactory {
	//DAO의 싱글톤 패턴과 동일하니 설명 생략
	private ActionFactory() {}
	
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	//요청된 명령(command)에 따라 적절한 액션(Action) 객체를 반환하는 getAction() 메서드
	/* 간단한 팩토리 메서드를 사용하여 요청된 명령에 따라 적절한 액션 객체를 생성하고 반환.
	   이를 통해 클라이언트의 요청을 처리하는데 필요한 액션 객체를 동적으로 생성.*/
	public Action getAction(String command) {
		/* 액션 객체는 클라이언트의 요청을 처리하는데 사용되는 비즈니스 로직을 포함.
		   따라서 각 요청에 맞는 액션 객체를 생성하여 실행함으로써 웹 애플리케이션의 동작을 제어. */
		Action action = null;
		System.out.println("ActionFactory : " + command);
		
		//예를 들어, 요청된 명령(command)이 employee_list인 경우, EmployeeListAction 객체를 생성하여 반환(else if 동일)
		if (command.equals("employee_list")) {
			action = new EmployeeListAction();
		} else if (command.equals("employee_write")) {
			action = new EmployeeWriteAction();
		} else if (command.equals("employee_write_form")) {
			action = new EmployeeWriteFormAction();
		} else if (command.equals("employee_view")) {
			action = new EmployeeViewAction();
		} else if (command.equals("employee_delete")) {
			action = new EmployeeDeleteAction();
		} else if (command.equals("employee_update_form")) {
			action = new EmployeeUpdateFormAction();
		} else if (command.equals("employee_update")) {
			action = new EmployeeUpdateAction();
		}
		
		return action;
	}
}
