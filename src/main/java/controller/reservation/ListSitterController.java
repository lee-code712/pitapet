package controller.reservation;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.service.LikeListManager;
import model.service.PetSitterManager;
import model.dto.PetSitter;
import model.dto.LikeList;

public class ListSitterController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		PetSitterManager sitterMan = PetSitterManager.getInstance();
		LikeListManager likelistMan = LikeListManager.getInstance();
		int currentPage = Integer.parseInt(request.getParameter("currntPage"));
		int rpp = 2;
		
		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if(!UserSessionUtils.hasLogined(session)) {
			 return "redirect:/mainpage";
		}
		
		// 공개여부가 'Y'인 모든 돌보미 리스트 반환
		ArrayList<PetSitter> sitterList = sitterMan.findPetSitterList();
		
		// 페이지 정보 및 현재 페이지에 출력 될 돌보미 리스트를 전달
		int totalSitter = sitterList.size();
		int totalPage;
		if (totalSitter % 2 == 0)
			totalPage = totalSitter / rpp;
		else
			totalPage = totalSitter / rpp + 1;
		int startIndex = currentPage * rpp - 1;
		int endIndex;
		if (totalSitter - startIndex == 0)
			endIndex = startIndex;
		else if (totalSitter - startIndex == 1)
			endIndex = startIndex + 1;
		else
			endIndex = startIndex + rpp;
		ArrayList<Integer> pageInfo = new ArrayList<Integer>();
		pageInfo.add(totalPage, currentPage);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("petSitterList", sitterList.subList(startIndex, endIndex));

		// 지역별 추천 돌보미 리스트(3개) 전달
		
		// 좋아요 누른 돌보미 id list 전달
		ArrayList<LikeList> likeSitters = likelistMan.findLikeListOfMember(UserSessionUtils.getLoginUserId(session));
		request.setAttribute("likeSitters", likeSitters);
		
        return "/reservation/sitterList.jsp";
    }
}