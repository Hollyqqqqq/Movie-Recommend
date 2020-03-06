/*
 * 管理端监视器
 */

package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class Controller_Admin_UI implements Initializable {

	// 添加电影
	@FXML
	private Button button_chooseFile;
	@FXML
	private Text insert_notice;
	@FXML
	private Text download_notice;
	
	
	@FXML
	private Button button_modif;

	// 数据库状态显示
	@FXML
	private Text numberOfUser;
	@FXML
	private Text numberOfMovie;
	@FXML
	private Text country_num;
	@FXML
	private Text type_num;
	@FXML
	private Button download_user;
	@FXML
	private Button download_movie;

	// 搜索,删除电影
	@FXML
	private TableView<Movie> table_movie_search;
	@FXML
	private TableColumn<Movie, String> search_movie_name;
	@FXML
	private TableColumn<Movie, Integer> search_movie_score;
	@FXML
	private TableColumn<Movie, Integer> search_movie_year;
	@FXML
	private TableColumn<Movie, Integer> search_movie_id;
	@FXML
	private TableColumn<Movie, String> search_movie_country;
	private List<Movie> search_movie_list = new ArrayList<Movie>();// 数据集
	ObservableList<Movie> search_list = FXCollections.observableArrayList();// JavaFX数据集合
	ObservableList<Movie> empty_list = FXCollections.observableArrayList();// JavaFX数据集合
	@FXML
	private Button button_search;
	@FXML
	private TextField search_movie;
	@FXML
	private Button button_delete;
	@FXML
	private Button button_cancel_delete;
	@FXML
	private Text delete_notice;

	private int movieid = 0;

	private int[] update_insert = new int[2];

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		// 双击响应
		table_movie_search.setRowFactory(tv -> {
			TableRow<Movie> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Movie rowData = row.getItem();
					try {
						delete_notice.setText(rowData.getTitle());
						movieid = rowData.getId();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Double click on: " + rowData.getId());
				}
			});
			return row;
		});

		// 查询电影列表绑定
		search_movie_name.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
		search_movie_score.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("star"));
		search_movie_year.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year_released"));
		search_movie_country.setCellValueFactory(new PropertyValueFactory<Movie, String>("country"));
		search_movie_id.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));

		AddMovie();
		ShowDatabaseState();
	}

	// 添加电影 监视
	public void AddMovie() {
		button_chooseFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ALL EXCEL (*.xlsx)", "*.xlsx");
				FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("ALL EXCEL (*.xls)", "*.xls");
				fileChooser.getExtensionFilters().add(extFilter);
				fileChooser.getExtensionFilters().add(extFilter2);
				File file = fileChooser.showOpenDialog(button_chooseFile.getScene().getWindow());
				String path = file.getPath();
//				System.out.println("path:" + path);
				try {
					update_insert = application.File_Processing.startProcess(path);
					String notice = "导入成功，插入新的电影: " + update_insert[0] + "部,更新电影:" + update_insert[1] + "部.";
					insert_notice.setText(notice);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
	}

	// 显示搜索的电影
	public void showSearchMovie() {
		delete_notice.setText("");
		String title = search_movie.getText();
		search_movie_list = database.Database_getMovie.search_movie(title);
		search_list.setAll(search_movie_list);
		table_movie_search.setItems(search_list);
	}

	// 修改电影
	public void ModifyMovie() throws Exception {

	}

	public void cancelDelete() {
		movieid = 0;
		delete_notice.setText("");
	}

	// 确认删除电影
	public void ensureDeleteMovie() {
		boolean flag = database.Database_deleteMovie.delete_movie(movieid);
		if (flag)
			delete_notice.setText("删除成功!");
		else
			delete_notice.setText("删除失败!");
	}

	// 数据库状态
	public void ShowDatabaseState() {
		int userNum = database.Database_databaseState.getUserNum();
		int movieNum = database.Database_databaseState.getMovieNum();
		int countryNum = database.Database_databaseState.getCountryNum();
		int typeNum = database.Database_databaseState.getTypeNum();
		numberOfUser.setText(userNum + "");
		numberOfMovie.setText(movieNum + "");
		country_num.setText(countryNum + "");
		type_num.setText(typeNum + "");
	}

	public void downloadData_user() {
		database.database_download_data.userData();
		download_notice.setText("用户数据导出成功!");
	}

	public void downloadData_movie() {
		database.database_download_data.moviesData();
		download_notice.setText("电影数据导出成功!");
	}
}
