package model;

import java.util.List;

public class CareRecord {
	private String id;
	private List<Service> services;
	private String writeDate;
	private String title;
	private String content;
	private Reservation resInfo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Reservation getResInfo() {
		return resInfo;
	}
	public void setResInfo(Reservation resInfo) {
		this.resInfo = resInfo;
	}
}
