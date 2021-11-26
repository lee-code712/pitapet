package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dto.Member;
import model.service.MemberManager;
import model.service.exception.PasswordMismatchException;

public class UpdateMemberController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);

		// 회원정보 수정 form 이동
		if (request.getMethod().equals("GET")) {
			MemberManager memMan = MemberManager.getInstance();

			Member memberInfo = memMan.findMember(userId);
			request.setAttribute("memberInfo", memberInfo);

			return "/member/memberUpdateForm.jsp";
		}
		MemberManager memMan = MemberManager.getInstance();
		Member memberInfo = memMan.findMember(userId);

		try {
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");

			Member updateInfo = new Member(userId, newPassword, email, phone, address);
			memMan.update(updateInfo, oldPassword);
		} catch (PasswordMismatchException e) {
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("memberInfo", memberInfo);
			return "/member/memberUpdateForm.jsp";
		}
		
		return "redirect:/member/memberMyPage";
	}
}
