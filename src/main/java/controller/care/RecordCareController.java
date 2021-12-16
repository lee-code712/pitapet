package controller.care;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.CareDetails;
import model.dto.CareRecord;
import model.service.CareManager;
import model.service.ServiceManager;

public class RecordCareController implements Controller {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ServiceManager serviceMan = ServiceManager.getInstance();
		CareManager careMan = CareManager.getInstance();
		
		// 돌봄일지 작성 form 이동
		if (request.getMethod().equals("GET")) {
			// session에 id정보가 없으면 mainpage 호출 리다이렉션
			if(!UserSessionUtils.hasLogined(session)) {
				return "redirect:/mainpage";
			}
			
			int careId = Integer.parseInt(request.getParameter("careId"));
			List<CareDetails> checkList = serviceMan.findReceiveServiceList(careId);
			request.setAttribute("checkList", checkList);
			
			List<String> carePetNameList = new ArrayList<String>();
			for (CareDetails careDetail : checkList)
				carePetNameList.add(careDetail.getCarePet().getName());
			Set<String> setPets = new HashSet<String>(carePetNameList);
			carePetNameList = new ArrayList<String>(setPets);
			request.setAttribute("carePets", carePetNameList);
			
			return "/care/careRecordForm.jsp";
		}
		
		// 일지 추가 처리
		String careId = request.getParameter("careId");
		CareRecord careRecord = new CareRecord(-1, null, null, request.getParameter("title"), 
				request.getParameter("content"), new Care(Integer.parseInt(careId)), null);
		List<CareDetails> careDetails = new ArrayList<CareDetails>();
		String[] recvService = request.getParameterValues("recvService"); // 돌봄 받은 펫들의 id
		for (String recvId : recvService) {
			CareDetails careDetail = new CareDetails(recvId);
			careDetails.add(careDetail);
		}
		careRecord.setCheckList(careDetails);
		
		int isCreated = careMan.createCareRecord(careRecord);
		
		if (isCreated == 0) { // care 레코드 생성 실패
			request.setAttribute("recordFailed", true);
			session.setAttribute("recordInfo", careRecord);
			return "redirect:/care/recordCare";
		} 
		
		// 날짜만큼 개수를 채운경우 -> 돌봄완료로 전환
			
		if (session.getAttribute("recordInfo") != null)
			session.removeAttribute("recordInfo");
		
		return "redirect:/care/listCareDiary?careId=" + careId;
	}
}
