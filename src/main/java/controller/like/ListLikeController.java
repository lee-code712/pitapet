package controller.like;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.LikeList;
import model.service.LikeListManager;

public class ListLikeController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LikeListManager likeman = LikeListManager.getInstance();
		
		if(!UserSessionUtils.hasLogined(session)) {
			 return "redirect:/mainpage";
		}
		
		List<LikeList> likeLists = likeman.findLikeListOfMember(UserSessionUtils.getLoginUserId(session));
		
		request.setAttribute("likeLists", likeLists);
		
		return "/like/likeList.jsp";
	}
}
