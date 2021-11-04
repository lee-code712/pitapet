package model;

import java.util.List;

public class PetSitter {
	private String id;
	private int publicStatus;
	private List<Integer> availableDate;
	private int calculatedPrice;
	private List<String> tag;
	private String notes;
	private float avgRate;
	private int like;
	private int view;
	private PetSitterApplyInfo myApplyInfo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPublicStatus() {
		return publicStatus;
	}
	public void setPublicStatus(int publicStatus) {
		this.publicStatus = publicStatus;
	}
	public List<Integer> getAvailableDate() {
		return availableDate;
	}
	public void setAvailableDate(List<Integer> availableDate) {
		this.availableDate = availableDate;
	}
	public int getCalculatedPrice() {
		return calculatedPrice;
	}
	public void setCalculatedPrice(int calculatedPrice) {
		this.calculatedPrice = calculatedPrice;
	}
	public List<String> getTag() {
		return tag;
	}
	public void setTag(List<String> tag) {
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
	public PetSitterApplyInfo getMyApplyInfo() {
		return myApplyInfo;
	}
	public void setMyApplyInfo(PetSitterApplyInfo myApplyInfo) {
		this.myApplyInfo = myApplyInfo;
	}
}
