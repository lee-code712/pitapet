package model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.dao.mybatis.ServiceDAO;
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
	
	/* 모든 서비스 타입 리스트 반환 */
	public List<Service> findAllServiceList() throws SQLException {
		return serviceDAO.findAllServiceList();
	}
	
	/* 특정 돌보미의 제공 가능 서비스 리스트 반환 */
	public List<Service> findProvideServiceList(String sitterId) throws SQLException {
		return serviceDAO.findProvideServiceList(sitterId);
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
	
	/* 특정 돌봄 내역에 해당하는 제공 서비스 리스트 반환 */
	public List<CareDetails> findReceiveServiceList(int careId) throws SQLException {
		return serviceDAO.findReceiveServiceList(careId);
	}
	
	/* 특정 돌봄 내역에 해당하는 제공 서비스 리스트 삭제 */
	public int deleteReceiveService(int careId) throws SQLException {
		return serviceDAO.deleteReceiveService(careId);
	}

	/* 특정 반려동물에 대해 제공 서비스가 있는지 확인 */
	public int countReceiveServiceByPetId(String petId) throws SQLException {
		return serviceDAO.countReceiveServiceByPetId(petId);
	}
	
}
