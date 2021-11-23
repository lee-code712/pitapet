package controller.reservation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if(!UserSessionUtils.hasLogined(session)) {
			 return "redirect:/mainpage";
		}
		
		// 페이지 정보 및 현재 페이지에 출력 될 돌보미 리스트를 전달
		Map<Integer, List<PetSitter>> sitterMap = sitterMan.getPetSittersOfPage(currentPage);
		if (sitterMap != null) {
			Iterator<Integer> iterator = sitterMap.keySet().iterator();
	        if (iterator.hasNext()) {
	        	Integer totalPage = iterator.next();        	
	        	ArrayList<Integer> pageInfo = new ArrayList<Integer>();
				pageInfo.add(totalPage);
				pageInfo.add(currentPage);
				request.setAttribute("pageInfo", pageInfo);
				request.setAttribute("petSitterList", sitterMap.get(totalPage));
	        }
			// 현재 로그인한 사용자의 likeList 전달
			List<LikeList> likeSitters = likelistMan.findLikeListOfMember(UserSessionUtils.getLoginUserId(session));
			request.setAttribute("likeSitters", likeSitters);
		}
		// 지역 및 동물 맞춤 추천 돌보미 전달
		
		
        return "/reservation/sitterList.jsp";
    }
}