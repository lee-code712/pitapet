package controller.reservation;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.PetKind;
import model.dto.PetSitter;
import model.dto.Review;
import model.dto.Service;
import model.service.CareManager;
import model.service.MemberManager;
import model.service.PetManager;
import model.service.PetSitterManager;
import model.service.ReviewManager;
import model.service.ServiceManager;

public class ViewSitterDetailController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		MemberManager memMan = MemberManager.getInstance();
		PetSitterManager sitterMan = PetSitterManager.getInstance();
		ServiceManager serviceMan = ServiceManager.getInstance();
		PetManager petMan = PetManager.getInstance();
		CareManager careMan = CareManager.getInstance();
		ReviewManager reviewMan = ReviewManager.getInstance();
		
		String memberId = UserSessionUtils.getLoginUserId(session);
		String sitterId = (String) request.getParameter("sitterId");

		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if (!UserSessionUtils.hasLogined(session)) {
			return "redirect:/mainpage";
		}

		// 돌보미 상세 정보 전달
		PetSitter sitter = sitterMan.findPetSitter(sitterId);
		List<Service> prvdServiceList = serviceMan.findProvideServiceList(sitterId);
		List<PetKind> ablePetKindList = petMan.findAblePetKindLsit(sitterId);
		sitter.getMyApplyInfo().setServices(prvdServiceList);
		sitter.getMyApplyInfo().setKinds(ablePetKindList);
		String[] address = sitter.getSitter().getAddress().split(" ");
		String city = null;
		for (int j = 0; j < address.length; j++) {
			if (address[j].matches("(.*)로"))
				city = address[j].substring(0, address[j].length() - 1);
		}
		sitter.getSitter().setAddress(city);

		request.setAttribute("sitterInfo", sitter);

		// 돌보미 돌봄 가능시간대 전달
		String unparsedAbleDay = sitter.getAbleDate();
		String parsedAbleDay = Integer.toBinaryString(unparsedAbleDay.charAt(0));
		String[] dateArr = parsedAbleDay.split("");
		request.setAttribute("dateArr", dateArr);

		// 로그인한 유저의 예약 스케줄 전달
		List<Care> careSchedules = careMan.findCareSchedules(memberId);
		if (careSchedules != null) {
			Iterator<Care> iterator = careSchedules.iterator();
			Map<Integer, Care> scheduleMap = new HashMap<Integer, Care>();
			while (iterator.hasNext()) {
				Care care = iterator.next();
				scheduleMap.put(care.getId(), care);
			}
			ObjectMapper mapper = new ObjectMapper();
			String schedules = mapper.writeValueAsString(scheduleMap);
			request.setAttribute("careSchedules", schedules);
		}

		// 해당 돌보미의 예약 스케줄 전달
		List<Care> sitterCareSchedules = careMan.findCareSchedulesOfSitter(sitterId);
		if (careSchedules != null) {
			Iterator<Care> iterator = sitterCareSchedules.iterator();
			Map<Integer, Care> sitterScheduleMap = new HashMap<Integer, Care>();
			while (iterator.hasNext()) {
				Care care = iterator.next();
				sitterScheduleMap.put(care.getId(), care);
			}
			ObjectMapper mapper = new ObjectMapper();
			String sitterSchedules = mapper.writeValueAsString(sitterScheduleMap);
			request.setAttribute("sitterCareSchedules", sitterSchedules);
		}

		// 돌보미 후기 전달
		List<Review> reviews = reviewMan.findReviewListOfSitter(sitterId);
		for (Review review : reviews) {
			List<String> imgList = reviewMan.findReviewAttachments(review.getCareInfo().getCompanion().getId(),
					review.getCareInfo().getId());
			review.setImages(imgList);
		}
		request.setAttribute("reviews", reviews);
		
		// 돌보미와의 돌봄완료 내역 중 리뷰 작성을 안한 내역 전달
		List<Care> careList = careMan.findCareOfDoNotReview(memberId, sitterId);
		request.setAttribute("careList", careList);
		

		return "/reservation/sitterDetailView.jsp";
	}
}
