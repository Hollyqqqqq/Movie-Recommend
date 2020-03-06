/*
 * ����˼�����
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

	// ��ӵ�Ӱ
	@FXML
	private Button button_chooseFile;
	@FXML
	private Text insert_notice;
	@FXML
	private Text download_notice;
	
	
	@FXML
	private Button button_modif;

	// ���ݿ�״̬��ʾ
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

	// ����,ɾ����Ӱ
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
	private List<Movie> search_movie_list = new ArrayList<Movie>();// ���ݼ�
	ObservableList<Movie> search_list = FXCollections.observableArrayList();// JavaFX���ݼ���
	ObservableList<Movie> empty_list = FXCollections.observableArrayList();// JavaFX���ݼ���
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

		// ˫����Ӧ
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

		// ��ѯ��Ӱ�б��
		search_movie_name.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
		search_movie_score.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("star"));
		search_movie_year.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year_released"));
		search_movie_country.setCellValueFactory(new PropertyValueFactory<Movie, String>("country"));
		search_movie_id.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));

		AddMovie();
		ShowDatabaseState();
	}

	// ��ӵ�Ӱ ����
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
					String notice = "����ɹ��������µĵ�Ӱ: " + update_insert[0] + "��,���µ�Ӱ:" + update_insert[1] + "��.";
					insert_notice.setText(notice);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
	}

	// ��ʾ�����ĵ�Ӱ
	public void showSearchMovie() {
		delete_notice.setText("");
		String title = search_movie.getText();
		search_movie_list = database.Database_getMovie.search_movie(title);
		search_list.setAll(search_movie_list);
		table_movie_search.setItems(search_list);
	}

	// �޸ĵ�Ӱ
	public void ModifyMovie() throws Exception {

	}

	public void cancelDelete() {
		movieid = 0;
		delete_notice.setText("");
	}

	// ȷ��ɾ����Ӱ
	public void ensureDeleteMovie() {
		boolean flag = database.Database_deleteMovie.delete_movie(movieid);
		if (flag)
			delete_notice.setText("ɾ���ɹ�!");
		else
			delete_notice.setText("ɾ��ʧ��!");
	}

	// ���ݿ�״̬
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
		download_notice.setText("�û����ݵ����ɹ�!");
	}

	public void downloadData_movie() {
		database.database_download_data.moviesData();
		download_notice.setText("��Ӱ���ݵ����ɹ�!");
	}
}
