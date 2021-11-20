package model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.PetDAO;
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
		return petDAO.findPetListOfMember(memberId);
	}
	
	public ArrayList<PetKind> findAblePetKindLsit(String sitterId) throws SQLException {
		return petDAO.findAblePetKindList(sitterId);
	}
	
	public ArrayList<String> findPetAttachments(String memberId, String petId) throws SQLException {
		return (ArrayList<String>) petDAO.findPetAttachments(memberId, petId);
	}
}
