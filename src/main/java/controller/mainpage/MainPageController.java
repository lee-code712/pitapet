package controller.mainpage;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

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
		
		if(UserSessionUtils.hasLogined(session)) {
			 List<Care> careSchedules = careMan.findCareSchedules(UserSessionUtils.getLoginUserId(session));
			 request.setAttribute("careSchedules", careSchedules);
			 request.setAttribute("isLogined", true);
		} else {
			request.setAttribute("isLogined", false);
		}
		
		List<Review> reviews = reviewMan.findReviewList();
		request.setAttribute("reviews", reviews);
		
		Random rd = new Random();
		List<Review> randomReviews = new ArrayList<Review>();
		for (int i = 0; i < 3; i++) {
			int randomIndex = rd.nextInt(reviews.size());
			Review review = reviews.get(randomIndex);
			Member sitterMemInfo = memMan.findMember(review.getCareInfo().getSitter().getSitter().getId());
			String[] address = sitterMemInfo.getAddress().split(" ");
			String city = null;
			for (int j = 0; j < address.length; j++) {
				if (address[j].matches("(.*)로"))
					city = address[j].substring(0, address[j].length() - 2);
			}
			review.getCareInfo().getSitter().getSitter().setAddress(city);
			randomReviews.add(reviews.get(randomIndex));
		}
		
		System.out.print(reviews);
		
        return "/mainPage/mainPage.jsp";
    }
}
