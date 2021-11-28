package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.LikeListDAO;
import model.dao.PetSitterDAO;
import model.dto.LikeList;
import model.dto.PetSitter;

public class LikeListManager {
	private static LikeListManager likelistMan = new LikeListManager();
	private LikeListDAO likeListDAO;
	private PetSitterDAO petSitterDAO;

	private LikeListManager() {
		try {
			likeListDAO = new LikeListDAO();
			petSitterDAO = new PetSitterDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static LikeListManager getInstance() {
		return likelistMan;
	}

	/* 특정 회원의 likeList에 포함되는 돌보미 정보 반화 */
	public List<PetSitter> findLikeSitterOfMember(String memberId) throws SQLException {
		List<LikeList> mLikeLists = likeListDAO.findLikeListOfMember(memberId);
		List<PetSitter> likeSitterLists = new ArrayList<PetSitter>();

		if (mLikeLists != null) {
			for (LikeList likeList : mLikeLists) {
				PetSitter likeSitter = petSitterDAO.findPetSitter(likeList.getLikeSitter().getSitter().getId());
				String[] address = likeSitter.getSitter().getAddress().split(" ");
				String city = null;
				for (int j = 0; j < address.length; j++) {
					if (address[j].matches("(.*)로")) {
						city = address[j].substring(0, address[j].length() - 1);
					}
				}
				likeSitter.getSitter().setAddress(city);
				likeSitterLists.add(likeSitter);
			}
		}
		return likeSitterLists;
	}

	/* 특정 회원의 likeList 반환 */
	public List<LikeList> findLikeListOfMember(String memberId) throws SQLException {
		return likeListDAO.findLikeListOfMember(memberId);
	}

	/* like 추가 */
	public boolean add(String memberId, String sitterId) throws SQLException {
		int count = likeListDAO.add(memberId, sitterId);
		if (count == 0)
			return false;
		return true;
	}
	
	/* like 삭제 */
	public boolean remove(String memberId, String sitterId) throws SQLException {
		int count = likeListDAO.remove(memberId, sitterId);
		if (count == 0)
			return false;
		return true;
	}
}
