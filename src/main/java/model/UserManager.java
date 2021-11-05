package model;

import java.sql.SQLException;
import java.util.List;

public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private ReviewDAO reviewDAO;
	
	private UserManager() {
		try {
			userDAO = new UserDAO();
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public List<Review> reviewList() throws SQLException {
		return reviewDAO.findReviewList();
	}
	
	public List<Care> caredateList() throws SQLException {
		return userDAO.careDateList();
	}
	
	
	public UserDAO getUserDAO() {
		return this.userDAO;
	}
	
}
