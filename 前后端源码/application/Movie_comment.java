/*
 * 电影评分评论界面注册
 */

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Movie_comment extends Application {
	Stage stage = new Stage();

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/application/Movie_Comment.fxml"));
			primaryStage.setTitle("登录");
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
		application.Controller_Movie_Comment.setId(userid, movieid);
		start(stage);
	}

}
