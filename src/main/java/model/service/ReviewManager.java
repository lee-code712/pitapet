package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Review;
import model.dao.ReviewDAO;

public class ReviewManager {
	private static ReviewManager reviewMan = new ReviewManager();
	private ReviewDAO reviewDAO;
	
	private ReviewManager() {
		try {
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static ReviewManager getInstance() {
		return reviewMan;
	}
	
	public List<Review> findReviewList() throws SQLException {
		return reviewDAO.findReviewList();
	}
}
