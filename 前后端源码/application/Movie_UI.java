/*
 	电影详细信息页面生成
 */

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Movie_UI extends Application {
	Stage stage = new Stage();

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/application/Movie_UI.fxml"));
			primaryStage.setTitle("电影详细信息");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showWindow(int movieid,int userid) throws Exception {
		application.Controller_Movie_UI.setMovieId(movieid,userid);
		start(stage);
	}

}
