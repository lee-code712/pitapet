package controller.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.Member;
import model.dto.Pet;
import model.dto.PetSitter;
import model.service.CareManager;
import model.service.MemberManager;
import model.service.PetSitterApplicationManager;
import model.service.PetSitterManager;

public class MemberMyPageController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		MemberManager memberMan = MemberManager.getInstance();
		PetSitterApplicationManager applicationMan = PetSitterApplicationManager.getInstance();
		CareManager careMan = CareManager.getInstance();
		PetSitterManager sitterMan = PetSitterManager.getInstance();
		
		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if(!UserSessionUtils.hasLogined(session)) {
			 return "redirect:/mainpage";
		}
		
		// 마이페이지에 보일 정보 전달
		String userId = UserSessionUtils.getLoginUserId(session);
		
		Member memberInfo = memberMan.findMember(userId);
		if (memberInfo != null) {
			ObjectMapper mapper = new ObjectMapper();
			String memberInfoJson = mapper.writeValueAsString(memberInfo);
			request.setAttribute("memberInfo", memberInfo);
			request.setAttribute("memberInfoJson", memberInfoJson);
		}
		
		String applicationStatus = applicationMan.getApprovalStatus(userId);
		request.setAttribute("applicationStatus", applicationStatus);
		
		List<Care> careList = careMan.getCareSchedules(userId, null);
		if (careList != null) {
			ObjectMapper mapper = new ObjectMapper();
			String careListJson = mapper.writeValueAsString(careList);
			request.setAttribute("careList", careList);
			request.setAttribute("careListJson", careListJson);
		}
		
		// 돌보미 정보 전달
		if (memberInfo.getIdentity().equals("S")) {
			PetSitter sitterInfo = sitterMan.findPetSitter(userId);
			if (sitterInfo != null) {
				ObjectMapper mapper = new ObjectMapper();
				String sitterInfoJson = mapper.writeValueAsString(sitterInfo);
				request.setAttribute("sitterInfo", sitterInfo);
				request.setAttribute("sitterInfoJson", sitterInfoJson);
			}
		}
		
        return "/member/memberMyPage.jsp";
    }
}