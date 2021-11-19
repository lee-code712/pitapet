package model.service;

import model.dao.ReservationDAO;

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
}
