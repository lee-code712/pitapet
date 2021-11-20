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
import model.service.LikeListManager;
import model.service.PetSitterManager;

public class ListLikeController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LikeListManager likeman = LikeListManager.getInstance();
		PetSitterManager sitterman = PetSitterManager.getInstance();
		
		if(!UserSessionUtils.hasLogined(session)) {
			 return "redirect:/mainpage";
		}
		
		List<LikeList> likeLists = likeman.findLikeListOfMember(UserSessionUtils.getLoginUserId(session));
		List<PetSitter> likeSitterLists = new ArrayList<PetSitter>();
		for (LikeList likeList : likeLists) {
			PetSitter likeSitter = sitterman.findPetSitter(likeList.getLikeSitter().getSitter().getId());
			String[] address = likeSitter.getSitter().getAddress().split(" ");
			String city = null;
			for (int j = 0; j < address.length; j++) {
				if (address[j].matches("(.*)로")) {
					city = address[j].substring(0, address[j].length() - 1);
				}
			}
			likeSitter.getSitter().setAddress(city);
			likeSitterLists.add(likeSitter);
		}
		
		request.setAttribute("likeSitterLists", likeSitterLists);
		
		return "/like/likeList.jsp";
	}
}
