package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Fri_movie {
	private SimpleStringProperty name;
	private SimpleIntegerProperty id;
	private SimpleStringProperty title;
	private SimpleDoubleProperty star;
	private SimpleStringProperty reason;
	private SimpleStringProperty date;

	public Fri_movie() {

	}

	public Fri_movie(String name, int id, String title, double star, String reason, String date) {
		this.name = new SimpleStringProperty(name);
		this.id = new SimpleIntegerProperty(id);
		this.title = new SimpleStringProperty(title);
		this.star = new SimpleDoubleProperty(star);
		this.reason = new SimpleStringProperty(reason);
		this.date = new SimpleStringProperty(date);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title = new SimpleStringProperty(title);
	}

	public double getStar() {
		return star.get();
	}

	public void setStar(double star) {
		this.star = new SimpleDoubleProperty(star);
	}

	public String getReason() {
		return reason.get();
	}

	public void setReason(String reason) {
		this.reason = new SimpleStringProperty(reason);
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(String date) {
		this.date = new SimpleStringProperty(date);
	}
}
