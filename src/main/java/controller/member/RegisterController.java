package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.Member;
import model.service.MemberManager;
import model.service.exception.ExistingIdException;

public class RegisterController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			MemberManager memMan = MemberManager.getInstance();
			Member member = null;

			member = new Member(request.getParameter("id"), request.getParameter("password"),
					request.getParameter("name"), request.getParameter("birth"), request.getParameter("gender"),
					request.getParameter("email"), request.getParameter("phone"), request.getParameter("address"),
					"C", "/images/detailNulImg.svg");
			
			int createResult = memMan.createMember(member);
			if(createResult > 0)
				return "/member/loginForm.jsp";
			else
				return "/member/registerForm.jsp";
		} catch (ExistingIdException e) {
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
            
			return "/member/registerForm.jsp";			
		}
	}
}


