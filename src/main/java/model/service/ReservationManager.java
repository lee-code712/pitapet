package model.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.ReservationDAO;
import model.dto.Care;
import model.dto.CareDetails;
import model.dto.Member;
import model.dto.Pet;
import model.dto.PetSitter;
import model.dto.Service;

public class ReservationManager {
	private static ReservationManager reservationMan = new ReservationManager();
	private ReservationDAO reservationDAO;

	private ReservationManager() {
		try {
			reservationDAO = new ReservationDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ReservationManager getInstance() {
		return reservationMan;
	}

	public ReservationDAO getReservationDAO() {
		return this.reservationDAO;
	}

	public Care createCare(int totalPrice, String fromDate, String toDate, String cautionText, String memberId, String sitterId) throws SQLException, ParseException {
		toDate = toDate + " 00:00:01";
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// String 타입을 Date 타입으로 변환
		Date formatDate = dtFormat.parse(toDate);
		// Date타입의 변수를 새롭게 지정한 포맷으로 변환
		toDate = newDtFormat.format(formatDate);

		Care care = new Care(fromDate, toDate, totalPrice, cautionText,
				"X", null, new Member(memberId), new PetSitter(new Member(sitterId)));
		
		care.setId(reservationDAO.createCare(care));
		
		return care;
	}
	
	public Care findReservation(int careId) throws SQLException {
		// care 정보
		Care care = reservationDAO.findReservation(careId);
		// care 정보 중 petSitter 정보
		MemberManager memberMan = MemberManager.getInstance();
		Member member = memberMan.findMember(care.getSitter().getSitter().getId());
		PetSitterManager petSitterMan = PetSitterManager.getInstance();
		PetSitter petSitter = petSitterMan.findPetSitter(care.getSitter().getSitter().getId());
		petSitter.setSitter(member);
		care.setSitter(petSitter);
		
		// receive_service 정보
		ServiceManager serviceMan = ServiceManager.getInstance();
		List<CareDetails> careDetails = serviceMan.findReceiveServiceByCareId(care);
		care.setCareList(careDetails);

		// careDetails 정보 중 pet 정보, service 정보
		PetManager petMan = PetManager.getInstance();
		for (int i = 0; i < careDetails.size(); i++) {
			CareDetails cd = careDetails.get(i);
			Pet pet = petMan.findPetInfo(cd.getCarePet().getId());
			Service service = serviceMan.findServiceInfo(cd.getServiceInfo().getId());

			careDetails.get(i).setCarePet(pet);
			careDetails.get(i).setServiceInfo(service);
		}
		care.setCareList(careDetails);
		
		// care_checklist 정보 - care_checklist 정보는 돌봄일지가 1개 이상일 때 생성되므로
		if (!care.getStatus().equals("X")) { // 돌봄 진행, 돌봄 완료인 경우에만 찾음
			CareManager careMan = CareManager.getInstance();
			for (int i = 0; i < careDetails.size(); i++) {
				CareDetails cd = careDetails.get(i);
				String check = careMan.getCheckInfo(cd.getId());
				if (check != null) {
					careDetails.get(i).setCheck(check);
				}
 			}
			care.setCareList(careDetails);
		}
		
		return care;
	}
}
