package database;

import java.sql.*;
import java.util.ArrayList;

import entity.people;
import entity.movie_d;

public class Database_insert_movie {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 驱动
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // 地址
	private static final String USER = "root"; // 用户名
	private static final String PASS = "123456qwer"; // 密码

	// 插入电影数据
//	public static void insertmovie(ArrayList<movie_d> movie_data_t, String[][] type_data_t, people[][] people_data_t) {
//		for (int i = 0; i < movie_data_t.size(); i++) {
//			int key1 = -1, key2 = -2;
//			try {
//				Class.forName(JDBC_DRIVER);
//				Connection con1 = DriverManager.getConnection(DB_URL, USER, PASS);
//				Connection con2 = DriverManager.getConnection(DB_URL, USER, PASS);
//				Connection con3 = DriverManager.getConnection(DB_URL, USER, PASS);
//
//				String sql1 = "insert into movies (title, year_released, country, star, intro, runtime) values (?, ?, ?, ?, ?, ?);";
//				String sql2 = "{CALL find_type(?, ?)}";
//				String sql3 = "insert into movie_types values (?, ?);";
//				PreparedStatement pst1 = con1.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
//				CallableStatement pst2 = con2.prepareCall(sql2);
//				PreparedStatement pst3 = con3.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
//				// PreparedStatement pst = con.prepareStatement(sql3,
//				// Statement.RETURN_GENERATED_KEYS);
//
//				pst1.setString(1, movie_data_t.get(i).getTitle());
//				pst1.setString(2, movie_data_t.get(i).getReleased_data());
//				pst1.setString(3, movie_data_t.get(i).getCountry());
//				pst1.setDouble(4, movie_data_t.get(i).getStar());
//				pst1.setString(5, movie_data_t.get(i).getIntro());
//				pst1.setInt(6, movie_data_t.get(i).getRuntime());
//				pst1.execute();
//				ResultSet rs1 = pst1.getGeneratedKeys();
//				if (rs1.next())
//					key1 = rs1.getInt(1);
//
//				for (int j = 0; j < type_data_t[i].length; j++) {
//					if (type_data_t[i][j] != null) {
//						pst2.setString(1, type_data_t[i][j]);
//						pst2.registerOutParameter(2, Types.INTEGER);
//						pst2.execute();
//						key2 = pst2.getInt(2);
//					}
//					pst3.setInt(1, key1);
//					pst3.setInt(2, key2);
//					pst3.execute();
//				}
//				insertpeople(i, key1, people_data_t);
//
//				con1.close();
//				con2.close();
//				con3.close();
//			} catch (Exception e) {
//				System.out.println("insertMovie----MYSQL ERROR:" + e.getMessage() + " when intsert movie" + i);
//			}
//		}
//	}

	public static int[] insertmovie(ArrayList<movie_d> movie_data_t, String[][] type_data_t, people[][] people_data_t) {
		int[] result = new int[2];
		for (int i = 0; i < movie_data_t.size(); i++) {
			int key1 = -1, key2 = -2;
			try {
				Class.forName(JDBC_DRIVER);
				Connection con1 = DriverManager.getConnection(DB_URL, USER, PASS);
				Connection con2 = DriverManager.getConnection(DB_URL, USER, PASS);
				Connection con3 = DriverManager.getConnection(DB_URL, USER, PASS);

//				String sql1 = "insert into movies (title, year_released, country, star, intro, runtime) values (?, ?, ?, ?, ?, ?);";
				String sql1 = "{CALL insert_movie(?,?,?,?,?,?,?,?)}";
				String sql2 = "{CALL find_type(?, ?)}";
				String sql3 = "insert into movie_types values (?, ?);";
				CallableStatement pst1 = con1.prepareCall(sql1);
				CallableStatement pst2 = con2.prepareCall(sql2);
				PreparedStatement pst3 = con3.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
				// PreparedStatement pst = con.prepareStatement(sql3,
				// Statement.RETURN_GENERATED_KEYS);

				pst1.setString(1, movie_data_t.get(i).getTitle());
				pst1.setString(2, movie_data_t.get(i).getReleased_data());
				pst1.setString(3, movie_data_t.get(i).getCountry());
				pst1.setDouble(4, movie_data_t.get(i).getStar());
				pst1.setString(5, movie_data_t.get(i).getIntro());
				pst1.setInt(6, movie_data_t.get(i).getRuntime());
				pst1.registerOutParameter(7, Types.INTEGER);
				pst1.registerOutParameter(8, Types.INTEGER);
				pst1.execute();

				if (pst1.getInt(7) == 1)
					result[0]++;
				else
					result[1]++;

				key1 = pst1.getInt(8);

				for (int j = 0; j < type_data_t[i].length; j++) {
					if (type_data_t[i][j] != null) {
						pst2.setString(1, type_data_t[i][j]);
						pst2.registerOutParameter(2, Types.INTEGER);
						pst2.execute();
						key2 = pst2.getInt(2);
					}
					pst3.setInt(1, key1);
					pst3.setInt(2, key2);
					pst3.execute();
				}
				insertpeople(i, key1, people_data_t);

				con1.close();
				con2.close();
				con3.close();
			} catch (Exception e) {
				System.out.println("1----checkUser----MYSQL ERROR:" + e.getMessage() + " " + i);
			}
		}
		return result;
	}

