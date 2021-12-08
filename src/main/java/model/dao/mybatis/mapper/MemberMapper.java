package model.dao.mybatis.mapper;

import model.dto.Member;

public interface MemberMapper {
	
	public Member selectMember (String memberId);
	
	public int createMember (Member newMember);
	
	public int updateMember (Member updateInfo);
}
