package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Care;
import model.dto.Member;
import model.dto.PetSitter;

public class CareDAO {
	private JDBCUtil jdbcUtil = null;
	
	public CareDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	//사용자의 돌봄 예약 내역 반환
	public List<Care> findCareSchedules(String memberId) throws SQLException {
		String sql = "SELECT care_id, start_date, end_date, sitter_id "
				+ "FROM care c, member m "
				+ "WHERE c.member_id = m.member_id AND member_id = ?";     
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});	
						
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			List<Care> careList = new ArrayList<Care>();	
			while (rs.next()) {
				Care care = new Care(			
						rs.getInt("care_id"),
						rs.getString("start_date"),
						rs.getString("end_date"),
						new PetSitter(
								new Member(
										rs.getString("sitter_id")
								)
						)
				);
				careList.add(care);				
				}		
				return careList;					
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();
			}
		
		return null;
	}
}
