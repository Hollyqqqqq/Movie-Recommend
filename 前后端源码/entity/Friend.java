package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Friend {
	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty pre;
	private SimpleStringProperty des;

	public Friend() {

	}

	public Friend(int uid, String n, String p, String d) {
		this.id = new SimpleIntegerProperty(uid);
		this.name = new SimpleStringProperty(n);
		this.pre = new SimpleStringProperty(p);
		this.des = new SimpleStringProperty(d);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getPre() {
		return pre.get();
	}

	public void setPre(String pre) {
		this.pre = new SimpleStringProperty(pre);
	}

	public String getDes() {
		return des.get();
	}

	public void setDes(String des) {
		this.des = new SimpleStringProperty(des);
	}

}
