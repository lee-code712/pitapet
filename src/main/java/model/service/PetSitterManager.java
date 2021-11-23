package model.service;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/* 페이지에 출력될 펫시터 리스트 및 페이지 정보 반환 */
	public Map<Integer, List<PetSitter>> getPetSittersOfPage(int currentPage) throws SQLException {
		ArrayList<PetSitter> sitterList = sitterDAO.findPetSitterList();
		
		if (sitterList != null) {
			int rpp = 2;
			int totalSitter = sitterList.size();
			int totalPage;
			if (totalSitter % 2 == 0)
				totalPage = totalSitter / rpp;
			else
				totalPage = totalSitter / rpp + 1;
			
			int startIndex = 0;
			for (int i = 1; i < currentPage; i++)
				startIndex += rpp;
			int endIndex;
			if (totalSitter - startIndex == 0)
				endIndex = startIndex;
			else if (totalSitter - startIndex == 1)
				endIndex = startIndex + 1;
			else
				endIndex = startIndex + rpp;
			
			List<PetSitter> pagingSitters = sitterList.subList(startIndex, endIndex);
			for (PetSitter sitter : pagingSitters) {
				String[] address = sitter.getSitter().getAddress().split(" ");
				String city = null;
				for (int j = 0; j < address.length; j++) {
					if (address[j].matches("(.*)로"))
						city = address[j].substring(0, address[j].length() - 1);
				}
				sitter.getSitter().setAddress(city);
			}		
			Map<Integer, List<PetSitter>> sitterMap = new HashMap<Integer, List<PetSitter>>();
			sitterMap.put(totalPage, pagingSitters);
			
			return sitterMap;
		}
		return null;
	}

	public ArrayList<PetSitter> findPetSitterList() throws SQLException {
		return sitterDAO.findPetSitterList();
	}

	public PetSitter findPetSitter(String sitter_id) throws SQLException {
		return sitterDAO.findPetSitter(sitter_id);
	}

}
