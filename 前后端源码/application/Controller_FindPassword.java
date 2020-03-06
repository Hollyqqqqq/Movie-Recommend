package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller_FindPassword implements Initializable {

	@FXML
	private TextField user_id;
	@FXML
	private TextField text_answer1;
	@FXML
	private TextField text_answer2;
	@FXML
	private TextField text_answer3;
	@FXML
	private Button button_qr;
	@FXML
	private Button button_exit;
	@FXML
	private Text notice;
	@FXML
	private Text rst;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// �˳�
	public void Exit() throws Exception {
		Stage stage = (Stage) button_exit.getScene().getWindow();
		stage.close();
	}

	// �û��һ������ж�
	public void CheckAns() throws Exception {
		String user = user_id.getText();
		String ans1 = text_answer1.getText();
		String ans2 = text_answer2.getText();
		String ans3 = text_answer3.getText();
		if (isDigit(user)) {
			int user_id = Integer.parseInt(user) - 10000;
			String[] ans = { ans1, ans2, ans3 };
			String result = database.Database_findPassword.checkAnswer(user_id, ans);
			if (!result.equals("Not correct answer!")) { // ��֤�ɹ�
				rst.setText("�һ�����ɹ�,���������ǣ�" + result + ",���ס��������.");
				System.out.println("�ɹ�");
			} else {
				rst.setText("�˺Ż�����𰸴���");
				rst.setFill(Color.RED);
			}
		} else {
			notice.setText("��������ȷ���˺Ÿ�ʽ��");
			notice.setFill(Color.RED);
		}
	}

	// �ж�һ���ַ����Ƿ�ȫΪ����
	public static boolean isDigit(String strNum) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) strNum);
		return matcher.matches();
	}

}
