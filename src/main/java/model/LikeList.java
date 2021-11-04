package model;

public class LikeList {
	private String memberId;
	private PetSitter likeSitter;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public PetSitter getLikeSitter() {
		return likeSitter;
	}
	public void setLikeSitter(PetSitter likeSitter) {
		this.likeSitter = likeSitter;
	}
}
