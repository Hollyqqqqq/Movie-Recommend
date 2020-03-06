package database;

import java.sql.*;

public class Database_databaseState {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 驱动
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // 地址
	private static final String USER = "root"; // 用户名
	private static final String PASS = "123456qwer"; // 密码

	public static int getUserNum() {
		int num = 0;
		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = con.createStatement();
			String sql = "select count(*) cnt from user;";
			ResultSet rst = stmt.executeQuery(sql);
			if (rst.next()) {
				num = rst.getInt("cnt");
			}
			con.close();
		} catch (Exception e) {
			System.out.println("getUserNum-------MySQL error:" + e.getMessage());
		}
		return num;
	}

	public static int getMovieNum() {
		int num = 0;
		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = con.createStatement();
			String sql = "select count(*) cnt from movies;";
			ResultSet rst = stmt.executeQuery(sql);
			if (rst.next()) {
				num = rst.getInt("cnt");
			}
			con.close();
		} catch (Exception e) {
			System.out.println("getUserNum-------MySQL error:" + e.getMessage());
		}
		return num;
	}

	public static int getCountryNum() {
		int num = 0;
		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = con.createStatement();
			String sql = "SELECT COUNT(DISTINCT country) cnt FROM movies;";
			ResultSet rst = stmt.executeQuery(sql);
			if (rst.next()) {
				num = rst.getInt("cnt");
			}
			con.close();
		} catch (Exception e) {
			System.out.println("getUserNum-------MySQL error:" + e.getMessage());
		}
		return num;
	}

	public static int getTypeNum() {
		int num = 0;
		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = con.createStatement();
			String sql = "select count(distinct id_type) cnt from movie_types;";
			ResultSet rst = stmt.executeQuery(sql);
			if (rst.next()) {
				num = rst.getInt("cnt");
			}
			con.close();
		} catch (Exception e) {
			System.out.println("getUserNum-------MySQL error:" + e.getMessage());
		}
		return num;
	}

}
