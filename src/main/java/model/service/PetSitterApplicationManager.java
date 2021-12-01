package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dto.PetKind;
import model.dto.PetSitter;
import model.dto.PetSitterApplication;
import model.dao.MemberDAO;
import model.dao.PetDAO;
import model.dao.PetSitterApplicationDAO;

public class PetSitterApplicationManager {
	private static PetSitterApplicationManager applycationMan = new PetSitterApplicationManager();
	private PetSitterApplicationDAO petSitterApplicationDAO;
	private MemberDAO memberDAO;
	
	private PetSitterApplicationManager() {
		try {
			petSitterApplicationDAO = new PetSitterApplicationDAO();
			memberDAO = new MemberDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static PetSitterApplicationManager getInstance() {
		return applycationMan;
	}
	
	public String getApprovalStatus(String memberId) throws SQLException {
		return petSitterApplicationDAO.getApprovalStatus(memberId);
	}
	
	public List<PetSitterApplication> findApplicationList() throws SQLException {
		return petSitterApplicationDAO.findApplicationList();
	}
	
	public PetSitterApplication findApplication(String applyId) throws SQLException {
		PetSitterApplication application = petSitterApplicationDAO.findApplication(applyId);
		String[] address = application.getApplicant().getAddress().split(" ");
		String city = null;
		for (int j = 0; j < address.length; j++) {
			if (address[j].matches("(.*)로")) {
				city = address[j].substring(0, address[j].length() - 1);
			}
		}
		application.getApplicant().setAddress(city);
		
		List<String> imgList = petSitterApplicationDAO.findApplyAttachments(application.getApplicant().getId());
		application.setImages(imgList);
		
		return application;
	}
	
	public int applyStatus(String applyId) throws SQLException {
		return petSitterApplicationDAO.applyStatus(applyId);
	}
	
	public int refuseStatus(String applyId) throws SQLException {
		return petSitterApplicationDAO.refuseStatus(applyId);
	}
	
	public int createBasicSitter(String memberId, String applyId) throws SQLException {
	      return petSitterApplicationDAO.createBasicSitter(memberId, applyId);
	}
}
