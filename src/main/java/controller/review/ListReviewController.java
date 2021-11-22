package controller.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.CareDetails;
import model.dto.Member;
import model.dto.Review;
import model.service.MemberManager;
import model.service.ReviewManager;

public class ListReviewController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ReviewManager reviewMan = ReviewManager.getInstance();
		MemberManager memMan = MemberManager.getInstance();
		
		// session에 id정보가 없는지 확인
		if (!UserSessionUtils.hasLogined(session)) {
			// 로그인 상태가 아니면 방문자인 상태를 전달
			request.setAttribute("isNotLogined", true);
		}	
		
		List<Review> reviews = reviewMan.findReviewList();
		
		if (reviews != null ) {
			for (Review review : reviews) {
				Member sitterMemInfo = memMan.findMember(review.getCareInfo().getSitter().getSitter().getId());
				String[] address = sitterMemInfo.getAddress().split(" ");
				System.out.println(Arrays.toString(address));
				String city = null;
				for (int j = 0; j < address.length; j++) {
					if (address[j].matches("(.*)로")) {
						city = address[j].substring(0, address[j].length() - 1);
						//System.out.println(city);
					}
				}
				review.getCareInfo().getSitter().getSitter().setAddress(city);
				
				List<String> imgList = reviewMan.findReviewAttachments(review.getCareInfo().getCompanion().getId(), review.getCareInfo().getId());
				review.setImages(imgList);
			}
		}
		request.setAttribute("reviews", reviews);
		
		return "/review/reviewList.jsp";
	}
}
