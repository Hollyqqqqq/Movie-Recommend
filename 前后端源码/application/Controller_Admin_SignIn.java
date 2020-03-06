/*
 * ����Ա��¼���������
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller_Admin_SignIn implements Initializable {

	@FXML
	private Button Button_dl;
	@FXML
	private Button Button_fh;
	@FXML
	private TextField user;
	@FXML
	private PasswordField pass;
	@FXML
	private Text notice;

	public Controller_Admin_SignIn() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// �����û���¼����
	public void Rtn() throws Exception {
		Stage stage = (Stage) Button_fh.getScene().getWindow();
		stage.close();
		application.Main second = new Main();
		second.showWindow();
	}

	public void ToAdmin() throws Exception {
		Stage stage = (Stage) Button_dl.getScene().getWindow();
		String u = user.getText();
		String p = pass.getText();

		if (isDigit(u)) {
			int user_id = Integer.parseInt(u) - 10;
			if (user_id > 0) {
				boolean check = database.DatabaseUtil.checkAdmin(user_id, p);// ͨ�����ݿ�ȷ����Ϣ
				if (check) { // �ɹ���¼��ת
					stage.close();
					application.Admin_UI second = new Admin_UI();
					second.showWindow();
				} else {
					notice.setText("�˺Ż��������");
					notice.setFill(Color.RED);
				}
			} else {
				notice.setText("�˺Ż��������");
				notice.setFill(Color.RED);
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
