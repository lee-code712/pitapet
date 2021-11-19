package controller.reservation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.Member;
import model.dto.Pet;
import model.dto.PetSitter;
import model.service.CareManager;
import model.service.MemberManager;
import model.service.PetManager;
import model.service.PetSitterManager;
import model.service.ReservationManager;
import model.service.ServiceManager;
import model.service.exception.UnavailableReservationTimeException;

public class ReserveController {
   
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession session = request.getSession();
      
      String userId = UserSessionUtils.getLoginUserId(session);
      
      if (request.getMethod().equals("GET")) {
         // 화면 뿌려주고
         /*
          * 돌보미 이름 - member 테이블
          * 보호자 반려동물 리스트 - pet 테이블
          * 돌보미 제공 가능 서비스 리스트 - provide_service 테이블
          * 돌보미 돌봄 가능 반려동물 리스트 - available_pet_kind 테이블
          */
         
         String sitterId = (String) request.getAttribute("sitterId");
         
         PetSitter petSitterInfo = PetSitterManager.findPetSitter(sitterId);
         request.setAttribute("petSitterInfo", petSitterInfo);
         
         List<Pet> ablePets = PetManager.findPetListOfMember(sitterId);
         request.setAttribute("ablePets", ablePets);
         
         List<Integer> ableDay = CareManager.findCareSchedules(sitterId);
         request.setAttribute("ableDay", ableDay);
         
         List<Pet> myPets = PetManager.findPetListOfMember(userId);
         request.setAttribute("myPets", myPets);
         
         // 가격 조회 추가
         
         return "/reservation/reservationForm.jsp";
      })
      
      // 예약 처리
      try {
         PetSitterManager petsitterMan = PetSitterManager.getInstance();
         ReservationManager reservationMan = ReservationManager.getInstance();
         ServiceManager serviceMan = ServiceManager.getInstance();
         
         PetSitter petsitter = (PetSitter) request.getAttribute("petSitterInfo");
//         String sitterId = petsitter.getSitter().getId();
            
         String[] fromdate = request.getParameter("fromDate").split("-");
         String[] todate = request.getParameter("toDate").split("-");
         String fDate = (fromdate[0] + fromdate[1] + fromdate[2]);
         String tDate = (todate[0] + todate[1] + todate[2]);
         int dateLength = Integer.parseInt(tDate) - Integer.parseInt(fDate);
         int money = 0;
         String[] payMoney = petsitter.getCalculatedPrice().split(",");
         int nightMoney = Integer.parseInt(payMoney[0]);
         int dayMoney = Integer.parseInt(payMoney[1]);
         if(dateLength == 0)
            money = dayMoney;
         else
            money = dateLength * nightMoney;
               
         Care care = new Care(
               request.getParameter("fromDate"),
               request.getParameter("toDate"),
               money,
               request.getParameter("cautionText"),
               null,
               null,
               new Member(userId),
               new PetSitter(
                     new Member("M0000006"))
            );
            return "redirect:/member/memberMyPage";         
      } catch (UnavailableReservationTimeException e) {
            request.setAttribute("reservationFailed", true);
         request.setAttribute("exception", e);
            
         return  "redirect:/reservation/Reserve";         
      } 
   }
}
