package controller.care;

import java.util.List;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.CareRecord;
import model.service.CareManager;

public class CareDiaryListController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		CareManager careMan = CareManager.getInstance();
		
		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if(!UserSessionUtils.hasLogined(session)) {
			return "redirect:/mainpage";
		}		
		
		int careId = Integer.valueOf(request.getParameter("careId")); 
		List<CareRecord> careRecords = careMan.findCareRecordsByCare(careId);
		request.setAttribute("careRecordList", careRecords);
		
		System.out.println(careRecords);
		
		return "/care/careDiary.jsp";
	}
}
