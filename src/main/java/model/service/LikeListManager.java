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
	   
	   //ListSitterController에서 이 메소드 쓰고 있어서 삭제 안하고, 위 메소드 추가함
	   public List<LikeList> findLikeListOfMember(String memberId) throws SQLException {
	         return likeListDAO.findLikeListOfMember(memberId);
	      }

}
