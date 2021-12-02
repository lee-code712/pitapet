package controller.pet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PetManager;

public class DeletePetController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String petId = request.getParameter("petId");
		PetManager petMan = PetManager.getInstance();
		
		int delete = petMan.remove(petId);
		if (delete > 0) {
			return "redirect:/pet/listPet";
		}
		
		return "redirect:/pet/listPet?deleteFailed=true";
	}

}
