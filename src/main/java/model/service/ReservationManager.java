package model.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.dao.ReservationDAO;
import model.dto.Care;
import model.dto.Member;
import model.dto.PetSitter;

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
}
