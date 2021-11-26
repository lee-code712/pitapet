package model.service;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import model.dao.PetDAO;
import model.dto.Care;
import model.dto.CareDetails;
import model.dto.Member;
import model.dto.Pet;
import model.dto.PetKind;

public class PetManager {
	private static PetManager petMan = new PetManager();
	private PetDAO petDAO;

	private PetManager() {
		try {
			petDAO = new PetDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PetManager getInstance() {
		return petMan;
	}

	public ArrayList<Pet> findPetListOfMember(String memberId) throws SQLException {
		ArrayList<Pet> userPets = petDAO.findPetListOfMember(memberId);

		// 로그인한 유저의 반려동물 별 사진 리스트
		for (Pet pet : userPets) {
			List<String> imgList = petMan.findPetAttachments(memberId, pet.getId());
			pet.setImages(imgList);
		}

		return userPets;
	}

	public ArrayList<PetKind> findAblePetKindLsit(String sitterId) throws SQLException {
		return petDAO.findAblePetKindList(sitterId);
	}

	public ArrayList<String> findPetAttachments(String memberId, String petId) throws SQLException {
		return (ArrayList<String>) petDAO.findPetAttachments(memberId, petId);
	}

	public ArrayList<CareDetails> findCarePetList(Integer careId) throws SQLException {
		ArrayList<Pet> petList = petDAO.findCarePetList(careId);
		ArrayList<CareDetails> careDetailList = new ArrayList<CareDetails>();
		if (petList != null) {
			for (Pet pet : petList) {
				careDetailList.add(new CareDetails(pet));
				System.out.println(pet.getName());
			}
		}
		return careDetailList;
	}

	public Map<String, Pet> getPetMapOfMember(List<Pet> userPets) throws SQLException {
		Iterator<Pet> iterator = userPets.iterator();
		Map<String, Pet> petMap = new HashMap<String, Pet>();
		while (iterator.hasNext()) {
			Pet pet = iterator.next();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
			String[] today = sdf.format(new Date()).split("-");
			String[] birth = pet.getBirth().split(" ")[0].split("-");
			int year = Integer.valueOf(today[0]) - Integer.valueOf(birth[0]) + 1;
			pet.setBirth(String.valueOf(year));

			petMap.put(pet.getId(), pet);
		}
		return petMap;
	}
}
