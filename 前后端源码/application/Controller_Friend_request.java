package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Controller_Friend_request implements Initializable {

	@FXML
	private Button button_add;
	@FXML
	private Button button_refuse;

	private static int requestid = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public static void setId(int id) {
		requestid = id;
	}

	// �ܾ�����
	public void refuse() {
//		database.Database_addFriends.
	}

	// ��������
	public void add() {
		database.Database_addFriends.agree_add_friend(requestid);
	}

}
