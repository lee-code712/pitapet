package controller.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.CareDetails;
import model.dto.Member;
import model.dto.Pet;
import model.dto.PetKind;
import model.dto.PetSitter;
import model.dto.Service;
import model.service.CareManager;
import model.service.PetManager;
import model.service.PetSitterManager;
import model.service.ReservationManager;
import model.service.ServiceManager;
import model.service.exception.UnavailableReservationTimeException;

public class ReserveController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);

		// 예약 form 이동
		if (request.getMethod().equals("GET")) {
			String sitterId = (String) request.getParameter("sitterId");

			PetSitterManager sitterMan = PetSitterManager.getInstance();
			PetManager petMan = PetManager.getInstance();
			ServiceManager srvcMan = ServiceManager.getInstance();

			// 펫시터 정보
			PetSitter petsitterInfo = sitterMan.findPetSitter(sitterId);
			request.setAttribute("petsitterInfo", petsitterInfo);

			// 돌봄 시작일, 종료일 정보
			String fromDate = request.getParameter("fromDate");
			request.setAttribute("fromDate", fromDate);
			String toDate = request.getParameter("toDate");
			request.setAttribute("toDate", toDate);

			// 돌봄 가능 동물종 리스트
			List<PetKind> ablePetKinds = petMan.findAblePetKindList(sitterId);
			request.setAttribute("ablePetKinds", ablePetKinds);

			// 로그인한 유저의 반려동물 리스트
			List<Pet> userPets = petMan.findPetListOfMember(userId);
			request.setAttribute("userPets", userPets);
			
			// 로그인한 유저의 반려동물 리스트 js에서 사용하기 위해 JSON 객체로 저장
			if (userPets != null) {
				Map<String, Pet> petMap = petMan.getPetMapOfMember(userPets);

				ObjectMapper mapper = new ObjectMapper();
				String pets = mapper.writeValueAsString(petMap);

				request.setAttribute("userPetsJson", pets);
			}

			// 가능 서비스
			List<Service> ableService = srvcMan.findProvideServiceList(sitterId);

			if (ableService != null) {
				Map<String, Service> serviceMap = srvcMan.getServiceMap(ableService);
				
				ObjectMapper mapper = new ObjectMapper();
				String services = mapper.writeValueAsString(serviceMap);

				request.setAttribute("ableService", services);
			}

			return "/reservation/reservationForm.jsp";
		}

		// 예약 처리
		CareManager careMan = CareManager.getInstance();
		ReservationManager reservationMan = ReservationManager.getInstance();
		ServiceManager serviceMan = ServiceManager.getInstance();

		// care 레코드 생성
		Care care = reservationMan.createCare(Integer.parseInt(request.getParameter("totalPrice")), request.getParameter("fromDate")
				, request.getParameter("toDate"), request.getParameter("cautionText")
				, userId, request.getParameter("sitterId"));
		
		int careId = care.getId();

		if (careId == 0) { // care 레코드 생성 실패
			request.setAttribute("reservationFailed", true);
			request.setAttribute("care", care);
			return "redirect:/reservation/reserve";
		} else {
			String[] pets = request.getParameterValues("pet"); // 돌봄 받을 펫들의 id
			List<CareDetails> careDetails = new ArrayList<CareDetails>();

			for (String pet : pets) {
				String[] services = request.getParameterValues(pet); // 돌봄 받을 펫의 요청 서비스들

				for (String service : services) {
					String receiveServiceId = serviceMan.createReceiveService(careId, pet, service);

					if (receiveServiceId == null) { // receiveService 레코드 생성 실패
						serviceMan.deleteReceiveService(careId); // 삽입된 receive_service 레코드 삭제
						careMan.deleteCare(careId); // 삽입된 care 레코드 삭제

						request.setAttribute("reservationFailed", true);
						request.setAttribute("care", care);
						return "redirect:/reservation/reserve";
					} else {
						String recvId = Integer.toString(careId) + pet.replaceAll("[^0-9]", "") + service.replaceAll("[^0-9]", "");
						CareDetails cd = new CareDetails(recvId, care, new Service(service), new Pet(pet), "N");
						careDetails.add(cd);
					}
				}
			}
			care.setCareList(careDetails);

			request.setAttribute("reservationFailed", false);
			request.setAttribute("care", care);

			return "redirect:/member/memberMyPage";
		}
	}
}
