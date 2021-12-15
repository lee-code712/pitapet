package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.dao.ServiceDAO;
import model.dto.Care;
import model.dto.CareDetails;
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

	/* 제공받을 서비스 맵으로 반환 */
	public Map<String, Service> getServiceMap(List<Service> ableService) throws SQLException {
		Iterator<Service> iterator = ableService.iterator();
		Map<String, Service> serviceMap = new HashMap<String, Service>();
		
		while (iterator.hasNext()) {
			Service service = iterator.next();
			serviceMap.put(service.getId(), service);
		}
		
		return serviceMap;
	}
	
	public String createReceiveService(int careId, String petId, String serviceId) throws SQLException {
		   return serviceDAO.createReceiveService(careId, petId, serviceId);
	}
	
	public int deleteReceiveService(int careId) throws SQLException {
		return serviceDAO.deleteReceiveService(careId);
	}

	public List<CareDetails> findReceiveServiceByCareId(Care care) throws SQLException {
		return serviceDAO.findReceiveServiceByCareId(care);
	}
	
	public Service findServiceInfo(String serviceId) throws SQLException {
		return serviceDAO.findServiceInfo(serviceId);
	}
	
	public int countReceiveServiceByPetId(String petId) throws SQLException {
		return serviceDAO.countReceiveServiceByPetId(petId);
	}
	
	public ArrayList<Service> findProvideServiceList(String sitterId) throws SQLException {
		return serviceDAO.findProvideServiceList(sitterId);
	}
	
	public List<Service> findAllServiceList() throws SQLException {
		return serviceDAO.findAllServiceList();
	}
}
