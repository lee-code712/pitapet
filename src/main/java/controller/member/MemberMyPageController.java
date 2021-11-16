package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.Member;
import model.service.CareManager;
import model.service.MemberManager;
import model.service.PetSitterApplicationManager;

public class MemberMyPageController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		MemberManager memberMan = MemberManager.getInstance();
		PetSitterApplicationManager applicationMan = PetSitterApplicationManager.getInstance();
		CareManager careMan = CareManager.getInstance();
		
		if(!UserSessionUtils.hasLogined(session)) {
			 return "redirect:/mainpage";
		}
		
		String userId = UserSessionUtils.getLoginUserId(session);
		
		Member memberInfo = memberMan.findMember(userId);
		request.setAttribute("memberInfo", memberInfo);
		System.out.println(memberInfo);
		
		String applicationStatus = applicationMan.getApprovalStatus(userId);
		request.setAttribute("applicationStatus", applicationStatus);
		System.out.println(applicationStatus);
		
		List<Care> careList = careMan.findCareSchedules(userId);
		request.setAttribute("careList", careList);
		
        return "/member/memberMyPage.jsp";
    }
}