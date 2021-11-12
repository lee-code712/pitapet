package model;

/* receive service랑 checklist 테이블이 합쳐진 것 */
public class CareDetails {
	private int id; // recv_id
	private Care careInfo;
	private Service serviceInfo;
	private Pet carePet;
	private String check;
	
	public CareDetails(int id) {
		super();
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Care getCareInfo() {
		return careInfo;
	}

	public void setCareInfo(Care careInfo) {
		this.careInfo = careInfo;
	}

	public Service getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(Service serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public Pet getCarePet() {
		return carePet;
	}
	public void setCarePet(Pet carePet) {
		this.carePet = carePet;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
}
