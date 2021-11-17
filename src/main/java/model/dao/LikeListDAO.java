package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.LikeList;

public class LikeListDAO {
	private JDBCUtil jdbcUtil = null;
	
	public LikeListDAO() {         
		jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	}
	
	public ArrayList<LikeList> findLikeListOfMember(String memberId) throws SQLException {
		String sql = "SELECT sitter_id "
                 + "FROM likelist "        
				 + "WHERE member_id = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();
	         if (rs.next()) {                  
	        	 ArrayList<LikeList> likeList = new ArrayList<>();
	        	 LikeList like = new LikeList(rs.getString("sitter_id"));
	        	 likeList.add(like);
	        	 while (rs.next()) {
	        		 like = new LikeList(rs.getString("sitter_id"));
		        	 likeList.add(like);
	        	 }
	            return likeList;
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	   }	
}
