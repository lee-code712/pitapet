package model.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import model.dto.Member;

public interface MemberMapper {
	
	public Member selectMember (String memberId);
	
	public int createMember (Member newMember);
	
	public int updateMember (Member updateInfo);
	
	public int deleteProfilePic (@Param("memberId") String memberId, @Param("like") String like);
	
	public int addAttachment (@Param("memberId") String memberId, @Param("image") String image);
}
