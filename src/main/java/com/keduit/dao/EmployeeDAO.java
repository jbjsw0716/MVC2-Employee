package com.keduit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.keduit.dto.EmployeeVO;
import com.keduit.util.DBManager;

public class EmployeeDAO {
	/* 싱글톤 디자인 패턴을 구현할 때 사용
	-> 클래스의 인스턴스가 단 하나만 생성되도록 보장하고, 그 인스턴스에 대한 전역적인 접근점을 제공하는 디자인 패턴 */
	private EmployeeDAO() {}
	
	//private으로 선언된 정적 필드 instance 생성. 이 필드는 EmployeeDAO 클래스의 유일한 인스턴스를 저장.
	private static EmployeeDAO instance = new EmployeeDAO();
	
	//외부에서 EmployeeDAO 클래스의 인스턴스를 가져오는 데 사용. 이 메서드는 항상 동일한 인스턴스를 반환하도록 구현.
	public static EmployeeDAO getInstance() {
		return instance;
	}
	
	/* 데이터베이스에서 모든 직원 정보를 가져오는 메서드. 이 메서드는 EmployeeVO 객체의 리스트 반환.
	   1. SQL 쿼리를 정의, 이를 실행하여 결과 얻기
	   2. 결과 집합에서 각 행을 반복하여 EmployeeVO 객체를 생성, 각 열의 값을 해당 객체에 설정
	   3. EmployeeVO 객체를 리스트에 추가
	   4. 데이터베이스 연결을 닫고, 리스트 반환 */
	public List<EmployeeVO> selectAllEmployee() {
		String sql = "select * from employees order by name asc";
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		conn = DBManager.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				EmployeeVO eVO = new EmployeeVO();
				
				eVO.setId(rs.getString("id"));
				eVO.setPass(rs.getString("pass"));
				eVO.setName(rs.getString("name"));
				eVO.setLev(rs.getString("lev"));
				eVO.setEnter(rs.getTimestamp("enter"));
				eVO.setGender(rs.getString("gender"));
				
				if (rs.getString("phone") == null) {
	            	eVO.setPhone("해당 없음");
	            } else {
	            	eVO.setPhone(rs.getString("phone"));
	            }
				
				list.add(eVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		
		return list;
	}
	
	//selectAllEmployee() 메소드와 동일한 방식
	public EmployeeVO selectOneEmployee(String id) {
		EmployeeVO eVO = null;
		String sql = "select * from employees where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				eVO = new EmployeeVO();
				eVO.setId(rs.getString("id"));
				eVO.setPass(rs.getString("pass"));
				eVO.setName(rs.getString("name"));
				eVO.setLev(rs.getString("lev"));
				eVO.setGender(rs.getString("gender"));
				
	            if (rs.getString("phone") == null) {
	            	eVO.setPhone("해당 없음");
	            } else {
	            	eVO.setPhone(rs.getString("phone"));
	            }
	            
				eVO.setEnter(rs.getTimestamp("enter"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		System.out.println("----- selectOne : " + eVO);
		
		return eVO;
	}
	
	//데이터베이스에 직원 정보를 추가하는 메서드. 이 메서드는 EmployeeVO 객체에 저장된 직원 정보를 받아와서 데이터베이스에 삽입.
	public void insertEmployee(EmployeeVO eVO) {
		String sql = "insert into employees(id, pass, name, lev, gender, phone, enter) values(?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		//SQL 문을 실행하기 위한 객체. PreparedStatement를 사용하면 SQL 쿼리에 매개변수를 바인딩하여 SQL 인젝션과 같은 공격을 방지 가능.
		PreparedStatement pstmt = null;
		
		try {
			//DBManager 클래스의 정적 메서드로, 데이터베이스와의 연결을 설정. 이 메서드는 데이터베이스 연결을 가져오는 데 사용.
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, eVO.getId());
			pstmt.setString(2, eVO.getPass());
			pstmt.setString(3, eVO.getName());
			pstmt.setString(4, eVO.getLev());
			pstmt.setString(5, eVO.getGender());
			pstmt.setString(6, eVO.getPhone());
			pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			
			//PreparedStatement 객체의 메서드로, SQL 문을 실행하여 데이터베이스에 업데이트를 수행. 이 메서드는 삽입, 갱신, 삭제 등의 작업에 사용.
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//DBManager 클래스의 정적 메서드로, 데이터베이스 연결을 닫음. 이 메서드는 사용이 끝난 데이터베이스 연결을 반환하고, 자원을 해제.
			DBManager.close(conn, pstmt);
		}
		
		System.out.println("-----insert : " + eVO);
	}
	
	//데이터베이스에 id를 확인하여 직원 리스트를 삭제하는 메서드. 다른 DAO 메서드들과 구조는 비슷.
	public void deleteEmployee(String id) {
		String sql = "delete from employees where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//EmployeeVO 객체를 사용하여 데이터베이스의 employees 테이블을 업데이트(다른 DAO 메서드들과 구조는 비슷) -> 정보 수정 페이지
	public void updateEmployee(EmployeeVO eVO) {
		String sql = "update employees set pass=?, name=?, lev=?, gender=?, phone=? where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, eVO.getPass());
			pstmt.setString(2, eVO.getName());
			pstmt.setString(3, eVO.getLev());
			pstmt.setString(4, eVO.getGender());
			pstmt.setString(5, eVO.getPhone());
			pstmt.setString(6, eVO.getId());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
}
