package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Care;

public class UserDAO {
private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	//사용자의 돌봄 예약 내역 날짜 반환
	public List<Care> careDateList(String id) throws SQLException {
        String sql = "SELECT care_id, start_date, end_date, member_id "
        		   + "FROM care "
        		   + "WHERE member_id = ?";     
		jdbcUtil.setSqlAndParameters(sql, new Object[] {id});	
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			List<Care> careDateList = new ArrayList<Care>();	
			while (rs.next()) {
				Care care = new Care(			
						rs.getInt("care_id"),
						rs.getString("start_date"),
						rs.getString("end_date"),
						rs.getString("member_id"));
				careDateList.add(care);				
			}		
			return careDateList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
