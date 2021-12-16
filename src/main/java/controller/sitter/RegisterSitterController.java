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
			String[] ableDate = request.getParameterValues("ableDate");
			for (String d : ableDate) {
				System.out.println(d);
			}
			int count = 0;
			String ableDateBin = "";
			for (int i = 0; i < 7; i++) { // 0 1 3 4 : 월 화 목 금 : 1101100
				if (count < ableDate.length) {
					if (Integer.parseInt(ableDate[count]) == i) {
						ableDateBin += "1";
						count++;
					}
					else {
						ableDateBin += "0";
					}
				}
				else {
					ableDateBin += "0";
				}
			}
			String ascii = Character.toString((char)Integer.parseInt(ableDateBin, 2));
			System.out.println(ableDateBin);
			System.out.println(ascii);
			
			sitter = new PetSitter(request.getParameter("publicStatus"), ascii, request.getParameter("calculatedPrice")
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
