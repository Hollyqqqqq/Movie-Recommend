package entity;

import javafx.beans.property.SimpleStringProperty;

public class people {
	private SimpleStringProperty name;
	private SimpleStringProperty credit; // A为演员，D为导演
	private SimpleStringProperty gender;
	private SimpleStringProperty birth;
	private SimpleStringProperty breif_intro;

	public people(String name, String credit, String gender, String birth, String breif_intro) {
		this.name = new SimpleStringProperty(name);
		this.credit = new SimpleStringProperty(credit);
		this.gender = new SimpleStringProperty(gender);
		this.birth = new SimpleStringProperty(birth);
		this.breif_intro = new SimpleStringProperty(breif_intro);
	}

	public people() {

	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getCredit() {
		return credit.get();
	}

	public void setCredit(String credit) {
		this.credit = new SimpleStringProperty(credit);
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender = new SimpleStringProperty(gender);
	}

	public String getBirth() {
		return birth.get();
	}

	public void setBirth(String birth) {
		this.birth = new SimpleStringProperty(birth);
	}

	public String getBreif_intro() {
		return breif_intro.get();
	}

	public void setBreif_intro(String breif_intro) {
		this.breif_intro = new SimpleStringProperty(breif_intro);
	}

}
