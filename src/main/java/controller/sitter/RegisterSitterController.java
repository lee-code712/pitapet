package controller.sitter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.PetSitter;
import model.dto.PetSitterApplication;
import model.dto.Service;
import model.service.PetSitterApplicationManager;
import model.service.PetSitterManager;
import model.service.ServiceManager;

public class RegisterSitterController implements Controller{
	 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		PetSitterApplicationManager appMan = PetSitterApplicationManager.getInstance();
		PetSitterManager sitterMan = PetSitterManager.getInstance();
		ServiceManager servMan = ServiceManager.getInstance();
		
		String memberId = UserSessionUtils.getLoginUserId(session);
		
		PetSitter sitter = null;
		
		if (request.getMethod().equals("GET")) {
			 PetSitterApplication applicationInfo = appMan.findApplicationByMemberId(memberId);
			 List<Service> serviceList = servMan.findAllServiceList();
			 
			 request.setAttribute("serviceList", serviceList);
			 request.setAttribute("applicationInfo", applicationInfo);
			 
			 return "/sitter/sitterRegisterForm.jsp";
		}
		 
		try {
//			String[] service = request.getParameterValues("serviceCheck");
//			List<Service> serviceList = new ArrayList<>();
//			for(int i = 0; i < service.length; i++) {
//				Service s = new Service();
//				s.setId(service[i]);
//				serviceList.add(s);
//			}
//			
//			String[] ableDate = request.getParameterValues("ableDate");
			
			sitter = new PetSitter(request.getParameter("publicStatus"), "@", request.getParameter("calculatedPrice")
					,request.getParameter("tag"), request.getParameter("notes"));
			
			
			 PetSitterApplication applicationInfo = appMan.findApplicationByMemberId(memberId);
			 
	         boolean isRegister = sitterMan.createSitter(memberId, sitter, applicationInfo.getId());
	         if(isRegister)
	            return "redirect:/member/memberMyPage";
	         else
	            return "redirect:/petSitter/registerSitter";
	      } catch (Exception e) {
	         request.setAttribute("registerFailed", true);         
	         return "redirect:/petSitter/registerSitter";         
	      }
		
	}
}