	// 插入电影参与人员数据
	public static void insertpeople(int i, int key1, people[][] people_data_t) {
//		public static void insertpeople(int i, int key1, ArrayList<movie_d> movie_data_t, String[][] type_data_t,
//				people[][] people_data_t) {
		int key2 = -1;
		try {
			Class.forName(JDBC_DRIVER);
			Connection con2 = DriverManager.getConnection(DB_URL, USER, PASS);
			Connection con3 = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql2 = "{CALL find_people(?, ?, ?, ?, ?)}";
			String sql3 = "insert into credits_as values (?, ?, ?);";
			CallableStatement pst2 = con2.prepareCall(sql2);
			PreparedStatement pst3 = con3.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);

			for (int j = 0; j < people_data_t[i].length; j++) {
				if (people_data_t[i][j] != null) {
					pst2.setString(1, people_data_t[i][j].getName());
					pst2.setString(2, people_data_t[i][j].getGender());
					pst2.setString(3, people_data_t[i][j].getBirth());
					pst2.setString(4, people_data_t[i][j].getBreif_intro());
					pst2.registerOutParameter(5, Types.INTEGER);
					pst2.execute();
					key2 = pst2.getInt(5); // 得到peopleid
					pst3.setInt(1, key1); // key1 new_movie_id
					pst3.setInt(2, key2); // key2 peopleid
					pst3.setString(3, people_data_t[i][j].getCredit());
//					System.out.println(i + ":" + people_data_t[i][j].getName() + "," + people_data_t[i][j].getCredit());
					pst3.execute();
				}

			}

			con2.close();
			con3.close();
		} catch (Exception e) {
			System.out.println("insert people----MYSQL ERROR:" + e.getMessage() + " at " + i);
		}
		// }
	}

	// 插入看过的电影
	public static boolean insertWatchedMovie(int userid, int movieid, int score, String comm) {
//		insert into watched_movie (id_user, id_movie, star, commends)
//		values (1, 33, 0, '');
		boolean flag = false;
		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "call replace_watched_movie(?,?,?,?);";
//			String sql = "insert into watched_movie (id_user, id_movie, star, commends) values (?, ?, ?, ?);";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setInt(1, userid);
			cstm.setInt(2, movieid);
			cstm.setInt(3, score);
			cstm.setString(4, comm);
			cstm.execute();
			int line = cstm.getUpdateCount();
			if (line > 0)
				flag = true;
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("插入看过的电影----insertWatchedMovie----MYSQL ERROR:" + e.getMessage());
		}
		return flag;
	}

	// 插入好友推荐电影
	public static boolean insertFriendRecommendMovie(int userid, int friendid, int movieid, String comm) {
		boolean flag = false;
		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "call recommend_movie(?,?,?,?);";
//			String sql = "insert into watched_movie (id_user, id_movie, star, commends) values (?, ?, ?, ?);";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setInt(1, userid);
			cstm.setInt(2, friendid);
			cstm.setInt(3, movieid);
			cstm.setString(4, comm);
			cstm.execute();
			int line = cstm.getUpdateCount();
			System.out.println(line + "--");
			if (line > 0)
				flag = true;
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("插入看过的电影----insertWatchedMovie----MYSQL ERROR:" + e.getMessage());
		}
		return flag;
	}

}
