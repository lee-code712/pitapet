package model.service;

import java.sql.SQLException;

import model.dao.MemberDAO;
import model.dao.ReviewDAO;

public class MemberManager {
	private static MemberManager userMan = new MemberManager();
	private MemberDAO memberDAO;
	private ReviewDAO reviewDAO;
	
	private MemberManager() {
		try {
			memberDAO = new MemberDAO();
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static MemberManager getInstance() {
		return userMan;
	}
		
	public MemberDAO getMemberDAO() {
		return this.memberDAO;
	}
	
//	public boolean login(String userId, String password)
//			throws SQLException, UserNotFoundException, PasswordMismatchException {
//			Member member = findUser(userId);
//
//			if (!user.matchPassword(password)) {
//				throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
//			}
//			return true;
//		}
}
