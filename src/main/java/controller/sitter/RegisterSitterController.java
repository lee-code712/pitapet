package controller.sitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.PetSitterApplication;
import model.service.PetSitterApplicationManager;
import model.service.PetSitterManager;

public class RegisterSitterController implements Controller{
	 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PetSitterApplicationManager appMan = PetSitterApplicationManager.getInstance();
		PetSitterManager sitterMan = PetSitterManager.getInstance();
		String memberId = UserSessionUtils.getLoginUserId(session);
	
		if (request.getMethod().equals("GET")) {
			 PetSitterApplication applicationInfo = appMan.findApplicationByMemberId(memberId);
			 request.setAttribute("applicationInfo", applicationInfo);
			 
			 return "/sitter/sitterRegisterForm.jsp";
		}
		 
		try {
//	         boolean isRegister = sitterMan.createPetSitter();
//	         if(isRegister)
//	            return "redirect:/member/memberMyPage";
//	         else
//	            return "redirect:/petSitter/registerSitter";
			return "redirect:/member/memberMyPage";
	      } catch (Exception e) {
	         request.setAttribute("registerFailed", true);         
	         return "redirect:/petSitter/registerSitter";         
	      }
		
	}
}
