/*
 * 电影推荐页面注册
 */

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Recommend_Movie extends Application {
	Stage stage = new Stage();

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/application/Recommend_Movie.fxml"));
			primaryStage.setTitle("电影推荐");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

//		database.Database_getMovie.show_recently_watched(3);
	}

	public void showWindow(int userid, int movieid) throws Exception {
		Controller_Recommend_Movie.setId(userid, movieid);
		start(stage);
	}

}
