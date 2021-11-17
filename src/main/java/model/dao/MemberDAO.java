package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.Member;

public class MemberDAO {
private JDBCUtil jdbcUtil = null;
   
   public MemberDAO() {         
      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
   }
   
   public Member findMember(String memberId) throws SQLException {
        String sql = "SELECT password, name, birth, gender, email, phone, address, identity "
                 + "FROM member "
                 + "WHERE member_id=? ";              
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
               rs.getString("identity")
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
}
