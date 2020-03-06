/*
 * �û�����ע��
 */

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class User_UI extends Application {
	Stage stage = new Stage();

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/application/User_UI.fxml"));

			primaryStage.setTitle("��Ӱ�Ƽ�ϵͳ");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showWindow(int id) throws Exception {
		application.Controller_User_UI.setUser(id);
		start(stage);
	}
}
