/*
 * 主页面监视器
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

	// 管理员登录跳转
	public void ToAdminSignIn() throws Exception {
		Stage stage = (Stage) Button_gly.getScene().getWindow();
		stage.close();
		application.Admin_SignIn second = new Admin_SignIn();
		second.showWindow();
	}

	// 注册跳转
	public void SignUp() throws Exception {
		Stage stage = (Stage) Button_zc.getScene().getWindow();
		stage.close();
		application.SignUp second = new SignUp();
		second.showWindow();
	}

	// 用户登录判断及跳转
	public void SignIn() throws Exception {
		Stage stage = (Stage) Button_dl.getScene().getWindow();
		String user = text_user.getText();
		String pass = text_pass.getText();
		if (isDigit(user)) {
			int user_id = Integer.parseInt(user) - 10000;// 账号与用户id对应
			if (user_id > 0) {
				boolean check = database.DatabaseUtil.checkUser(user_id, pass);// 连接数据库判断登录
				if (check) { // 登录成功跳转
					stage.close();
					application.User_UI second = new User_UI();
					second.showWindow(user_id);

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

	// 找回密码页面跳转
	public void FindPass() throws Exception {
		Stage stage = (Stage) Button_findPass.getScene().getWindow();
		stage.close();
		application.FindPassword second = new FindPassword();
		second.showWindow();
	}

	// 判断一个字符串是否全为数字
	public static boolean isDigit(String strNum) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) strNum);
		return matcher.matches();
	}

}
