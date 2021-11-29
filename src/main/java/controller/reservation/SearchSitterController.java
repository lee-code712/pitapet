package controller.reservation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dto.PetSitter;
import model.service.PetSitterManager;

public class SearchSitterController implements Controller {
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	
			HttpSession session = request.getSession();
			PetSitterManager sitterMan = PetSitterManager.getInstance();
			
			List<String> options = new ArrayList<>();
			options.add((String) request.getParameter("searchOption"));
			options.add((String) request.getParameter("keyword"));
			
			if (options.get(0) == null)
				options = (List<String>) session.getAttribute("searchOption");
			else
				session.setAttribute("searchOption", options);
			
			if (options.get(1) != null) {
				List<PetSitter> sitters = sitterMan.findPetSitterListByKeyword(options);
				session.setAttribute("searchSitters", sitters);
			}
			System.out.println(options.get(0));
			
			String currentPage = (String) request.getParameter("currentPage");
			return "redirect:/reservation/listSitter?currentPage=" + currentPage;
	}
}
