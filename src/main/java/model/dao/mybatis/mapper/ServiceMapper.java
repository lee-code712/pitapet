package model.dao.mybatis.mapper;

import java.util.List;

import model.dto.CareDetails;
import model.dto.Service;

public interface ServiceMapper {
	
	/* 제공가능한 서비스 리스트 조회 */
	public List<Service> findProvideServicesBySitter(String sitterId);
	
	/* 제공받을 서비스 삽입 */
	public int createReceiveServices(List<CareDetails> rcevServices);
	
	/* 제공받을 서비스 삭제 */
	public int deleteReceiveService(String careId);
	
}
