package model.dao.mybatis.mapper;

import java.util.List;

import model.dto.Service;

public interface ServiceMapper {
	public int deleteReceiveService(String careId);
	public List<Service> selectProvideServicesBySitter(String sitterId);
}
