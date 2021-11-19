package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Care;
import model.dto.Member;
import model.dto.PetSitter;
import model.dto.Service;

public class ServiceDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ServiceDAO() {         
		jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	}
	
	public ArrayList<Service> findProvideServiceList(String sitterId) throws SQLException {
		String sql = "SELECT service_id, s.title, s.content "
                 + "FROM provide_service prvds JOIN service s USING (service_id) "        
				 + "WHERE prvds.sitter_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {sitterId});
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();
	         if (rs.next()) {                  
	        	 ArrayList<Service> serviceList = new ArrayList<>();
	        	 Service service = new Service (
	        			 rs.getString("service_id"),
	        			 rs.getString("title"),
	        			 rs.getString("content"));
	        	 serviceList.add(service);
	        	 while (rs.next()) {
	        		 service = new Service (
		        			 rs.getString("service_id"),
		        			 rs.getString("title"),
		        			 rs.getString("content"));
	        		 serviceList.add(service);
	        	 }
	            return serviceList;
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	   }
	
// 아직 완료x	
	 public int createRecvService(Care care) throws SQLException {
		  String sql = "INSERT INTO RECEIVE_SERVICE VALUES (?, ?, ?, ?)";
         Object[] param = new Object[] {};   
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
