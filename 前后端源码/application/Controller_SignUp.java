/*
 * ע����������
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller_SignUp implements Initializable {

	@FXML
	private Button Button_qr;
	@FXML
	private Button Button_qx;
	@FXML
	private TextField user_name;
	@FXML
	private PasswordField pass1;
	@FXML
	private PasswordField pass2;
	@FXML
	private TextField answer1;
	@FXML
	private TextField answer2;
	@FXML
	private TextField answer3;
	@FXML
	private Text ques1;
	@FXML
	private Text ques2;
	@FXML
	private Text ques3;
	@FXML
	private Text notice;
	@FXML
	private TextArea des;
	@FXML
	private CheckBox box_jq;
	@FXML
	private CheckBox box_xj;
	@FXML
	private CheckBox box_dz;
	@FXML
	private CheckBox box_aq;
	@FXML
	private CheckBox box_kh;
	@FXML
	private CheckBox box_dh;
	@FXML
	private CheckBox box_kb;
	@FXML
	private CheckBox box_mx;
	@FXML
	private CheckBox box_qh;
	@FXML
	private CheckBox box_zn;

	public Controller_SignUp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// ע��ʱ����ȡ�û�����Ϣ��Ȼ�������ݿ⴫��
	public void ButtonOn(ActionEvent event) throws Exception {
		String name = user_name.getText().replaceAll(" ", "");
		String password1 = pass1.getText().replaceAll(" ", "");
		String password2 = pass2.getText().replaceAll(" ", "");
		String ans1 = answer1.getText().replaceAll(" ", "");
		String ans2 = answer2.getText().replaceAll(" ", "");
		String ans3 = answer3.getText().replaceAll(" ", "");
		String intro = des.getText().trim();

		CheckBox[] box = { box_jq, box_xj, box_dz, box_aq, box_kh, box_dh, box_kb, box_mx, box_qh, box_zn };
		int[] preference = new int[10];
		for (int i = 0; i < 10; i++) {
			if (box[i].isSelected()) {
				preference[i] = 1;
			} else
				preference[i] = 0;
		}

		if (name.isEmpty() || password1.isEmpty() || password2.isEmpty() || ans1.isEmpty() || ans2.isEmpty()
				|| ans3.isEmpty()) {
			notice.setText("��������б����");
			notice.setFill(Color.RED);
		} else if (!password1.equals(password2)) {
			notice.setText("������������벻��ͬ��");
			notice.setFill(Color.RED);
		} else if (password1.length() < 6) {
			notice.setText("���볤�ȹ��̣�");
			notice.setFill(Color.RED);
		} else {
			String[] info = { name, password1, intro };

			int key = database.DatabaseUtil.userSignUp(info); // �����ݿ⴫�����ݲ������û���id

			if (key != -1) { // ע��ɹ������ص�¼������ת
				String[] ans = { ans1, ans2, ans3 };
				int line = database.DatabaseUtil.userAnswer(key, ans);// �û��ܱ��洢
				int line2 = database.DatabaseUtil.userPreference(key, preference);// �û�ϲ���б�洢
				System.out.println(line + "," + line2);
				if (line > 0) {
					Stage stage = (Stage) Button_qr.getScene().getWindow();
					stage.close();
					application.SignUpNotice second = new SignUpNotice();
					second.showWindow(key + 10000);
				} else {
					notice.setText("ϵͳ��æ�����Ժ����ԣ�");
					notice.setFill(Color.RED);
				}
			} else {
				notice.setText("�û����Ѵ��ڣ�");
				notice.setFill(Color.RED);
			}
		}

	}

	// �˳�����
	public void Exit(ActionEvent event) throws Exception {
		Stage stage = (Stage) Button_qx.getScene().getWindow();
		stage.close();
	}

}
