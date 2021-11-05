package model;

import java.util.List;

public class Care {
	private Integer id;
	private String startDate;
	private String endDate;
	private int totalPrice;
	private String notes;
	private int status;
	private List<Service> services;
	private List<Pet> pets;
	private Member companion;
	private PetSitter sitter;
	
	public Care(Integer id, String startDate, String endDate, PetSitter sitter) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sitter = sitter;
	}
	
	public Care(Integer id) {
		super();
		this.id = id;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}
	public List<Pet> getPets() {
		return pets;
	}
	public void setPets(List<Pet> pets) {
		this.pets = pets;
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
