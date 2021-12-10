package model.dao.mybatis.mapper;

import java.util.List;

import model.dto.Care;

public interface CareMapper {

	/* 보호자 및 특정 돌보미의 돌봄 스케쥴 조회 */
	public List<Care> findCareSchedules(String memberId, String sitterId);
	
	/* 돌봄 예약내역 조회 */
	public Care findReservation(int careId);
	
	/* 보호자-돌보미 간 돌봄 완료 내역 반환 */
	public List<Care> findCareList(String memberId, String sitterId);
	
	/* 돌봄 내역 생성 */
	public int createCare(Care care);
	
	public int deleteCare(int careId);
	
	public String getCheckInfo(String rcvId);
}
