package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.mainpage.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
    	// main
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/mainpage", new MainPageController());
        // member
        mappings.put("/member/login/form", new ForwardController("/member/loginForm.jsp"));
        mappings.put("/member/register/form", new ForwardController("/member/registerForm.jsp"));
        mappings.put("/member/memberMyPage", new ForwardController("/member/memberMyPage.jsp"));
        mappings.put("/member/updateMember", new ForwardController("/member/memberUpdateForm.jsp"));
        mappings.put("/member/applySitter", new ForwardController("/member/sitterApplyForm.jsp"));
        mappings.put("/member/updateSitterApply", new ForwardController("/member/sitterApplyUpdateForm.jsp"));
        // pet
        mappings.put("/pet/addPet", new ForwardController("/member/petAddForm.jsp"));
        mappings.put("/pet/viewPet", new ForwardController("/member/petView.jsp"));
        // sitter
        mappings.put("/petSitter/sitterMyPage", new ForwardController("/sitter/sitterMyPage"));
        mappings.put("/petSitter/registerSitter", new ForwardController("/sitter/sitterRegisterForm.jsp"));
        mappings.put("/petSitter/updateSitter", new ForwardController("/sitter/sitterUpdateForm.jsp"));
        mappings.put("/petSitter/viewSitter", new ForwardController("/sitter/sitterView.jsp"));
        // reservation
        mappings.put("/reservation/listSitter", new ForwardController("/reservation/sitterList.jsp"));
        mappings.put("/reservation/viewSitterDetail", new ForwardController("/reservation/sitterDetailView.jsp"));
        mappings.put("/reservation/Reserve", new ForwardController("/reservation/reservationForm.jsp"));
        // care
        mappings.put("/care/listCareDiary", new ForwardController("/care/careDiary.jsp"));
        mappings.put("/care/recordCare", new ForwardController("/care/careRecordForm.jsp"));
        mappings.put("/care/updateCareDiary", new ForwardController("/care/careDiaryUpdateForm.jsp"));
        // review
        mappings.put("/review/listReview", new ForwardController("/review/reviewList.jsp"));
        // like
        mappings.put("/like/listLike", new ForwardController("/like/likeList.jsp"));
        // manager
        mappings.put("/manager/listSitterApply", new ForwardController("/manager/sitterApplyList.jsp"));
        mappings.put("/manager/viewApply", new ForwardController("/manager/applicationView.jsp"));
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
