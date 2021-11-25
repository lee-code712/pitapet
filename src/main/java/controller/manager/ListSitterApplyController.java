package controller.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.PetSitterApplication;
import model.service.PetSitterApplicationManager;

public class ListSitterApplyController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	PetSitterApplicationManager psApplicationMan = PetSitterApplicationManager.getInstance();
    	
    	List<PetSitterApplication> applicantList = psApplicationMan.findApplicationList();
    	request.setAttribute("applicantList", applicantList);
    	
    	return "/manager/sitterApplyList.jsp";
    }
}
