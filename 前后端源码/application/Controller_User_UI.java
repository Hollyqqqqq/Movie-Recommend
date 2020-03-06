package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Fri_movie;
import entity.Friend;
import entity.Friend_request;
import entity.Movie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Controller_User_UI implements Initializable {

	@FXML
	private Button reload1;
	@FXML
	private Button reload2;
	@FXML
	private Button reload3;
	@FXML
	private Button reload4;
	@FXML
	private Button reload5;

	// 系统推荐
	@FXML
	private Button button_reload;
	@FXML
	private TableView<Movie> table_movie_system; // 系统推荐电影表tableview
	@FXML
	private TableColumn<Movie, String> system_movie_title;// 列绑定
	@FXML
	private TableColumn<Movie, Integer> system_movie_id;// 列绑定
	@FXML
	private TableColumn<Movie, Double> system_movie_score;
	@FXML
	private TableColumn<Movie, Integer> system_movie_year;
	@FXML
	private TableColumn<Movie, String> system_movie_country;
	private List<Movie> system_movie = new ArrayList<Movie>();// 数据集
	ObservableList<Movie> system_list = FXCollections.observableArrayList();// JavaFX数据集合

	// 好友推荐
	@FXML
	private TableView<Fri_movie> table_movie_friend;
	@FXML
	private TableColumn<Fri_movie, String> recommend_friend_name;
	@FXML
	private TableColumn<Fri_movie, Integer> friend_movie_id;
	@FXML
	private TableColumn<Fri_movie, String> friend_movie_title;
	@FXML
	private TableColumn<Fri_movie, Double> friend_movie_score;
	@FXML
	private TableColumn<Fri_movie, String> friend_movie_reason;
	@FXML
	private TableColumn<Fri_movie, String> friend_movie_date;
	private List<Fri_movie> friend_movie = new ArrayList<Fri_movie>();// 数据集
	ObservableList<Fri_movie> friend_movie_list = FXCollections.observableArrayList();// JavaFX数据集合

	// 电影分类
	// 类型
	@FXML
	private RadioButton allType;
	@FXML
	private RadioButton type_jq;
	@FXML
	private RadioButton type_xj;
	@FXML
	private RadioButton type_dz;
	@FXML
	private RadioButton type_aq;
	@FXML
	private RadioButton type_kh;
	@FXML
	private RadioButton type_dh;
	@FXML
	private RadioButton type_kb;
	@FXML
	private RadioButton type_mx;
	@FXML
	private RadioButton type_qh;
	@FXML
	private RadioButton type_zn;
	// 地区
	@FXML
	private RadioButton allCountry;
	@FXML
	private RadioButton country_cn;
	@FXML
	private RadioButton country_us;
	@FXML
	private RadioButton country_fr;
	@FXML
	private RadioButton country_usa;
	@FXML
	private RadioButton country_jp;
	@FXML
	private RadioButton country_sp;
	@FXML
	private RadioButton country_rus;
	@FXML
	private RadioButton country_other;
	// 年代
	@FXML
	private RadioButton allYear;
	@FXML
	private RadioButton year_2019;
	@FXML
	private RadioButton year_2010;
	@FXML
	private RadioButton year_2000;
	@FXML
	private RadioButton year_other;
	@FXML
	private Button button_ensure_load;
	@FXML
	private TableView<Movie> table_diff_movie; // 系统推荐电影表tableview
	@FXML
	private TableColumn<Movie, String> diff_movie_title;// 列绑定
	@FXML
	private TableColumn<Movie, Integer> diff_movie_id;// 列绑定
	@FXML
	private TableColumn<Movie, Double> diff_movie_score;
	@FXML
	private TableColumn<Movie, Integer> diff_movie_year;
	@FXML
	private TableColumn<Movie, String> diff_movie_country;
	private List<Movie> diff_movie = new ArrayList<Movie>();// 数据集
	ObservableList<Movie> diff_list = FXCollections.observableArrayList();// JavaFX数据集合
	private int type_index = 0;
	private int country_index = 0;
	private int year_index = 0;
	private int typeid = 0;
	private String country = "全部地区";
	private int year = 0;

	// 搜索电影
	@FXML
	private TableView<Movie> table_movie_search;
	@FXML
	private TableColumn<Movie, String> search_movie_name;
	@FXML
	private TableColumn<Movie, Double> search_movie_score;
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

	// 好友列表显示
	@FXML
	private Text delete_friend_notice;
	@FXML
	private TableView<Friend> table_friends; // 好友列表tableview
	@FXML
	private TableColumn<Friend, Integer> friend_id;
	@FXML
	private TableColumn<Friend, String> friend_name;
	@FXML
	private TableColumn<Friend, String> friend_pre;
	@FXML
	private TableColumn<Friend, String> friend_des;
	private List<Friend> friends = new ArrayList<Friend>();
	ObservableList<Friend> f = FXCollections.observableArrayList();
	// 好友请求列表显示
	@FXML
	private TableView<Friend_request> table_friend_requests; // 好友列表tableview
	@FXML
	private TableColumn<Friend_request, Integer> col_requestid;
	@FXML
	private TableColumn<Friend_request, Integer> col_request_userid;;
	@FXML
	private TableColumn<Friend_request, String> col_request_userName;
	@FXML
	private TableColumn<Friend_request, String> col_request_userPre;
	@FXML
	private TableColumn<Friend_request, String> col_request_userIntro;
	@FXML
	private TableColumn<Friend_request, String> col_request_date;
	private List<Friend_request> list_friend_request = new ArrayList<Friend_request>();
	ObservableList<Friend_request> friend_request = FXCollections.observableArrayList();

	// 好友搜索
	@FXML
	private Button button_findUser;
	@FXML
	private Button button_addFriend;
	@FXML
	private TextField text_name;
	@FXML
	private TextField text_search_id;
	@FXML
	private TextArea text_pre;
	@FXML
	private TextArea text_userIntro;
	@FXML
	private Text search_notice;
	@FXML
	private Text add_notice;

	// 用户个人信息
	@FXML
	private Button button_modify;
	@FXML
	private Text update_notice;
	@FXML
	private PasswordField update_pass;
	@FXML
	private TextField userId;
	@FXML
	private TextField user_name;
	@FXML
	private TextArea user_intro;
	@FXML
	private TableView<Fri_movie> table_watched_movies; // 看过的电影tableview
	@FXML
	private TableColumn<Fri_movie, Integer> watched_movie_id;
	@FXML
	private TableColumn<Fri_movie, String> watched_movie_title;
	@FXML
	private TableColumn<Fri_movie, Double> watched_movie_score;
	@FXML
	private TableColumn<Fri_movie, String> watched_movie_comment;
	private List<Fri_movie> list_watched_movie = new ArrayList<Fri_movie>();
	ObservableList<Fri_movie> oblist_watched_movie = FXCollections.observableArrayList();
	// 喜好列表
	@FXML
	private CheckBox box_jq;
	@FXML
	private CheckBox box_xj;
	@FXML
	private CheckBox box_dz;
	@FXML
	private CheckBox box_aq;
	@FXML
	private CheckBox box_kh;
	@FXML
	private CheckBox box_dh;
	@FXML
	private CheckBox box_kb;
	@FXML
	private CheckBox box_mx;
	@FXML
	private CheckBox box_qh;
	@FXML
	private CheckBox box_zn;

	private static int user_id = 0;
	private static int search_user_id = 0;

	private static int times = -1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		text_pre.setWrapText(true);
		user_intro.setWrapText(true);
//		user_prefer.setWrapText(true);
		text_userIntro.setWrapText(true);// 设置自动换行

		setDoubleClick();
		setGroup();

		configTable();
		showRequests();
		showData();
		showFriends();
		showFriend_recommend();
		showUserInfo();
		showWatchedMovie();
	}

	public void refresh() {
		delete_friend_notice.setText("");
		search_notice.setText("");
		add_notice.setText("");
		update_notice.setText("");

		system_movie = database.Database_getMovie.sys_recommend(user_id, times);
		system_list.setAll(system_movie);
		table_movie_system.setItems(system_list);

		showRequests();
		showFriends();
		showFriend_recommend();
		showUserInfo();
		showWatchedMovie();
	}

	// 单选按钮按钮组管理
	private void setGroup() {
		ToggleGroup group_type = new ToggleGroup();
		allType.setUserData(0);
		allType.setToggleGroup(group_type);
		type_jq.setUserData(1);
		type_jq.setToggleGroup(group_type);
		type_xj.setUserData(2);
		type_xj.setToggleGroup(group_type);
		type_dz.setUserData(3);
		type_dz.setToggleGroup(group_type);
		type_aq.setUserData(4);
		type_aq.setToggleGroup(group_type);
		type_kh.setUserData(5);
		type_kh.setToggleGroup(group_type);
		type_dh.setUserData(6);
		type_dh.setToggleGroup(group_type);
		type_kb.setUserData(7);
		type_kb.setToggleGroup(group_type);
		type_mx.setUserData(8);
		type_mx.setToggleGroup(group_type);
		type_qh.setUserData(9);
		type_qh.setToggleGroup(group_type);
		type_zn.setUserData(10);
		type_zn.setToggleGroup(group_type);

		group_type.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (group_type.getSelectedToggle() != null) {
					type_index = Integer.parseInt(group_type.getSelectedToggle().getUserData().toString());
//					System.out.println(type_index);
				}
			}
		});

		ToggleGroup group_country = new ToggleGroup();
		allCountry.setUserData(0);
		allCountry.setToggleGroup(group_country);
		country_cn.setUserData(1);
		country_cn.setToggleGroup(group_country);
		country_us.setUserData(2);
		country_us.setToggleGroup(group_country);
		country_fr.setUserData(3);
		country_fr.setToggleGroup(group_country);
		country_usa.setUserData(4);
		country_usa.setToggleGroup(group_country);
		country_jp.setUserData(5);
		country_jp.setToggleGroup(group_country);
		country_sp.setUserData(6);
		country_sp.setToggleGroup(group_country);
		country_rus.setUserData(7);
		country_rus.setToggleGroup(group_country);
		country_other.setUserData(8);
		country_other.setToggleGroup(group_country);
		group_country.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (group_country.getSelectedToggle() != null) {
					country_index = Integer.parseInt(group_country.getSelectedToggle().getUserData().toString());
//					System.out.println(country_index);
				}
			}
		});

		ToggleGroup group_year = new ToggleGroup();
		allYear.setUserData(0);
		allYear.setToggleGroup(group_year);
		year_2019.setUserData(1);
		year_2019.setToggleGroup(group_year);
		year_2010.setUserData(2);
		year_2010.setToggleGroup(group_year);
		year_2000.setUserData(3);
		year_2000.setToggleGroup(group_year);
		year_other.setUserData(4);
		year_other.setToggleGroup(group_year);
		group_year.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (group_year.getSelectedToggle() != null) {
					year_index = Integer.parseInt(group_year.getSelectedToggle().getUserData().toString());
//					System.out.println(year_index);
				}
			}
		});
	}

	// 获取电影列表
	public void getDiffMovie() {
		index_type();
		diff_movie = database.Database_getMovie.classify_movie(typeid, country, year);
		diff_list.setAll(diff_movie);
		table_diff_movie.setItems(diff_list);

	}

	// 序号与列表绑定
	public void index_type() {
		switch (type_index) {
		case 0:
			typeid = 0;
			break;
		case 1:
			typeid = 1;
			break;
		case 2:
			typeid = 2;
			break;
		case 3:
			typeid = 3;
			break;
		case 4:
			typeid = 4;
			break;
		case 5:
			typeid = 5;
			break;
		case 6:
			typeid = 6;
			break;
		case 7:
			typeid = 9;
			break;
		case 8:
			typeid = 19;
			break;
		case 9:
			typeid = 23;
			break;
		case 10:
			typeid = 22;
			break;
		default:
			typeid = 0;
			break;
		}

		switch (country_index) {
		case 0:
			country = "全部地区";
			break;
		case 1:
			country = "中国";
			break;
		case 2:
			country = "英国";
			break;
		case 3:
			country = "法国";
			break;
		case 4:
			country = "美国";
			break;
		case 5:
			country = "日本";
			break;
		case 6:
			country = "西班牙";
			break;
		case 7:
			country = "俄罗斯";
			break;
		case 8:
			country = "其他地区";
			break;
		default:
			country = "全部地区";
			break;
		}

		switch (year_index) {
		case 0:
			year = 0;
			break;
		case 1:
			year = 2019;
			break;
		case 2:
			year = 2010;
			break;
		case 3:
			year = 2000;
			break;
		case 4:
			year = -1;
			break;
		default:
			year = 0;
			break;
		}
	}

	// 双击响应
	private void setDoubleClick() {
		// 双击响应
		table_movie_system.setRowFactory(tv -> {
			TableRow<Movie> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Movie rowData = row.getItem();
					try {
						showMovie(rowData.getId(), user_id);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println("Double click on: " + rowData.getId());
				}
			});
			return row;
		});

		table_friends.setRowFactory(tv -> {
			TableRow<Friend> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Friend rowData = row.getItem();
					try {

						boolean flag = database.Database_delete.delete_friend(user_id, rowData.getId());
						if (flag) {
							delete_friend_notice.setText("刪除成功!");
							showFriends();
						}
//						showMovie(rowData.getId(), user_id);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println("Double click on: " + rowData.getId());
				}
			});
			return row;
		});

		// 双击响应
		table_movie_friend.setRowFactory(tv -> {
			TableRow<Fri_movie> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Fri_movie rowData = row.getItem();
					try {
						showMovie(rowData.getId(), user_id);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println("Double click on: " + rowData.getId());
				}
			});
			return row;
		});

		table_movie_search.setRowFactory(tv -> {
			TableRow<Movie> row = new TableRow<>();

			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Movie rowData = row.getItem();
					try {
						showMovie(rowData.getId(), user_id);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println("Double click on: " + rowData.getId());
				}
			});
			return row;
		});

		table_diff_movie.setRowFactory(tv -> {
			TableRow<Movie> row = new TableRow<>();

			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Movie rowData = row.getItem();
					try {
						showMovie(rowData.getId(), user_id);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println("Double click on: " + rowData.getId());
				}
			});
			return row;
		});

		table_watched_movies.setRowFactory(tv -> {
			TableRow<Fri_movie> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Fri_movie rowData = row.getItem();
					try {
						showMovie(rowData.getId(), user_id);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println("Double click on: " + rowData.getId());
				}
			});
			return row;
		});

	}

	// 表格的列绑定
	private void configTable() {
		// 系统推荐电影列表绑定
		system_movie_title.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
		system_movie_score.setCellValueFactory(new PropertyValueFactory<Movie, Double>("star"));
		system_movie_year.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year_released"));
		system_movie_country.setCellValueFactory(new PropertyValueFactory<Movie, String>("country"));
		system_movie_id.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));
		// 好友列表绑定
		friend_id.setCellValueFactory(new PropertyValueFactory<Friend, Integer>("id"));
		friend_name.setCellValueFactory(new PropertyValueFactory<Friend, String>("name"));
		friend_pre.setCellValueFactory(new PropertyValueFactory<Friend, String>("pre"));
		friend_des.setCellValueFactory(new PropertyValueFactory<Friend, String>("des"));
		// 好友推荐电影列表绑定
		friend_movie_id.setCellValueFactory(new PropertyValueFactory<Fri_movie, Integer>("id"));
		friend_movie_title.setCellValueFactory(new PropertyValueFactory<Fri_movie, String>("title"));
		friend_movie_score.setCellValueFactory(new PropertyValueFactory<Fri_movie, Double>("star"));
		friend_movie_reason.setCellValueFactory(new PropertyValueFactory<Fri_movie, String>("reason"));
		friend_movie_date.setCellValueFactory(new PropertyValueFactory<Fri_movie, String>("date"));
		recommend_friend_name.setCellValueFactory(new PropertyValueFactory<Fri_movie, String>("name"));
		// 查询电影列表绑定
		search_movie_name.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
		search_movie_score.setCellValueFactory(new PropertyValueFactory<Movie, Double>("star"));
		search_movie_year.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year_released"));
		search_movie_country.setCellValueFactory(new PropertyValueFactory<Movie, String>("country"));
		search_movie_id.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));

		// 看过的电影列表绑定
		watched_movie_id.setCellValueFactory(new PropertyValueFactory<Fri_movie, Integer>("id"));
		watched_movie_title.setCellValueFactory(new PropertyValueFactory<Fri_movie, String>("title"));
		watched_movie_score.setCellValueFactory(new PropertyValueFactory<Fri_movie, Double>("star"));
		watched_movie_comment.setCellValueFactory(new PropertyValueFactory<Fri_movie, String>("reason"));
		// 电影分类列表绑定
		diff_movie_title.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
		diff_movie_score.setCellValueFactory(new PropertyValueFactory<Movie, Double>("star"));
		diff_movie_year.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year_released"));
		diff_movie_country.setCellValueFactory(new PropertyValueFactory<Movie, String>("country"));
		diff_movie_id.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));

		col_requestid.setCellValueFactory(new PropertyValueFactory<Friend_request, Integer>("requestid"));
		col_request_userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
		col_request_userid.setCellValueFactory(new PropertyValueFactory<>("userid"));
		col_request_userPre.setCellValueFactory(new PropertyValueFactory<>("userPre"));
		col_request_userIntro.setCellValueFactory(new PropertyValueFactory<>("userIntro"));
		col_request_date.setCellValueFactory(new PropertyValueFactory<>("date"));

	}

	// 将数据添加到显示的表格中，显示任何
	public void showData() {
		times++;
		if (times == -1)
			times = 0;
		system_movie = database.Database_getMovie.sys_recommend(user_id, times);
		system_list.setAll(system_movie);
		table_movie_system.setItems(system_list);
	}

	// 显示请求
	private void showRequests() {
		list_friend_request = database.Database_addFriends.show_friend_request(user_id);
		friend_request.setAll(list_friend_request);
		table_friend_requests.setItems(friend_request);

		table_friend_requests.setRowFactory(tv -> {
			TableRow<Friend_request> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Friend_request rowData = row.getItem();
					try {
						Friend_request_UI second = new Friend_request_UI();
						second.showWindow(rowData.getRequestid());

//						System.out.println(rowData.getUserid());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println("Double click on: " + rowData.getId());
				}
			});
			return row;
		});
	}

	// 显示电影详细信息
	private void showMovie(int movieid, int userid) throws Exception {
		application.Movie_UI second = new Movie_UI();
		second.showWindow(movieid, userid);
	}

	// 设置登录的用户的id
	public static void setUser(int id) {
		user_id = id;
		System.out.println(user_id);
	}

	// 显示搜索的好友
	public boolean showSearchUser() throws Exception {
		search_notice.setText("");
		add_notice.setText("");
		boolean check = false;
		String get_id = text_search_id.getText();
		if (isDigit(get_id)) {
			int id = Integer.parseInt(get_id) - 10000;
			if (id > 0) {
				String[] user_info = database.Database_userInfo.get_user_info(id);
				if (user_info[0] != null) {
					text_name.setText(user_info[0]);
					text_pre.setText(user_info[1]);
					text_userIntro.setText(user_info[2]);
					search_user_id = id;
					check = true;
				} else {
					text_name.setText("");
					text_pre.setText("");
					text_userIntro.setText("");
					search_user_id = 0;
					search_notice.setText("您查找的用户不存在!");
					search_notice.setFill(Color.RED);
				}
			} else {
				text_name.setText("");
				text_pre.setText("");
				text_userIntro.setText("");
				search_user_id = 0;
				search_notice.setText("您查找的用户不存在!");
				search_notice.setFill(Color.RED);
			}
		} else {
			text_name.setText("");
			text_pre.setText("");
			text_userIntro.setText("");
			search_user_id = 0;
			search_notice.setText("请输入正确的账号格式！");
			search_notice.setFill(Color.RED);
		}
		return check;
	}

	// 添加好友
	public void add_friend() throws Exception {
		add_notice.setText("");
		if (showSearchUser()) {
			boolean check = database.Database_addFriends.add_friend_request(user_id, search_user_id);
			if (check) {
				showFriends();
				add_notice.setText("添加成功!");
				add_notice.setFill(Color.RED);
			} else {
				add_notice.setText("添加失败!");
				add_notice.setFill(Color.RED);
			}
		}
	}

	// 显示好友列表
	public void showFriends() {
		friends = database.Database_addFriends.showFriends(user_id);
		f.setAll(friends);
		table_friends.setItems(f);
	}

	// 显示好友推荐
	public void showFriend_recommend() {
		friend_movie = database.Database_getMovie.friend_recommend(user_id);
		friend_movie_list.setAll(friend_movie);
		table_movie_friend.setItems(friend_movie_list);
	}

	// 显示搜索的电影
	public void showSearchMovie() {
		String title = search_movie.getText();
		search_movie_list = database.Database_getMovie.search_movie(title);
		search_list.setAll(search_movie_list);
		table_movie_search.setItems(search_list);
	}

	// 显示用户基本信息
	public void showUserInfo() {
		String[] info = database.Database_userInfo.get_user_info(user_id);
		userId.setText((user_id + 10000) + "");
		user_name.setText(info[0]);
		user_intro.setText(info[2]);

		boolean[] box_list = database.Database_userInfo.getUserPre(user_id);
		CheckBox[] box = { box_jq, box_xj, box_dz, box_aq, box_kh, box_dh, box_kb, box_mx, box_qh, box_zn };
		for (int i = 0; i < 10; i++) {
			if (box_list[i])
				box[i].setSelected(true);
		}

	}

	// 更改用户信息
	public void updateUserInfo() {
		CheckBox[] box = { box_jq, box_xj, box_dz, box_aq, box_kh, box_dh, box_kb, box_mx, box_qh, box_zn };
		int[] preference = new int[10];
		for (int i = 0; i < 10; i++) {
			if (box[i].isSelected()) {
				preference[i] = 1;
			} else
				preference[i] = 0;
		}
		database.DatabaseUtil.userPreference(user_id, preference);

		String newPass = update_pass.getText().replaceAll(" ", "");
		if (!newPass.isEmpty()) {
			boolean flag = database.DatabaseUtil.updateUserPass(user_id, newPass);
			System.out.println(newPass + ",,,");
		}
		String newIntro = user_intro.getText().replaceAll(" ", "");
		if (newIntro != "") {
			boolean flag = database.DatabaseUtil.updateUserInfo(user_id, newIntro);
			if (flag)
				update_notice.setText("修改成功!");
		}
	}

	// 显示看过的电影
	public void showWatchedMovie() {
		list_watched_movie = database.Database_getMovie.show_recently_watched(user_id);
		oblist_watched_movie.setAll(list_watched_movie);
		table_watched_movies.setItems(oblist_watched_movie);
	}

	// 判断一个字符串是否全为数字
	public static boolean isDigit(String strNum) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) strNum);
		return matcher.matches();
	}

}
