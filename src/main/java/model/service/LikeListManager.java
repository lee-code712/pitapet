package model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.LikeListDAO;
import model.dto.LikeList;

public class LikeListManager {
	   private static LikeListManager likelistMan = new LikeListManager();
	   private LikeListDAO likeListDAO;
	   
	   private LikeListManager() {
	      try {
	    	  likeListDAO = new LikeListDAO();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }         
	   }
	   
	   public static LikeListManager getInstance() {
		      return likelistMan;
	   }
	   
	   public ArrayList<LikeList> findLikeListOfMember(String memberId) throws SQLException {
		   return likeListDAO.findLikeListOfMember(memberId);
	   }
}
