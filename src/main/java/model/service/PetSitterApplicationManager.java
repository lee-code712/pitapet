package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dto.PetSitterApplication;
import model.dao.PetSitterApplicationDAO;

public class PetSitterApplicationManager {
	private static PetSitterApplicationManager applycationMan = new PetSitterApplicationManager();
	private PetSitterApplicationDAO petSitterApplicationDAO;
	
	private PetSitterApplicationManager() {
		try {
			petSitterApplicationDAO = new PetSitterApplicationDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static PetSitterApplicationManager getInstance() {
		return applycationMan;
	}
	
	/* 회원의 돌보미 지원 상태 반환 */
	public String getApprovalStatus(String memberId) throws SQLException {
		return petSitterApplicationDAO.getApprovalStatus(memberId);
	}
	
	/* 돌보미 지원자 리스트 반환 */
	public List<PetSitterApplication> findApplicationList() throws SQLException {
		return petSitterApplicationDAO.findApplicationList();
	}
	
	/* 특정  돌보미 지원자 정보 반환 */
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
	
	public boolean updateStatus(String applyId, String memberId, String status) throws SQLException {
		int count = 0;
		
		if(status.equals("approval"))
			count = petSitterApplicationDAO.applyStatus(applyId);
	
		if(status.equals("refuse"))
			count = petSitterApplicationDAO.refuseStatus(applyId);
		
		if(count == 0)
			return false;
		return true;
	}
	
//	public int createBasicSitter(String memberId, String applyId) throws SQLException {
//	      return petSitterApplicationDAO.createBasicSitter(memberId, applyId);
//	}
}
