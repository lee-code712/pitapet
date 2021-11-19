package model.dto;

import java.util.List;

public class Care {
	private int id;
	private String startDate;
	private String endDate;
	private int totalPrice;
	private String notes;
	private String status;
	private List<CareDetails> careList;
	private Member companion;
	private PetSitter sitter;
	
	public Care(int id) {
		super();
		this.id = id;
	}
	
	public Care(int id, Member companion, PetSitter sitter) {
		super();
		this.id = id;
		this.companion = companion;
		this.sitter = sitter;
	}

	public Care(int id, String startDate, String endDate, PetSitter sitter) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sitter = sitter;
	}
	
	public Care(int id, String startDate, String endDate, PetSitter sitter, String status) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sitter = sitter;
		this.status = status;
	}
	
	public Care(String startDate, String endDate, int totalPrice, String notes, String status, List<CareDetails> careList, Member companion, PetSitter sitter) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
		this.notes = notes;
		this.status = status;
		this.careList = careList;
		this.companion = companion;
		this.sitter = sitter;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<CareDetails> getCareList() {
		return careList;
	}
	public void setCareList(List<CareDetails> careList) {
		this.careList = careList;
	}
	public Member getCompanion() {
		return companion;
	}
	public void setCompanion(Member companion) {
		this.companion = companion;
	}
	public PetSitter getSitter() {
		return sitter;
	}
	public void setSitter(PetSitter sitter) {
		this.sitter = sitter;
	}
}
