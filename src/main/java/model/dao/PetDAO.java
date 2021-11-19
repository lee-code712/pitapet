package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Pet;
import model.dto.PetKind;

public class PetDAO {
private JDBCUtil jdbcUtil = null;
	
	public PetDAO() {         
		jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	}
	
	public ArrayList<Pet> findPetListOfMember(String memberId) throws SQLException {
		String sql = "";
		jdbcUtil.setSqlAndParameters(sql, null);
		
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();
	         if (rs.next()) {                  
	        	 ArrayList<Pet> petList = new ArrayList<>();
	        	 Pet pet = new Pet();
	        	 petList.add(pet);
	        	 while (rs.next()) {
	        		 Pet pet = new Pet();
		        	 petList.add(pet);
	        	 }
	            return petList;
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	}
	
	public ArrayList<PetKind> findAblePetKindList(String sitterId) throws SQLException {
		String sql = "SELECT kind_id, large_category, small_category "
                + "FROM available_pet_kind JOIN pet_kind USING (kind_id) "        
				 + "WHERE sitter_id = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {sitterId});
		
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();
	         if (rs.next()) {                  
	        	 ArrayList<PetKind> petKindList = new ArrayList<>();
	        	 PetKind petKind = new PetKind(
	        			 rs.getString("kind_id"),
	        			 rs.getString("large_category"),
	        			 rs.getString("small_category"));
	        	 petKindList.add(petKind);
	        	 while (rs.next()) {
	        		 petKind = new PetKind(
		        			 rs.getString("kind_id"),
		        			 rs.getString("large_category"),
		        			 rs.getString("small_category"));
		        	 petKindList.add(petKind);
	        	 }
	            return petKindList;
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	}
}
