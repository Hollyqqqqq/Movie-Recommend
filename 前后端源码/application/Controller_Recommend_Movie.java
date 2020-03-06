package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.Friend;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class Controller_Recommend_Movie implements Initializable {

	@FXML
	private ChoiceBox<String> friend_list;
	@FXML
	private TextArea reason;
	@FXML
	private Button button_qr;
	@FXML
	private Text notice;

	private static int userid = 0;
	private static int friendid = 0;
	private static int movieid = 0;
	private static List<Friend> friends = new ArrayList<Friend>();// 好友列表
	private static ObservableList<String> options;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		friend_list.setItems(options);

		friend_list.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
					friendid = friends.get(new_val.intValue()).getId();
//			System.out.println(s);
				});
	}

	public static void setId(int user_id, int movie_id) {
		userid = user_id;
		movieid = movie_id;
		addFriendList();
	}

	public static void addFriendList() {
		friends = database.Database_addFriends.showFriends(userid);
		List<String> friends_name = new ArrayList<String>();
		for (Friend f : friends) {
			friends_name.add(f.getName());
//			options.add(f.getName());
		}

//		for (String str : friends_name) {
//			System.out.println(str);
//		}

		options = FXCollections.observableArrayList(friends_name);

	}

	public void recommendMovie() {
		String rea = reason.getText();
		boolean flag = database.Database_insert_movie.insertFriendRecommendMovie(userid, (friendid - 10000), movieid,
				rea);
		if (flag) {
			notice.setText("推荐成功！");
//			System.out.println(userid + " 向 " + (friendid - 10000) + " 推荐了 " + movieid + "," + rea);
		} else {
			notice.setText("系统繁忙，请稍后重试!");
		}
	}

}
