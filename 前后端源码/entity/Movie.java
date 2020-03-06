package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Movie {
	private SimpleIntegerProperty id;
	private SimpleStringProperty title;
	private SimpleStringProperty type;
	private SimpleIntegerProperty year_released;
	private SimpleStringProperty country;
	private SimpleDoubleProperty star;
	private SimpleStringProperty intro;
	private SimpleIntegerProperty runtime;
	private SimpleStringProperty director;
	private SimpleStringProperty actor;

	public Movie() {

	}

	public Movie(int id, String title, String type, int year_released, String country, double star, String intro,
			int runtime, String director, String actor) {
		this.id = new SimpleIntegerProperty(id);
		this.title = new SimpleStringProperty(title);
		this.type = new SimpleStringProperty(type);
		this.year_released = new SimpleIntegerProperty(year_released);
		this.country = new SimpleStringProperty(country);
		this.star = new SimpleDoubleProperty(star);
		this.intro = new SimpleStringProperty(intro);
		this.runtime = new SimpleIntegerProperty(runtime);
		this.director = new SimpleStringProperty(director);
		this.actor = new SimpleStringProperty(actor);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getType() {
		return type.get();
	}

	public void setType(String type) {
		this.type = new SimpleStringProperty(type);
	}

	public String getDirector() {
		return director.get();
	}

	public void setDirector(String director) {
		this.director = new SimpleStringProperty();
	}

	public String getActor() {
		return actor.get();
	}

	public void setActor(String actor) {
		this.actor = new SimpleStringProperty(actor);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title = new SimpleStringProperty(title);
	}

	public int getYear_released() {
		return year_released.get();
	}

	public void setYear_released(int year_released) {
		this.year_released = new SimpleIntegerProperty(year_released);
	}

	public String getCountry() {
		return country.get();
	}

	public void setCountry(String country) {
		this.country = new SimpleStringProperty(country);
	}

	public double getStar() {
		return star.get();
	}

	public void setStar(double star) {
		this.star = new SimpleDoubleProperty(star);
	}

	public String getIntro() {
		return intro.get();
	}

	public void setIntro(String intro) {
		this.intro = new SimpleStringProperty(intro);
	}

	public int getRuntime() {
		return runtime.get();
	}

	public void setRuntime(int runtime) {
		this.runtime = new SimpleIntegerProperty(runtime);
	}

}
