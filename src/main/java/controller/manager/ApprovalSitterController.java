package controller.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.PetSitter;
import model.service.PetSitterApplicationManager;
import model.service.PetSitterManager;

public class ApprovalSitterController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	PetSitterApplicationManager psApplicationMan = PetSitterApplicationManager.getInstance();
    	PetSitterManager sitterMan = PetSitterManager.getInstance();
    	
    	String applyId = (String) request.getParameter("applyId");
    	String memberId = (String) request.getParameter("memberId");
    	
    	int result = psApplicationMan.applyStatus(applyId);
    	if (result > 0) {
    		int createResult = psApplicationMan.createBasicSitter(memberId, applyId);
    		if(createResult > 0) {
    			int upgradeResult = sitterMan.upgradeSitter(memberId);
    			if(upgradeResult > 0) {
    				return "redirect:/manager/listSitterApply";
    			} else {
    				return "redirect:/manager/viewApply";
    			}
    		}
    		else {
    			request.setAttribute("updateFailed", true);
    			return "redirect:/manager/viewApply";
    		}
    	} else {
    		request.setAttribute("updateFailed", true);
    		return "redirect:/manager/viewApply";
    	}
    }
}
