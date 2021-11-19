package controller.reservation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.member.UserSessionUtils;
import model.dto.Pet;
import model.dto.PetSitter;
import model.service.CareManager;
import model.service.MemberManager;
import model.service.PetManager;
import model.service.PetSitterManager;
import model.service.ReservationManager;
import model.service.exception.UnavailableReservationTimeException;

public class ReserveController {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String userId = UserSessionUtils.getLoginUserId(session);
		
		if (request.getMethod().equals("GET")) {
			// 화면 뿌려주고
			/*
			 * 돌보미 이름 - member 테이블
			 * 보호자 반려동물 리스트 - pet 테이블
			 * 돌보미 제공 가능 서비스 리스트 - provide_service 테이블
			 * 돌보미 돌봄 가능 반려동물 리스트 - available_pet_kind 테이블
			 */
			
			String sitterId = (String) request.getAttribute("sitterId");
			
			PetSitter petSitterInfo = PetSitterManager.findPetSitter(sitterId);
			request.setAttribute("petSitterInfo", petSitterInfo);
			
			List<Pet> ablePets = PetManager.findPetListOfMember(sitterId);
			request.setAttribute("ablePets", ablePets);
			
			List<Integer> ableDay = CareManager.findCareSchedules(sitterId);
			request.setAttribute("ableDay", ableDay);
			
			List<Pet> myPets = PetManager.findPetListOfMember(userId);
			request.setAttribute("myPets", myPets);
			
			// 가격 조회 추가
			
			return "/reservation/reservationForm.jsp";
		})
		
		// 예약 처리
		try {
			ReservationManager reservationMan = ReservationManager.getInstance();
            
			
			
            return "redirect:/member/memberMyPage";			
		} catch (UnavailableReservationTimeException e) {
            request.setAttribute("reservationFailed", true);
			request.setAttribute("exception", e);
            
			return  "redirect:/reservation/Reserve";			
		} 
	}
}
