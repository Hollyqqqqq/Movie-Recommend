package application;

import java.net.URL;
import java.util.ResourceBundle;

import entity.people;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller_Actor implements Initializable {

	@FXML
	private TextField actor_name;
	@FXML
	private Text gender;
	@FXML
	private TextArea intro;
	@FXML
	private TextField born_time;

	private static people p1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		intro.setWrapText(true);
		setInfo();
	}

	public static void setPeople(people p) {
		p1 = p;
	}

	private void setInfo() {
		actor_name.setText(p1.getName());
		gender.setText(p1.getGender());
		intro.setText(p1.getBreif_intro());
		born_time.setText(p1.getBirth());
	}

}
