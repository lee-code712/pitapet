package controller.like;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.LikeList;
import model.dto.PetSitter;
import model.dto.Review;
import model.service.LikeListManager;
import model.service.PetSitterManager;

public class ListLikeController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LikeListManager likeman = LikeListManager.getInstance();
		PetSitterManager sitterman = PetSitterManager.getInstance();
		
		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if(!UserSessionUtils.hasLogined(session)) {
			 return "redirect:/mainpage";
		}
		
		// 사용자가 좋아요를 누른 돌보미 목록을 전달
		List<PetSitter> likeSitterLists = likeman.findLikeSitterOfMember(UserSessionUtils.getLoginUserId(session));
		request.setAttribute("likeSitterLists", likeSitterLists);
		
		return "/like/likeList.jsp";
	}
}
