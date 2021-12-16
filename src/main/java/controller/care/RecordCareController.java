package controller.care;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.CareDetails;
import model.service.ServiceManager;

public class RecordCareController implements Controller {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ServiceManager serviceMan = ServiceManager.getInstance();
		
		// 돌봄일지 작성 form 이동
		if (request.getMethod().equals("GET")) {
			// session에 id정보가 없으면 mainpage 호출 리다이렉션
			if(!UserSessionUtils.hasLogined(session)) {
				return "redirect:/mainpage";
			}
			
			int careId = Integer.parseInt(request.getParameter("careId"));
			List<CareDetails> checkList = serviceMan.findReceiveServiceList(careId);
			request.setAttribute("checkList", checkList);
			return "/care/careRecordForm.jsp";
		}
		
		// 일지 추가 처리
		return "redirect:/care/listCareDiary";
	}
}
