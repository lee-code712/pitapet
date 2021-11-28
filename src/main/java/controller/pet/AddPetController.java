package controller.pet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Pet;
import model.dto.PetKind;
import model.service.PetManager;

public class AddPetController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PetManager petMan = PetManager.getInstance();
		
		// 반려동물 추가 form 이동
		if (request.getMethod().equals("GET")) {
			// 동물 종 카테고리 리스트 전달(돌보미들이 선택한 돌봄 가능 돌물종으로 제한)
			List<PetKind> petKindList = petMan.findAllPetKindList();
			request.setAttribute("petKindList", petKindList);
			
			return "/member/petAddForm.jsp";
		}
		
		// 반려동물 추가
		
		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String kindId = request.getParameter("petKind");
		
		Pet pet = petMan.addPet(userId, name, birth, gender, kindId);
		
		if (pet != null) {
			return "redirect:/member/petView";
		}
		else {
			request.setAttribute("petInfo", pet);
			request.setAttribute("addFailed", true);
			return "/member/petAddForm.jsp";
		}
	}

}
