package controller.reservation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.service.ReservationManager;

public class ViewReservationController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		
		ReservationManager reservationMan = ReservationManager.getInstance();
		
		String careId = (String) request.getParameter("careId");
		
		Care care = reservationMan.findReservation(Integer.parseInt(careId));
		
		request.setAttribute("reservationInfo", care);		
		return "/reservation/reservationView.jsp";
	}

}
