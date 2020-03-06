/*
 * 管理员登录界面监视器
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

	// 返回用户登录界面
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
				boolean check = database.DatabaseUtil.checkAdmin(user_id, p);// 通过数据库确认信息
				if (check) { // 成功登录跳转
					stage.close();
					application.Admin_UI second = new Admin_UI();
					second.showWindow();
				} else {
					notice.setText("账号或密码错误！");
					notice.setFill(Color.RED);
				}
			} else {
				notice.setText("账号或密码错误！");
				notice.setFill(Color.RED);
			}
		} else {
			notice.setText("请输入正确的账号格式！");
			notice.setFill(Color.RED);
		}

	}

	// 判断一个字符串是否全为数字
	public static boolean isDigit(String strNum) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) strNum);
		return matcher.matches();
	}

}
