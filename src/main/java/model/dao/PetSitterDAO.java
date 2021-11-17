package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Member;
import model.dto.PetSitter;

public class PetSitterDAO {
	private JDBCUtil jdbcUtil = null;
	
	public PetSitterDAO() {         
		jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	}
	
	public ArrayList<PetSitter> findPetSitterList() throws SQLException {
		String sql = "SELECT ps.sitter_id, ps.tag, ps.notes, ps.avg_rate, ps.sitter_like, ps.sitter_view, m.address "
                 + "FROM member m JOIN petsitter ps ON (m.member_id = ps.sitter_id) "        
				 + "WHERE ps.public_status = 'Y'";
		jdbcUtil.setSqlAndParameters(sql, null);
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();
	         if (rs.next()) {                  
	        	 ArrayList<PetSitter> sitterList = new ArrayList<>();
	        	 PetSitter sitter = new PetSitter (
	        			 new Member (
	        					 rs.getString("sitter_id"), 
	        					 rs.getString("address")),
	        			 rs.getString("tag"),
	        			 rs.getString("notes"),
	        			 rs.getFloat("avg_rate"),
	        			 rs.getInt("sitter_like"),
	        			 rs.getInt("sitter_view"));
	        	 sitterList.add(sitter);
	        	 while (rs.next()) {
	        		 sitter = new PetSitter (
	        			 new Member (
	        					 rs.getString("sitter_id"), 
	        					 rs.getString("address")),
	        			 rs.getString("tag"),
	        			 rs.getString("notes"),
	        			 rs.getFloat("avg_rate"),
	        			 rs.getInt("sitter_like"),
	        			 rs.getInt("sitter_view"));
	        		 sitterList.add(sitter);
	        	 }
	            return sitterList;
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	   }
}
