package entity;

public class User {
//	private String id;
	private String name;
//	private String email;
	private String sex;
	private String intro;

	public User() {

	}

	public User(String name, String sex, String intro) {
		this.name = name;
		this.sex = sex;
		this.intro = intro;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

}
