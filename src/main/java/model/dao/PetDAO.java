package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Pet;

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
}
