package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class Controller_Movie_Comment implements Initializable {

	@FXML
	private Button button_qr;
	@FXML
	private TextArea text_comment;
	@FXML
	private ChoiceBox<Integer> score;
	@FXML
	private Text notice;
	// �����б�
	ObservableList<Integer> options = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	private int s = 0;
	private static int userid = 0;
	private static int movieid = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		score.setItems(options);

		score.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
					s = options.get(new_val.intValue());
//					System.out.println(s);
				});

	}

	// ���û��͵�Ӱid
	public static void setId(int id1, int id2) {
		userid = id1;
		movieid = id2;
	}

	public void getComments() {
		String comm = text_comment.getText();
		boolean flag = database.Database_insert_movie.insertWatchedMovie(userid, movieid, s, comm);
		if (flag)
			notice.setText("���۳ɹ�!");
		else
			notice.setText("�����Ѵ���!");
		System.out.println(flag);
		System.out.println(s + "," + comm);
	}

}
