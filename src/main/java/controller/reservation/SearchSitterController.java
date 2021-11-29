package controller.reservation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class SearchSitterController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	
			HttpSession session = request.getSession();
			
			String option = (String) request.getParameter("searchOption");
			if (option == null) {
				option = (String) session.getAttribute("searchOption");
			}
			
			if (option.equals("list")) {
				// 돌보미 조회 목록 호출 시 세션에 저장된 옵션정보 삭제
				if (session.getAttribute("searchOption") != null)
					session.removeAttribute("searchOption");
			}
			else {
				String keyword = (String) request.getParameter("keyword");
				List<String> options = new ArrayList<>();
				options.add(option);
				options.add(keyword);
				if (option.equals("city")) {
					// 지역으로 키워드 검색
				}
				else if (option.equals("tag")) {
					// 태그로 키워드 검색
				}
				else if (option.equals("category")) {
					// 카테고리에서 선택한 종(키워드)으로 검색
				}
				// 세션에 옵션정보 저장
				session.setAttribute("searchOption", options);
			}
				
			String currentPage = (String) request.getParameter("currentPage");
			return "redirect:/reservation/listSitter?currentPage=" + currentPage;
	}
}
