package controller.reservation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class SearchSitterController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	
			HttpSession session = request.getSession();
			return "redirect:/reservation/listSitter";
	}
}
