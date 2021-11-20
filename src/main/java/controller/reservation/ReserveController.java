package controller.reservation;

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

		if (request.getMethod().equals("GET")) {
			String sitterId = (String) request.getParameter("sitterId");

			PetSitterManager sitterMan = PetSitterManager.getInstance();
			PetManager petMan = PetManager.getInstance();
			CareManager careMan = CareManager.getInstance();
			ServiceManager srvcMan = ServiceManager.getInstance();

			// 펫시터 정보
			PetSitter petsitterInfo = sitterMan.findPetSitter(sitterId);
			request.setAttribute("petsitterInfo", petsitterInfo);

			// 돌봄 가능 동물종 리스트
			List<PetKind> ablePetKinds = petMan.findAblePetKindLsit(sitterId);
			request.setAttribute("ablePetKinds", ablePetKinds);

			// 로그인한 유저의 반려동물 리스트
			List<Pet> userPets = petMan.findPetListOfMember(userId);
			request.setAttribute("userPets", userPets);

			// 로그인한 유저의 반려동물 별 사진 리스트
			for (Pet pet : userPets) {
				List<String> imgList = petMan.findPetAttachments(userId, pet.getId());
				pet.setImages(imgList);
			}

			// 가능 서비스
			List<Service> ableService = srvcMan.findProvideServiceList(sitterId);
			
			if (ableService != null) {
				Iterator<Service> iterator = ableService.iterator();
				Map<String, Service> serviceMap = new HashMap<String, Service>();
				while (iterator.hasNext()) {
					Service service = iterator.next();
					serviceMap.put(service.getId(), service);
				}
				ObjectMapper mapper = new ObjectMapper();
				String services = mapper.writeValueAsString(serviceMap);

				request.setAttribute("ableService", services);
			}

			return "/reservation/reservationForm.jsp";
		}

		// 예약 처리
		PetSitterManager petsitterMan = PetSitterManager.getInstance();
		ReservationManager reservationMan = ReservationManager.getInstance();
		ServiceManager serviceMan = ServiceManager.getInstance();

		PetSitter petsitter = (PetSitter) request.getAttribute("petSitterInfo");
//         String sitterId = petsitter.getSitter().getId();

		String[] fromdate = request.getParameter("fromDate").split("-");
		String[] todate = request.getParameter("toDate").split("-");
		String fDate = (fromdate[0] + fromdate[1] + fromdate[2]);
		String tDate = (todate[0] + todate[1] + todate[2]);
		int dateLength = Integer.parseInt(tDate) - Integer.parseInt(fDate);
		int money = 0;
		String[] payMoney = petsitter.getCalculatedPrice().split(",");
		int nightMoney = Integer.parseInt(payMoney[0]);
		int dayMoney = Integer.parseInt(payMoney[1]);
		if (dateLength == 0)
			money = dayMoney;
		else
			money = dateLength * nightMoney;

		Care care = new Care(request.getParameter("fromDate"), request.getParameter("toDate"), money,
				request.getParameter("cautionText"), null, null, new Member(userId),
				new PetSitter(new Member("M0000006")));
		return "redirect:/member/memberMyPage";
	}
}
