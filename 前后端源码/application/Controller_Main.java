/*
 * ��ҳ�������
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

public class Controller_Main implements Initializable {

	@FXML
	private Button Button_dl;
	@FXML
	private Button Button_zc;
	@FXML
	private Button Button_gly;
	@FXML
	private Button Button_findPass;
	@FXML
	private TextField text_user;
	@FXML
	private PasswordField text_pass;
	@FXML
	private Text notice;

	public Controller_Main() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// ����Ա��¼��ת
	public void ToAdminSignIn() throws Exception {
		Stage stage = (Stage) Button_gly.getScene().getWindow();
		stage.close();
		application.Admin_SignIn second = new Admin_SignIn();
		second.showWindow();
	}

	// ע����ת
	public void SignUp() throws Exception {
		Stage stage = (Stage) Button_zc.getScene().getWindow();
		stage.close();
		application.SignUp second = new SignUp();
		second.showWindow();
	}

	// �û���¼�жϼ���ת
	public void SignIn() throws Exception {
		Stage stage = (Stage) Button_dl.getScene().getWindow();
		String user = text_user.getText();
		String pass = text_pass.getText();
		if (isDigit(user)) {
			int user_id = Integer.parseInt(user) - 10000;// �˺����û�id��Ӧ
			if (user_id > 0) {
				boolean check = database.DatabaseUtil.checkUser(user_id, pass);// �������ݿ��жϵ�¼
				if (check) { // ��¼�ɹ���ת
					stage.close();
					application.User_UI second = new User_UI();
					second.showWindow(user_id);

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

	// �һ�����ҳ����ת
	public void FindPass() throws Exception {
		Stage stage = (Stage) Button_findPass.getScene().getWindow();
		stage.close();
		application.FindPassword second = new FindPassword();
		second.showWindow();
	}

	// �ж�һ���ַ����Ƿ�ȫΪ����
	public static boolean isDigit(String strNum) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) strNum);
		return matcher.matches();
	}

}
