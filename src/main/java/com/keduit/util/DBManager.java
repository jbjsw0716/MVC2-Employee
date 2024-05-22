package com.keduit.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//JDBC를 사용하여 데이터베이스 연결을 관리하는 유틸리티 클래스. 데이터베이스 연결 획득과 해제를 관리하는 메서드를 제공.
/* 이 클래스를 사용하여 데이터베이스 연결을 관리할 수 있으며, 메서드를 호출하여 데이터베이스 자원을 안전하게 해재할 수 있다.
   이렇게 함으로써 메모리 누수나 데이터베이스 리소스 누수를 방지할 수 있다. */
public class DBManager {
	//데이터베이스 연결을 가져오는 역할
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//Connection, Statement, ResultSet을 인자로 받아서 각각을 닫는 역할. 사용이 끝난 자원을 정리하고 연결을 닫는다.
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//위와 동일하지만 ResultSet이 필요하지 않을 때 사용하는 메서드.
	public static void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
