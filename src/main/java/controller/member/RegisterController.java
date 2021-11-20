package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.Member;
import model.service.MemberManager;
import model.service.PetSitterManager;
import model.service.exception.ExistingIdException;
import model.service.exception.MemberNotFoundException;
import model.service.exception.PasswordCkMismatchException;
import model.service.exception.PasswordMismatchException;

public class RegisterController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		try {
			MemberManager memMan = MemberManager.getInstance();
			System.out.println(request.getParameter("male"));
//			if (memMan.findMember(request.getParameter("id")) != null)
//					
//			if(request.getParameter("password") != request.getParameter("checkPassword"))
				
//			Member member = new Member(request.getParameter("id"), request.getParameter("password"), 
//					request.getParameter("name"), request.getParameter("birth"), //젠더 들어갈 자리,
//					request.getParameter("email"), request.getParameter("phone"), request.getParameter("address"), null);
			return "/member/loginForm.jsp";
//		} catch (ExistingIdException e) {
//            request.setAttribute("registerFailed", true);
//			request.setAttribute("exception", e);
//            
//			return "/member/registerForm.jsp";			
//		} catch (PasswordCkMismatchException e) {
//            request.setAttribute("registerFailed", true);
//			request.setAttribute("exception", e);
//           
//			return "/member/registerForm.jsp";			
		}		
	}

}
