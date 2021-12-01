package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dto.Care;
import model.dto.Member;

public class MemberDAO {
private JDBCUtil jdbcUtil = null;
private JDBCUtil jdbcUtil2 = null;
   
   public MemberDAO() {         
      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
      jdbcUtil2= new JDBCUtil();
   }
   
   public Member findMember(String memberId) throws SQLException {
        String sql = "SELECT password, name, birth, gender, email, phone, address, identity, img_src "
                 + "FROM member JOIN attachment USING (member_id) "
                 + "WHERE member_id=? AND category_id = 'AtchId04'";              
      jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});   // JDBCUtil에 query문과 매개 변수 설정

      try {
         ResultSet rs = jdbcUtil.executeQuery();      
         if (rs.next()) {                  
            Member member = new Member(      
               memberId,
               rs.getString("password"),
               rs.getString("name"),
               rs.getString("birth"),
               rs.getString("gender"),
               rs.getString("email"),               
               rs.getString("phone"),
               rs.getString("address"),
               rs.getString("identity"),
               rs.getString("img_src")
            );
            return member;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      
      }
      return null;
   }
     
//   public String findProfileAttachment(String memberId) throws SQLException {   
//	     String sql = "SELECT img_src "
//	                + "FROM attachment "
//	                + "WHERE member_id=? AND img_src LIKE ?"; 
//	    	       
//	     String like = "%profile-" + memberId + "-%";
//	     like = like.replaceAll(" ", "");
//	       
//	     jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId, like});   // JDBCUtil에 query문과 매개 변수 설정
//
//	     try {
//	        ResultSet rs = jdbcUtil.executeQuery();  
//	        if (rs.next()) {                  
//	           String img_src = rs.getString("img_src");
//	           return img_src;
//	        }
//	     } catch (Exception ex) {
//	        ex.printStackTrace();
//	     } finally {
//	        jdbcUtil.close();      
//	     }
//	     return null;
//	  }
   
   public int createMember(Member newMember) throws SQLException {
		  String sql = "INSERT INTO MEMBER VALUES (?, ?, ?, TO_DATE(?, 'YYYYMMDD'), ?, ?, ?, ?, ?)";
		  String sql2 = "INSERT INTO ATTACHMENT VALUES (?, ?, ?)";
		  int rs = 0, rs2 = 0;
//		  Date birth = null;
//		  
//		  try {
//			String birthday = newMember.getBirth();
//			SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
//			birth = format.parse(birthday);
//		  } catch (Exception e) {
//			  e.printStackTrace();
//		  }
		  
		  Object[] param = new Object[] {newMember.getId(), newMember.getPassword(), newMember.getName(), newMember.getBirth(),
				  newMember.getGender(), newMember.getEmail(), newMember.getPhone(), newMember.getAddress(), 'C'};
		  
		  Object[] param2 = new Object[] {newMember.getProfileImage(), newMember.getId(), "AtchId04"};
		  
		  jdbcUtil.setSqlAndParameters(sql, param);
	      jdbcUtil2.setSqlAndParameters(sql2, param2);
		  
	      try {
	         rs = jdbcUtil.executeUpdate();
	      } catch (Exception ex) {
	          jdbcUtil.rollback();
	    	  ex.printStackTrace();
	      } finally {
	    	 jdbcUtil.commit();
	         jdbcUtil.close();      
	      }
	      
	      try {
	    	  rs2  = jdbcUtil2.executeUpdate();
	      } catch (Exception ex) {
	          jdbcUtil2.rollback();
	    	  ex.printStackTrace();
	      } finally {
	    	 jdbcUtil2.commit();
	         jdbcUtil2.close();      
	      }
	      
	      if(rs == 1 && rs2 == 1)
	    	  return 1;
	      else
	    	  return 0;
	   }
   
   public int update(Member updateInfo) throws SQLException {
		String sql = "UPDATE member "
					+ "SET password=?, email=?, phone=?, address=? "
					+ "WHERE member_id=?";
		Object[] param = new Object[] {updateInfo.getPassword(), updateInfo.getEmail(), 
				updateInfo.getPhone(), updateInfo.getAddress(), updateInfo.getId()};				
		jdbcUtil.setSqlAndParameters(sql, param);
			
		try {				
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;
	}
}
