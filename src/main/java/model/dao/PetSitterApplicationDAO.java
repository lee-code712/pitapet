package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Care;
import model.dto.Member;
import model.dto.PetSitter;
import model.dto.PetSitterApplication;
import model.dto.Review;

public class PetSitterApplicationDAO {
	private JDBCUtil jdbcUtil = null;

	public PetSitterApplicationDAO() {         
	      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	   }

	public String getApprovalStatus(String memberId) throws SQLException {
		String sql = "SELECT approval_status " + "FROM petsitter_application "
				+ "WHERE member_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				String approval_status = rs.getString("approval_status");
				return approval_status;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	//돌보미에 지원한 대기 상태인 사람들의 아이디와 지원날짜
	public List<PetSitterApplication> findApplicationList() throws SQLException {
		String sql = "SELECT apply_id, apply_date, member_id "
				+ "FROM petsitter_application "
				+ "WHERE approval_status='X'";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<PetSitterApplication> applicationLists = new ArrayList<PetSitterApplication>();
			if(rs.next()) {
				PetSitterApplication pApplication = new PetSitterApplication(
						rs.getString("apply_id"), 
						rs.getString("apply_date"),
						new Member(rs.getString("member_id")));
				applicationLists.add(pApplication);
				while (rs.next()) {
					pApplication = new PetSitterApplication(
							rs.getString("apply_id"), 
							rs.getString("apply_date"),
							new Member(rs.getString("member_id")));
					applicationLists.add(pApplication);
				}
				return applicationLists;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
