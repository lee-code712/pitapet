package controller.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PetSitterApplicationManager;
import model.service.PetSitterManager;

public class UpdateStatusController implements Controller{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    	PetSitterApplicationManager psApplicationMan = PetSitterApplicationManager.getInstance();
    	PetSitterManager sitterMan = PetSitterManager.getInstance();
    	
    	String applyId = (String) request.getParameter("applyId");
    	String memberId = (String) request.getParameter("memberId");
    	String status = (String) request.getParameter("status");
    	
    	boolean updateSuccess = psApplicationMan.updateStatus(applyId, memberId, status);
    	
    	if(updateSuccess) {
    		if(status.equals("approval")) {
    			// int createResult = psApplicationMan.createBasicSitter(memberId, applyId); // 돌보미 등록시 PetSitter객체가 생성됨
        		//if(createResult > 0) {
        			int upgradeResult = sitterMan.upgradeSitter(memberId);
        			if(upgradeResult > 0) {
        				return "redirect:/manager/listSitterApply";
        			} else {
        				return "redirect:/manager/viewApply";
        			}
        		//}
//        		else {
//        			request.setAttribute("updateFailed", true);
//        			return "redirect:/manager/viewApply";
//        		}
    		} else {
    			return "redirect:/manager/listSitterApply";
    		}
    		
    	} else {
    		request.setAttribute("updateFailed", true);
    		return "redirect:/manager/viewApply";
    	}
    }
}
