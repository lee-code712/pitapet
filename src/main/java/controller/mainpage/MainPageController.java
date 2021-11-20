package controller.mainpage;

import java.util.*;
import com.fasterxml.jackson.databind.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Review;
import model.dto.Care;
import model.dto.Member;
import model.service.CareManager;
import model.service.MemberManager;
import model.service.ReviewManager;

public class MainPageController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		CareManager careMan = CareManager.getInstance();
		ReviewManager reviewMan = ReviewManager.getInstance();
		MemberManager memMan = MemberManager.getInstance();
		
		// login 상태 확인
		if(UserSessionUtils.hasLogined(session)) {
			 List<Care> careSchedules = careMan.findCareSchedules(UserSessionUtils.getLoginUserId(session));
			 if(careSchedules != null) {
					Iterator<Care> iterator = careSchedules.iterator();
					Map<Integer, Care> scheduleMap = new HashMap<Integer, Care>();
					while(iterator.hasNext()) {
						Care care = iterator.next();
						scheduleMap.put(care.getId(), care);
					}
					ObjectMapper mapper = new ObjectMapper();   
					String schedules = mapper.writeValueAsString(scheduleMap);
					
					request.setAttribute("careSchedules", schedules);
			 }
		} else {
			request.setAttribute("isNotLogined", true);
		}
		
		List<Review> reviews = reviewMan.findReviewList();
		Collections.shuffle(reviews);
		
		List<Review> randomReviews = reviews.subList(0, 3);
		
		for (Review review : randomReviews) {
			Member sitterMemInfo = memMan.findMember(review.getCareInfo().getSitter().getSitter().getId());
			String[] address = sitterMemInfo.getAddress().split(" ");
			System.out.println(Arrays.toString(address));
			String city = null;
			for (int j = 0; j < address.length; j++) {
				if (address[j].matches("(.*)로")) {
					city = address[j].substring(0, address[j].length() - 1);
					System.out.println(city);
				}
			}
			review.getCareInfo().getSitter().getSitter().setAddress(city);
			
			// 파일 이미지 경로 set
			List<String> imgList = reviewMan.findReviewAttachments(review.getCareInfo().getCompanion().getId(), review.getCareInfo().getId());
			review.setImages(imgList);
			System.out.println(imgList.get(0));
		}
		request.setAttribute("reviews", randomReviews);
		
		System.out.print(reviews);
		
        return "/mainPage/mainPage.jsp";
    }
}
