package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.Care;
import model.dto.Member;

public class ReservationDAO {
	private JDBCUtil jdbcUtil = null;
	   
	   public ReservationDAO() {         
	      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	   }
	   
	   public int createCare(Care care) throws SQLException {
		  String sql = "INSERT INTO CARE VALUES (?, ?, care_seq.nextval, ?, ?, ?, ?, ?)";
          Object[] param = new Object[] {care.getSitter().getSitter().getId(), care.getCompanion().getId(),
	    		  care.getStartDate(), care.getEndDate(), care.getTotalPrice(), care.getNotes(), care.getStatus()};   
          jdbcUtil.setSqlAndParameters(sql, param);
	      try {
	         int rs = jdbcUtil.executeUpdate();      
	         return rs;
	      } catch (Exception ex) {
	          jdbcUtil.rollback();
	    	  ex.printStackTrace();
	      } finally {
	    	 jdbcUtil.commit();
	         jdbcUtil.close();      
	      }
		   return 0;
	   }
}
