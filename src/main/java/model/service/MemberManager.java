package model.service;

import java.sql.SQLException;

import model.dao.MemberDAO;
import model.dto.Member;
import model.service.exception.ExistingIdException;
import model.service.exception.MemberNotFoundException;
import model.service.exception.PasswordMismatchException;

public class MemberManager {
	private static MemberManager userMan = new MemberManager();
	private MemberDAO memberDAO;

	private MemberManager() {
		try {
			memberDAO = new MemberDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MemberManager getInstance() {
		return userMan;
	}

	/* 로그인 정보 확인 후 로그인 성공 시 회원정보 반환 */
	public Member login(String memberId, String password)
			throws SQLException, MemberNotFoundException, PasswordMismatchException {
		Member member = memberDAO.findMember(memberId);

		if (member == null) {
			throw new MemberNotFoundException("존재하지 않는 아이디입니다.");
		}
		if (member != null && !member.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return member;
	}

	/* 특정 회원 정보 반환 */
	public Member findMember(String memberId) throws SQLException {
		return memberDAO.findMember(memberId);
	}

	/* 새로운 회원 생성 */
	public int createMember(Member newMember) throws SQLException, ExistingIdException {
		if (memberDAO.findMember(newMember.getId()) != null) {
			throw new ExistingIdException(newMember.getId() + "는 존재하는 아이디입니다.");
		}
		return memberDAO.createMember(newMember);
	}

	public int update(Member updateInfo, String oldPassword)
			throws SQLException, PasswordMismatchException {
		
		Member member = memberDAO.findMember(updateInfo.getId());

		if (!member.matchPassword(oldPassword)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		
		return memberDAO.update(updateInfo);
	}
}