/*
 * ∫√”—«Î«Û“≥√Ê
 */

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Friend_request_UI extends Application {
	Stage stage = new Stage();

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/application/Friend_request.fxml"));
			primaryStage.setTitle("µ«¬º");
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

	public void showWindow(int id) throws Exception {
		application.Controller_Friend_request.setId(id);
		start(stage);
	}

}
