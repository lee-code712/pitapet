package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Review;
import model.dto.Care;
import model.dto.Member;
import model.dto.PetSitter;

public class ReviewDAO {

private JDBCUtil jdbcUtil = null;
	
	public ReviewDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	//전체 리뷰 반환
	public List<Review> findReviewList() throws SQLException {
        String sql = "SELECT review_id, write_date, content, rate, r.care_id, c.member_id, c.sitter_id "
        		+ "FROM review r JOIN care c USING (care_id)";
		jdbcUtil.setSqlAndParameters(sql, null);	
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			List<Review> reviews = new ArrayList<Review>();	
			while (rs.next()) {
				Review review = new Review(			
						rs.getString("review_id"),
						rs.getString("write_date"),
						rs.getString("content"),
						rs.getFloat("rate"),
						new Care(
								rs.getInt("care_id"),
								new Member(rs.getString("member_id")),
								new PetSitter(
										new Member(rs.getString("sitter_id")))));
				reviews.add(review);				
			}		
			return reviews;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
