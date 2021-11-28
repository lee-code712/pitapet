package controller.reservation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class SearchSitterController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	
			HttpSession session = request.getSession();
			
			String option = (String) request.getParameter("searchOption");
			if (option == null) {
				String currentPage = (String) request.getParameter("currentPage");
				return "redirect:/reservation/listSitter?currentPage=" + currentPage;
			}
			
			if (option.equals("city")) {
				// 지역으로 키원드 검색
			}
			else if (option.equals("tag")) {
				// 태그로 키워드 검색
			}
			else if (option.equals("rankLike")) {
				// 종아요 순으로 정렬
			}
			else if (option.equals("rankView")) {
				// 조회 순으로 정렬
			}
			else if (option.equals("category")) {
				// 카테고리에서 선택한 종으로 검색
			}
			
			return "redirect:/reservation/listSitter";
	}
}
