package model.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.dto.Care;
import model.dto.CareDetails;
import model.dto.Member;
import model.dto.Pet;
import model.dto.PetSitter;
import model.dto.Service;
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
	
	/* 돌봄 스케줄을 맵으로 저장하여 반환 */
	public Map<Integer, Care> getCareScheduleMap(Member member) throws SQLException {
		List<Care> careSchedules = new ArrayList<>();
		if (member.getIdentity().equals("C")) {
			careSchedules = careDAO.findCareSchedules(member.getId());
		}
		else {
			careSchedules = careDAO.findCareSchedules(member.getId());
			List<Care> careSchedulesOfSitter = careDAO.findCareSchedulesOfSitter(member.getId());
			if (careSchedulesOfSitter != null)
				careSchedules.addAll(careSchedulesOfSitter);
			else
				careSchedules = careSchedulesOfSitter;
		}
		
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
	
	/* 돌봄 불가능한 날짜들을 맵으로 저장하여 반환 */
	public Map<String, String> getDisableDays(String memberId, PetSitter sitter) throws SQLException {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, String> scheduleMap = new HashMap<>();
		String unparsedAbleDay = sitter.getAbleDate();
		String parsedAbleDay = Integer.toBinaryString(unparsedAbleDay.charAt(0));
		String[] ableDateArr = parsedAbleDay.split("");
		for (int i = 0; i < 32; i++) {
			int dayNum = cal.get(Calendar.DAY_OF_WEEK);
			int idx = 0;
			if (dayNum == 1)
				idx = 6;
			else if (dayNum == 2)
				idx = 0;
			else
				idx = dayNum - 2;
			if(ableDateArr[idx].equals("0")) {
				String strDate = simpleDateFormat.format(cal.getTime());
				scheduleMap.put(strDate, strDate);
			}
			cal.add(Calendar.DATE, 1);
		}
		
		List<Care> careSchedules = careDAO.findCareSchedules(memberId);
		if (careSchedules != null) {
			List<Care> careSchedulesOfSitter = careDAO.findCareSchedulesOfSitter(sitter.getSitter().getId());
			if (careSchedulesOfSitter != null) {
				careSchedules.addAll(careSchedulesOfSitter);
			}
			
			Iterator<Care> iterator = careSchedules.iterator();
			while (iterator.hasNext()) {
				Care care = iterator.next();
				cal.setTime(date);
				scheduleMap.put(care.getStartDate(), care.getEndDate());
			}
			return scheduleMap;
		}
		return null; // 왜 daad code??
	}
	
	/* 후기를 작성해야 하는 돌봄내역 리스트 반환 */
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
	
	public List<Care> findCareSchedules(String memberId) throws SQLException {
		return careDAO.findCareSchedules(memberId);
	}
	
	public List<Care> findCareSchedulesOfSitter(String sitterId) throws SQLException {
		return careDAO.findCareSchedulesOfSitter(sitterId);
	}
}
