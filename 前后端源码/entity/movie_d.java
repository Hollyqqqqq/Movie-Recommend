package entity;

public class movie_d {
	private String released_data;
	private String title;
	private String country;
	private double star;
	private String intro;
	private int runtime;

	public movie_d(String released_data, String title, String country, double star, String intro, int runtime) {
		this.released_data = released_data;
		this.title = title;
		this.country = country;
		this.star = star;
		this.intro = intro;
		this.runtime = runtime;
	}

	public movie_d() {

	}

	public String getReleased_data() {
		return released_data;
	}

	public void setReleased_data(String released_data) {
		this.released_data = released_data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

}
