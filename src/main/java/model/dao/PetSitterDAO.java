package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.LikeList;
import model.dto.Member;
import model.dto.PetSitter;
import model.dto.PetSitterApplication;

public class PetSitterDAO {
	private JDBCUtil jdbcUtil = null;
	
	public PetSitterDAO() {         
		jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	}
	
	/* 돌보미 전체 리스트 조회 (프로필 이미지 포함) */
	public ArrayList<PetSitter> findPetSitterList() throws SQLException {
		String sql = "SELECT ps.sitter_id, ps.tag, ps.notes, ps.sitter_like, ps.sitter_view, m.address, img_src "
                + "FROM member m JOIN petsitter ps ON (m.member_id = ps.sitter_id) "
				+ "JOIN attachment atm ON (m.member_id = atm.member_id) "        
				+ "WHERE ps.public_status = 'Y' AND atm.category_id = 'AtchId04'";
		jdbcUtil.setSqlAndParameters(sql, null);
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();
	         if (rs.next()) {                  
	        	 ArrayList<PetSitter> sitterList = new ArrayList<>();
	        	 PetSitter sitter = new PetSitter (
	        			 new Member (
	        					 rs.getString("sitter_id"), 
	        					 rs.getString("address"),
	        					 rs.getString("img_src")),
	        			 rs.getString("tag"),
	        			 rs.getString("notes"),
	        			 rs.getInt("sitter_like"),
	        			 rs.getInt("sitter_view"));
	        	 sitterList.add(sitter);
	        	 while (rs.next()) {
	        		 sitter = new PetSitter (
		        			 new Member (
		        					 rs.getString("sitter_id"), 
		        					 rs.getString("address"),
		        					 rs.getString("img_src")),
		        			 rs.getString("tag"),
		        			 rs.getString("notes"),
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
	
	/* 돌보미 정보 반환 */
	public PetSitter findPetSitter(String sitterId) throws SQLException {
		String sql = "SELECT ps.sitter_id, ps.public_status, ps.able_date, ps.caculated_price, "
				+ "ps.tag, ps.notes, ps.avg_rate, ps.sitter_like, ps.sitter_view, ps.apply_id, m.address, img_src "
                + "FROM member m JOIN petsitter ps ON (m.member_id = ps.sitter_id) "  
                + "JOIN attachment atm ON (m.member_id = atm.member_id) "
				+ "WHERE ps.sitter_id = ? AND atm.category_id = 'AtchId04'";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {sitterId});
		
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();
	         if (rs.next()) {                  
	        	 PetSitter sitter = new PetSitter (
	        			 new Member (
	        					 rs.getString("sitter_id"), 
	        					 rs.getString("address"),
	        					 rs.getString("img_src")),
	        			 rs.getString("public_status"),
	        			 rs.getString("able_date"),
	        			 rs.getString("caculated_price"),
	        			 rs.getString("tag"),
	        			 rs.getString("notes"),
	        			 rs.getFloat("avg_rate"),
	        			 rs.getInt("sitter_like"),
	        			 rs.getInt("sitter_view"),
	        			 new PetSitterApplication(rs.getString("apply_id")));
	        	 
	            return sitter;
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	   }

}
