package controller.like;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class LikeController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String status = (String) request.getParameter("status");
		String sitterId = (String) request.getParameter("sitterId");
		
		if (status.equals("add")) {
			// 좋아요 레코드 추가
		}
		else if (status.equals("cancel")) {
			// 좋아요 레코드 삭제
		}
		
		return "redirect:";
	}
}
