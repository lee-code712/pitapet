package model.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
	
	/* 전체 반려동물 종 리스트 검색 */
	public ArrayList<PetKind> findAllPetKindList() throws SQLException {
		return petDAO.findAllPetKindList();
	}
	
	/* 전체 돌보미들의 돌봄 가능 종 리스트 반환 */
	public ArrayList<PetKind> findAllAblePetKindList() throws SQLException {
		return petDAO.findAllAblePetKindList();
	}

	public ArrayList<PetKind> findAblePetKindList(String sitterId) throws SQLException {
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

	public Map<String, Pet> getAbleCarePetMap(String memberId, String sitterId) throws SQLException {
		ArrayList<Pet> userPets = (ArrayList<Pet>) petDAO.findAbleCarePetList(memberId, sitterId);
		System.out.println(userPets);
		if (userPets != null) {
			for (Pet pet : userPets) {
				List<String> imgList = petMan.findPetAttachments(memberId, pet.getId());
				pet.setImages(imgList);
			}
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
		return null;
	}
	
	public Pet findPetInfo(String petId) throws SQLException {
		return petDAO.findPetInfo(petId);
	}
	
	public Pet addPet(String memberId, Pet pet) {
		/*
		 * PetKind petKind = petMan.findPetKindInfo(pet.getKind().getId());
		 * pet.setKind(petKind);
		 */
		
		return petDAO.addPet(memberId, pet);
	}
	
	public PetKind findPetKindInfo(String kindId) {
		return petDAO.findPetKindInfo(kindId);
	}
	
}	
