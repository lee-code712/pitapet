package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dto.Care;
import model.dao.CareDAO;

public class CareManager {
	private static CareManager careMan = new CareManager();
	private CareDAO careDAO;
	
	private CareManager() {
		try {
			careDAO = new CareDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static CareManager getInstance() {
		return careMan;
	}
	
	public List<Care> findCareSchedules(String memberId) throws SQLException {
		return careDAO.findCareSchedules(memberId);
	}

}
