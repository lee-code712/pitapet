package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import model.dao.mybatis.mapper.ServiceMapper;
import model.dto.Service;

public class ServiceDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public ServiceDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/**
	 * 특정 돌보미의 제공 서비스 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Service> findProvideServiceList(String sitterId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<Service> services = sqlSession.getMapper(ServiceMapper.class).findProvideServicesBySitter(sitterId);
			System.out.println(services);
			return services;
		} finally {
			sqlSession.close();
		}
	}
}
