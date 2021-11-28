package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.Care;
import model.dto.Member;
import model.dto.PetSitter;

public class ReservationDAO {
	private JDBCUtil jdbcUtil = null;
	   
	   public ReservationDAO() {         
	      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	   }
	   
	   public int createCare(Care care) throws SQLException {
		  String sql = "INSERT INTO CARE VALUES (?, ?, care_seq.nextval, ?, TO_DATE(?, 'yyyy/MM/dd HH24:mi:ss'), ?, ?, ?)";
          Object[] param = new Object[] {care.getSitter().getSitter().getId(), care.getCompanion().getId(),
	    		  care.getStartDate(), care.getEndDate(), care.getTotalPrice(), care.getNotes(), care.getStatus()};   
          jdbcUtil.setSqlAndParameters(sql, param);
          
          String key[] = {"care_id"}; 
	      try {
	         int result = jdbcUtil.executeUpdate(key);
	         ResultSet rs = jdbcUtil.getGeneratedKeys(); 
	         int generatedKey = 0;
	         if (rs.next()) {
	        	 generatedKey = rs.getInt(1);
	        	 return generatedKey;
	         }
	      } catch (Exception ex) {
	          jdbcUtil.rollback();
	    	  ex.printStackTrace();
	      } finally {
	    	 jdbcUtil.commit();
	         jdbcUtil.close();      
	      }
		   return 0;
	   }
	   
	   public Care findReservation(int careId) throws SQLException {
		   String sql = "SELECT sitter_id, member_id, care_id, start_date, end_date, total_price, notes, care_status "
	                 + "FROM care "
	                 + "WHERE care_id=? ";              
	      jdbcUtil.setSqlAndParameters(sql, new Object[] {careId});

	      try {
	         ResultSet rs = jdbcUtil.executeQuery();      
	         if (rs.next()) {                  
	            Care care = new Care(
	               careId,
	               rs.getString("start_date"),
	               rs.getString("end_date"),
	               rs.getInt("total_price"),
	               rs.getString("notes"),
	               rs.getString("care_status"),               
	               new Member(rs.getString("member_id")),
	               new PetSitter(new Member(rs.getString("sitter_id")))
	            );
	            return care;
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	   }
}
