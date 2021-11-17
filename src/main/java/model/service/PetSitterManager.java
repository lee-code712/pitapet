package model.service;

import java.sql.SQLException;

import java.util.ArrayList;

import model.dao.MemberDAO;
import model.dao.PetSitterDAO;
import model.dto.PetSitter;

public class PetSitterManager {
	   private static PetSitterManager sitterMan = new PetSitterManager();
	   private PetSitterDAO sitterDAO;
	   
	   private PetSitterManager() {
		   try {
			   sitterDAO = new PetSitterDAO();
		   } catch (Exception e) {
			   e.printStackTrace();
		   }         
	   }
	   
	   public static PetSitterManager getInstance() {
		   return sitterMan;
	   }
	   
	   public ArrayList<PetSitter> findPetSitterList() throws SQLException {
		  return sitterDAO.findPetSitterList();
	   }
}
