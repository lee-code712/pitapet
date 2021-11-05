package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

private JDBCUtil jdbcUtil = null;
	
	public ReviewDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	//전체 리뷰 반환
	public List<Review> findReviewList() throws SQLException {
        String sql = "SELECT review_id, write_date, content, rate, member_id "
        		   + "FROM review";       
		jdbcUtil.setSqlAndParameters(sql, null);	
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			List<Review> reviewList = new ArrayList<Review>();	
			while (rs.next()) {
				Review review = new Review(			
						rs.getString("review_id"),
						rs.getString("write_date"),
						rs.getString("content"),
						rs.getString("rate"),
						rs.getCare("member_id"));
				
				reviewList.add(review);				
			}		
			return reviewList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
