package model;

public class PetSitter {
	private Member sitter;
	private int publicStatus;
	private String ableDate;
	private String calculatedPrice;
	private String tag;
	private String notes;
	private float avgRate;
	private int like;
	private int view;
	private PetSitterApplyment myApplyInfo;
	
	public PetSitter(Member sitter) {
		super();
		this.sitter = sitter;
	}
	
	public Member getSitter() {
		return sitter;
	}
	public void setSitter(Member sitter) {
		this.sitter = sitter;
	}
	public int getPublicStatus() {
		return publicStatus;
	}
	public void setPublicStatus(int publicStatus) {
		this.publicStatus = publicStatus;
	}
	
	public String getAbleDate() {
		return ableDate;
	}

	public void setAbleDate(String ableDate) {
		this.ableDate = ableDate;
	}

	public String getCalculatedPrice() {
		return calculatedPrice;
	}

	public void setCalculatedPrice(String calculatedPrice) {
		this.calculatedPrice = calculatedPrice;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public float getAvgRate() {
		return avgRate;
	}
	public void setAvgRate(float avgRate) {
		this.avgRate = avgRate;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}

	public PetSitterApplyment getMyApplyInfo() {
		return myApplyInfo;
	}

	public void setMyApplyInfo(PetSitterApplyment myApplyInfo) {
		this.myApplyInfo = myApplyInfo;
	}
	
}
