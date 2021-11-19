package model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.ServiceDAO;
import model.dto.Care;
import model.dto.Service;

public class ServiceManager {
	private static ServiceManager serviceMan = new ServiceManager();
	private ServiceDAO serviceDAO;

	private ServiceManager() {
		try {
			serviceDAO = new ServiceDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ServiceManager getInstance() {
		return serviceMan;
	}

	public ArrayList<Service> findProvideServiceList(String sitterId) throws SQLException {
		return serviceDAO.findProvideServiceList(sitterId);
	}
	
	public int createRecvService(Care care) throws SQLException {
		   return serviceDAO.createRecvService(care);
	}
}
