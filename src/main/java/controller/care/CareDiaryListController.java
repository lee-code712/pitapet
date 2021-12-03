package controller.care;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class CareDiaryListController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String careId = request.getParameter("careId"); 
		
		return "/care/careDiary.jsp";
	}
}
