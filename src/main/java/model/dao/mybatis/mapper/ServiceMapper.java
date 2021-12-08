package model.dao.mybatis.mapper;

import java.util.List;

import model.dto.Service;

public interface ServiceMapper {
	public List<Service> selectProvideServicesBySitter(String sitterId);
}
