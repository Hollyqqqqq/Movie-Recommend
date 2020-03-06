package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller_SignUpNotice implements Initializable {

	@FXML
	private Button Button_dl;
	@FXML
	private Button Button_tc;
	@FXML
	private Text user;

	private static String str = "******";

	public Controller_SignUpNotice() {
		// TODO Auto-generated constructor stub
	}

	public void initialize(URL location, ResourceBundle resources) {
		user.setText(str);
	}

	public static void setUser(String s) {
		str = s;
	}

	public void Exit() throws Exception {
		Platform.exit();
	}

	public void ToSignIn() throws Exception {
		Stage stage = (Stage) Button_dl.getScene().getWindow();
		stage.close();
		application.Main second = new Main();
		second.showWindow();
	}

}
