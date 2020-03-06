/*
 * 主页面生成
 */

package application;

import entity.people;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class people_UI extends Application {
	Stage stage = new Stage();

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/application/Actor.fxml"));
			primaryStage.setTitle("演员详细信息");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

	public void showWindow(people p) throws Exception {
		application.Controller_Actor.setPeople(p);
		start(stage);
	}

}
