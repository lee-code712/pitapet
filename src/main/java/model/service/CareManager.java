package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.CareDetails;
import model.dto.Pet;
import model.dao.CareDAO;
import model.dao.PetDAO;
import model.dao.ReviewDAO;

public class CareManager {
	private static CareManager careMan = new CareManager();
	private CareDAO careDAO;
	private PetDAO petDAO;
	private ReviewDAO reviewDAO;
	
	private CareManager() {
		try {
			careDAO = new CareDAO();
			reviewDAO = new ReviewDAO();
			petDAO = new PetDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static CareManager getInstance() {
		return careMan;
	}
	
	public Map<Integer, Care> getCareScheduleMap(String memberId) throws SQLException {
		List<Care> careSchedules = careDAO.findCareSchedules(memberId);
		if(careSchedules != null) {
			Iterator<Care> iterator = careSchedules.iterator();
			Map<Integer, Care> scheduleMap = new HashMap<Integer, Care>();
			while(iterator.hasNext()) {
				Care care = iterator.next();
				ArrayList<Pet> myPetList = petDAO.findCarePetList(care.getId());
				ArrayList<CareDetails> careDetailList = new ArrayList<CareDetails>();
				if (myPetList != null) {
					for (Pet pet : myPetList) {
						careDetailList.add(new CareDetails(pet));
					}
				}
				care.setCareList(careDetailList);
				scheduleMap.put(care.getId(), care);
			}
			return scheduleMap;
		}
		return null;
	}
	
	public List<Care> findCareSchedules(String memberId) throws SQLException {
		return careDAO.findCareSchedules(memberId);
	}
	
	public List<Care> findCareSchedulesOfSitter(String sitterId) throws SQLException {
		return careDAO.findCareSchedulesOfSitter(sitterId);
	}

	public List<Care> findCareOfDoNotReview(String memberId, String sitterId) throws SQLException {
		List<Care> careList = careDAO.findCareList(memberId, sitterId);
		List<Care> needReviewCareList = new ArrayList<Care>();
		if (careList != null) {
			for (Care care : careList) {
				boolean isExist = reviewDAO.isExistReview(care.getId());
				if (!isExist)
					needReviewCareList.add(care);
			}
		}
		return needReviewCareList;
	}
	
	public int deleteCare(int careId) throws SQLException {
		return careDAO.deleteCare(careId);
	}
}
