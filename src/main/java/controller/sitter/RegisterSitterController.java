package controller.sitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.PetSitterApplication;
import model.service.PetSitterApplicationManager;

public class RegisterSitterController implements Controller{
	 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PetSitterApplicationManager appMan = PetSitterApplicationManager.getInstance();
		String memberId = UserSessionUtils.getLoginUserId(session);
		
		if (request.getMethod().equals("GET")) {
			 PetSitterApplication applicationInfo = appMan.findApplication(memberId);
			 request.setAttribute("applicationInfo", applicationInfo);
			 
			 return "/sitter/sitterRegisterForm.jsp";
		 }
		 
		 return "";
		
	}
}
