package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Comments_movie;
import entity.Fri_movie;
import entity.Movie;
import entity.people;

public class Database_getMovie {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // ����
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // ��ַ
	private static final String USER = "root"; // �û���
	private static final String PASS = "123456qwer"; // ����

	// ��ȡϵͳ�Ƽ���Ӱ
	public static List<Movie> sys_recommend(int id, int times) {
		List<Movie> sys_movie = new ArrayList<Movie>();
		String title = "", type = "", country = "", intro = "", director = "", actor = "";
		int movie_id = 0, year_released = 0, runtime = 0;
		double star = 0;
		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "{CALL sys_recommend(?,?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, id);
			cstm.setLong(2, times);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					movie_id = rs.getInt("id_movie");
					star = rs.getDouble("star");
					title = rs.getString("title");
					country = rs.getString("country");
					year_released = rs.getInt("year_released");
					Movie m = new Movie(movie_id, title, type, year_released, country, star, intro, runtime, director,
							actor);
					sys_movie.add(m);
				}
				hadResults = cstm.getMoreResults();
			}

			con.close();
		} catch (Exception e) {
			System.out.println("�һ�������----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return sys_movie;
	}

	// ��ȡ�����Ƽ��б�
	public static List<Fri_movie> friend_recommend(int id) {
		List<Fri_movie> friend_movie = new ArrayList<Fri_movie>();
		String name = "", title = "", reason = "", date = "";
		int movie_id = 0;
		double star = 0;
		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "{CALL fri_recommend(?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, id);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					name = rs.getString("user_name");
					movie_id = rs.getInt("movie_id");
					title = rs.getString("title");
					star = rs.getDouble("star");
					reason = rs.getString("reason");
					date = rs.getString("date");
					Fri_movie m = new Fri_movie(name, movie_id, title, star, reason, date);
					friend_movie.add(m);
				}
				hadResults = cstm.getMoreResults();
			}

			con.close();
		} catch (Exception e) {
			System.out.println("MYSQL ERROR:" + e.getMessage());
		}
		return friend_movie;
	}

	// ��Ӱ��ϸ��Ϣ��ʾ
	public static Movie show_movie_detail(int id) {
		String title = "", types = "", country = "", intro = "", director = "", actor = "";
		int year_released = 0, runtime = 0;
		double star = 0;
		Movie m = new Movie(id, title, types, year_released, country, star, intro, runtime, director, actor);
		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "{CALL show_movie_detail(?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, id);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					star = rs.getDouble("star");
					title = rs.getString("title");
					country = rs.getString("country");
					year_released = rs.getInt("year_released");
					runtime = rs.getInt("runtime");
					types = rs.getString("types");
//					director = rs.getString("director");
//					actor = rs.getString("actor");
					intro = rs.getString("intro");
					m = new Movie(id, title, types, year_released, country, star, intro, runtime, director, actor);
				}
				hadResults = cstm.getMoreResults();
			}

			con.close();
		} catch (Exception e) {
			System.out.println("�һ�������----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return m;
	}

	// ��ʾ��Ӱ����
	public static List<Comments_movie> show_movie_comments(int id) {
		List<Comments_movie> c = new ArrayList<Comments_movie>();
		String user_name = "", comments = "", date = "";
		double star = 0;
		Comments_movie comments_movie;
		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "{CALL show_movie_comments(?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, id);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					star = rs.getDouble("star");
					user_name = rs.getString("user_name");
					comments = rs.getString("commends");
					date = rs.getString("date");
//					System.out.println(user_name + "," + star);
					comments_movie = new Comments_movie(user_name, star, comments, date);
					c.add(comments_movie);
				}
				hadResults = cstm.getMoreResults();
			}

			con.close();
		} catch (Exception e) {
			System.out.println("�һ�������----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return c;
	}

	// ������Ӱjava
	public static List<Movie> search_movie(String subtitle) {
		List<Movie> search_movie = new ArrayList<Movie>();
		String title = "", type = "", country = "", intro = "", director = "", actor = "";
		int movie_id = 0, year_released = 0, runtime = 0;
		double star = 0;
		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "{CALL search_movie(?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setString(1, subtitle);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					movie_id = rs.getInt("id_movie");
					star = rs.getDouble("star");
					title = rs.getString("title");
					country = rs.getString("country");
					year_released = rs.getInt("year_released");
					Movie m = new Movie(movie_id, title, type, year_released, country, star, intro, runtime, director,
							actor);
					search_movie.add(m);
				}
				hadResults = cstm.getMoreResults();
			}

			con.close();
		} catch (Exception e) {
			System.out.println("MYSQL ERROR:" + e.getMessage());
		}
		return search_movie;
	}

	// ��ʾ�ۿ���ʷ
	public static List<Fri_movie> show_recently_watched(int id) {
		// ����Fri_movie,,reason �൱��recomment
		String title = "", comments = "";
		double star = 0;
		int movie_id = 0;
		List<Fri_movie> fri_movies = new ArrayList<>();
		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {
			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "{CALL show_watched_movies(?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, id);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					movie_id = rs.getInt("id_movie");
					title = rs.getString("title");
					star = rs.getDouble("star");
					comments = rs.getString("commends");
					Fri_movie fm = new Fri_movie("", movie_id, title, star, comments, "");
					fri_movies.add(fm);
				}
				hadResults = cstm.getMoreResults();
			}

			con.close();
		} catch (Exception e) {
			System.out.println("MYSQL ERROR:" + e.getMessage());
		}

//		for (Fri_movie f : fri_movies) {
//			System.out.println(f.getTitle() + "," + f.getStar() + "," + f.getReason());
//		}

		return fri_movies;
	}

	// ��Ӱ��Ա��ϸ��Ϣ
	public static List<people> show_movie_people(int id) {
		String name = "", role = "", gender = "", intro = "", birth = "";
		List<people> ps = new ArrayList<>();
		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "{CALL show_movie_people(?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, id);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					name = rs.getString("name");
					gender = rs.getString("gender");
					birth = rs.getString("born");
					intro = rs.getString("info");
					role = rs.getString("role");
					people p = new people(name, role, gender, birth, intro);
					ps.add(p);
				}
				hadResults = cstm.getMoreResults();
			}

			con.close();
		} catch (Exception e) {
			System.out.println("��Ӱ��Ա��Ϣ----MYSQL ERROR:" + e.getMessage());
		}
		return ps;
	}

	// ��Ӱ����
	public static List<Movie> classify_movie(int typeid, String country_, int year) {
		List<Movie> classify_movie = new ArrayList<Movie>();
		String title = "", type = "", country = "", intro = "", director = "", actor = "";
		int movie_id = 0, year_released = 0, runtime = 0;
		double star = 0;
//		System.out.println(typeid+","+country_+","+year);
		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "{CALL classify_movie(?,?,?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setInt(1, typeid);
			cstm.setString(2, country_);
			cstm.setInt(3, year);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					movie_id = rs.getInt("id_movie");
					star = rs.getDouble("star");
					title = rs.getString("title");
					country = rs.getString("country");
					year_released = rs.getInt("year_released");
					Movie m = new Movie(movie_id, title, type, year_released, country, star, intro, runtime, director,
							actor);
					classify_movie.add(m);
				}
				hadResults = cstm.getMoreResults();
			}

			con.close();
		} catch (Exception e) {
			System.out.println("MYSQL ERROR:" + e.getMessage());
		}

		return classify_movie;
	}

}
//ϲ����������rap������