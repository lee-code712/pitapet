package controller.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.PetSitterApplication;
import model.service.PetSitterApplicationManager;

public class ViewApplyController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	PetSitterApplicationManager psApplicationMan = PetSitterApplicationManager.getInstance();
    	
    	String applyId = (String) request.getParameter("applyId");
    	
    	PetSitterApplication applicantDetail = psApplicationMan.findApplication(applyId);
    	request.setAttribute("applicantDetail", applicantDetail);
    	
    	return "/manager/applicationView.jsp";
    }
}
