package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.Comments_movie;
import entity.Movie;
import entity.people;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Controller_Movie_UI implements Initializable {

	@FXML
	private Button button;
	@FXML
	private Button button_recommend;
	@FXML
	private Text score;
	@FXML
	private TextField movie_name;
	@FXML
	private TextField movie_released;
	@FXML
	private TextArea movie_type;
	@FXML
	private TextField movie_country;
	@FXML
	private TextArea movie_music;
	// 演员绑定
	@FXML
	private TableView<people> table_actors;
	@FXML
	private TableColumn<people, String> actor_name;
	@FXML
	private TableColumn<people, String> actor_role;
	private static List<people> people_list = new ArrayList<people>();
	static ObservableList<people> pep = FXCollections.observableArrayList();
	// 电影评论绑定
	@FXML
	private TableView<Comments_movie> table_comments;
	@FXML
	private TableColumn<Comments_movie, String> user_n;
	@FXML
	private TableColumn<Comments_movie, Integer> user_score;
	@FXML
	private TableColumn<Comments_movie, String> user_date;
	@FXML
	private TableColumn<Comments_movie, String> user_comments;
	private List<Comments_movie> cm = new ArrayList<Comments_movie>();
	ObservableList<Comments_movie> c = FXCollections.observableArrayList();

	private static int movie_id = 0;
	private static int userid = 0;
	private static Movie m;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		movie_music.setWrapText(true);

		// 双击响应
		table_actors.setRowFactory(tv -> {
			TableRow<people> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					people rowData = row.getItem();
					try {
						showPeopleDetail(rowData);
//						System.out.println(rowData.getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println("Double click on: " + rowData.getId());
				}
			});
			return row;
		});

		configTable();
		showMovie();
		showComments();
		showActor();
	}

	// 表格列绑定
	private void configTable() {

		user_n.setCellValueFactory(new PropertyValueFactory<Comments_movie, String>("user_name"));
		user_score.setCellValueFactory(new PropertyValueFactory<Comments_movie, Integer>("star"));
		user_date.setCellValueFactory(new PropertyValueFactory<Comments_movie, String>("date"));
		user_comments.setCellValueFactory(new PropertyValueFactory<Comments_movie, String>("comments"));

		actor_name.setCellValueFactory(new PropertyValueFactory<people, String>("name"));
		actor_role.setCellValueFactory(new PropertyValueFactory<people, String>("credit"));
	}

	private void showActor() {
		people_list = database.Database_getMovie.show_movie_people(movie_id);
		pep.setAll(people_list);
		table_actors.setItems(pep);
	}

	public static void setMovieId(int movieid, int user_id) {
//		System.out.println("id:" + id);
		userid = user_id;
		movie_id = movieid;
		m = database.Database_getMovie.show_movie_detail(movie_id);
	}

	private void showMovie() {
//		Movie m = database.Database_getMovie.show_movie_detail(movie_id);
		movie_name.setText(m.getTitle());
		movie_released.setText(m.getYear_released() + "");
		movie_country.setText(m.getCountry());
		movie_type.setText(m.getType());
		movie_music.setText(m.getIntro());
		score.setText(m.getStar() + "");
		score.setFont(Font.font("Verdana", 50));
		score.setFill(Color.BLUE);
//		System.out.println(m.getTitle());
	}

	private void showComments() {
		cm = database.Database_getMovie.show_movie_comments(movie_id);
		c.addAll(cm);
		table_comments.setItems(c);
	}

	private void showPeopleDetail(people p) {
		application.people_UI s = new people_UI();
		try {
			s.showWindow(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void goComment() throws Exception {
		application.Movie_comment second = new Movie_comment();
		second.showWindow(userid, movie_id);
	}

	public void goRecommend() throws Exception {
		application.Recommend_Movie second = new Recommend_Movie();
		second.showWindow(userid, movie_id);
	}
}
