package controller.reservation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class ReserveController {

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
			PetSitter petSitterInfo = sitterMan.findPetSitter(sitterId);
			request.setAttribute("petSitterInfo", petSitterInfo);

			// 돌봄 가능 동물종 리스트
			List<PetKind> ablePetKinds = petMan.findAblePetKindLsit(sitterId);
			request.setAttribute("ablePetKinds", ablePetKinds);

			// 로그인한 유저의 반려동물 리스트
			List<Pet> userPets = petMan.findPetListOfMember(userId);
			request.setAttribute("userPets", userPets);

			// 로그인한 유저의 반려동물 별 사진 리스트
			for (Pet pet : userPets) {
				List<String> imgList = petMan.findPetAttachments(userId);
				pet.setImages(imgList);
			}
			
			// 가능 서비스
			List<Service> ableService = srvcMan.findProvideServiceList(sitterId);
			request.setAttribute("ableService", ableService);

			// 가격 계산
			String[] fromDate = request.getParameter("startDate").split("-");
			String[] toDate = request.getParameter("endDate").split("-");

			String fDate = (fromDate[0] + fromDate[1] + fromDate[2]);
			String tDate = (toDate[0] + toDate[1] + toDate[2]);
			int dateLength = Integer.parseInt(tDate) - Integer.parseInt(fDate);
			
			int totalPrice = 0;
			String[] price = petSitterInfo.getCalculatedPrice().split(",");
			int nightPrice = Integer.parseInt(price[0]);
			int dayPrice = Integer.parseInt(price[1]);
			if (dateLength == 0)
				totalPrice = dayPrice;
			else
				totalPrice = dateLength * nightPrice;
			
			request.setAttribute("totalPrice", totalPrice);
			
			return "/reservation/reservationForm.jsp";
		}

		// 예약 처리
		try {
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
		} catch (UnavailableReservationTimeException e) {
			request.setAttribute("reservationFailed", true);
			request.setAttribute("exception", e);

			return "redirect:/reservation/Reserve";
		}
	}
}
