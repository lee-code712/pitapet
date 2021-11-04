package model;

public class Pet {
	private String id;
	private String name;
	private String birth;
	private int gender;
	private float weight;
	private PetKind kind;
	private Member myCompanion;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public PetKind getKind() {
		return kind;
	}
	public void setKind(PetKind kind) {
		this.kind = kind;
	}
	public Member getMyCompanion() {
		return myCompanion;
	}
	public void setMyCompanion(Member myCompanion) {
		this.myCompanion = myCompanion;
	}	
}
