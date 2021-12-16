package controller.care;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.SizeLimitExceededException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.CareDetails;
import model.dto.Review;
import model.service.ServiceManager;

public class RecordCareController implements Controller {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ServiceManager serviceMan = ServiceManager.getInstance();
		
		// 돌봄일지 작성 form 이동
		if (request.getMethod().equals("GET")) {
			// session에 id정보가 없으면 mainpage 호출 리다이렉션
			if(!UserSessionUtils.hasLogined(session)) {
				return "redirect:/mainpage";
			}
			
			int careId = Integer.parseInt(request.getParameter("careId"));
			List<CareDetails> checkList = serviceMan.findReceiveServiceList(careId);
			request.setAttribute("checkList", checkList);
			
			List<String> carePetNameList = new ArrayList<String>();
			for (CareDetails careDetail : checkList)
				carePetNameList.add(careDetail.getCarePet().getName());
			Set<String> setPets = new HashSet<String>(carePetNameList);
			carePetNameList = new ArrayList<String>(setPets);
			request.setAttribute("carePets", carePetNameList);
			
			return "/care/careRecordForm.jsp";
		}
		
		// 일지 추가 처리
		
		return "redirect:/care/listCareDiary";
	}
}
