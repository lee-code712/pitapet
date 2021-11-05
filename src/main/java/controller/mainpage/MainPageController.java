package controller.mainpage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.Care;
import model.service.UserManager;

public class MainPageController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		UserManager manager = UserManager.getInstance();
		if(UserSessionUtils.hasLogined(session)) {
			List<Care> careDateList = manager.careDateList(UserSessionUtils.getLoginUserId(session));
			request.setAttribute("careDateList", careDateList);
		} else {
			request.setAttribute("NotLogin", true);
		}
		
		List<Review> reviewList = manager.reviewList();
		request.setAttribute("reviewList", reviewList);
		
        return "mainPage.jsp";
    }
}
