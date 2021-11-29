package controller.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PetSitterApplicationManager;

public class ApprovalSitterController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	PetSitterApplicationManager psApplicationMan = PetSitterApplicationManager.getInstance();
    	String applyId = (String) request.getParameter("applyId");
    	int result = psApplicationMan.applyStatus(applyId);
    	if (result > 0) {
    		return "redirect:/manager/listSitterApply";
    	} else {
    		request.setAttribute("updateFailed", true);
    		return "redirect:/manager/viewApply";
    	}
    }
}
