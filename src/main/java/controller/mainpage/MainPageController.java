package controller.mainpage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.Care;
import model.service.CareManager;
import model.service.ReviewManager;

public class MainPageController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		CareManager careMan = CareManager.getInstance();
		ReviewManager reviewMan = ReviewManager.getInstance();
		
		if(UserSessionUtils.hasLogined(session)) {
			// List<Care> careSchedules = careMan.getCareList(UserSessionUtils.getLoginUserId(session));
			// request.setAttribute("careSchedules", careSchedules);
		} else {
			request.setAttribute("isLogined", false);
		}
		
		List<Review> reviews = reviewMan.getAllReviews();
		request.setAttribute("reviews", reviews);
		
		System.out.print(reviews);
		
        return "/mainPage.jsp";
    }
}
