package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Friend_request {
	private SimpleIntegerProperty requestid;
	private SimpleIntegerProperty userid;
	private SimpleStringProperty userName;
	private SimpleStringProperty userPre;
	private SimpleStringProperty userIntro;
	private SimpleStringProperty date;

	public Friend_request(int requestid, int userid, String username, String userPre, String userIntro, String date) {
		this.requestid = new SimpleIntegerProperty(requestid);
		this.userid = new SimpleIntegerProperty(userid);
		this.userName = new SimpleStringProperty(username);
		this.userPre = new SimpleStringProperty(userPre);
		this.userIntro = new SimpleStringProperty(userIntro);
		this.date = new SimpleStringProperty(date);
	}

	public Friend_request() {

	}

	public int getRequestid() {
		return requestid.get();
	}

	public void setRequestid(int requestid) {
		this.requestid = new SimpleIntegerProperty(requestid);
	}

	public int getUserid() {
		return userid.get();
	}

	public void setUserid(int userid) {
		this.userid = new SimpleIntegerProperty(userid);
	}

	public String getUserName() {
		return userName.get();
	}

	public void setUserName(String userName) {
		this.userName = new SimpleStringProperty(userName);
	}

	public String getUserPre() {
		return userPre.get();
	}

	public void setUserPre(String userPre) {
		this.userPre = new SimpleStringProperty(userPre);
	}

	public String getUserIntro() {
		return userIntro.get();
	}

	public void setUserIntro(String userIntro) {
		this.userIntro = new SimpleStringProperty(userIntro);
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(String date) {
		this.date = new SimpleStringProperty(date);
	}

}
