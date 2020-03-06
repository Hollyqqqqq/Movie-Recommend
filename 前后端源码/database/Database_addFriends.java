package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import entity.Friend;
import entity.Friend_request;

public class Database_addFriends {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 驱动
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // 地址
	private static final String USER = "root"; // 用户名
	private static final String PASS = "123456qwer"; // 密码

	// 好友添加
	public static boolean addFriend(int user_id, int friend_id) {
		boolean check = false;
		String result = "";
		Connection con = null; // 定义一个MYSQL链接对象
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			String sql = "{CALL add_friend(?,?,?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, user_id);
			cstm.setLong(2, friend_id);
			cstm.registerOutParameter(3, Types.VARCHAR);
			cstm.execute();
			result = cstm.getString(3);
			if (result.equals("true"))
				check = true;
			con.close();
		} catch (Exception e) {
			System.out.println("MYSQL ERROR:" + e.getMessage());
		}
		return check;
	}

	// 好友列表
	public static List<Friend> showFriends(int user_id) {
		List<Friend> friends = new ArrayList<Friend>();
		int id;
		String name, pre, des;
		Connection con = null; // 定义一个MYSQL链接对象
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			String sql = "{CALL show_friends(?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, user_id);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					id = rs.getInt("id_user") + 10000;
					name = rs.getString("user_name");
					pre = rs.getString("preference");
					des = rs.getString("decribe");
					Friend f = new Friend(id, name, pre, des);
					friends.add(f);
//					System.out.println("id:" + id);
				}
				hadResults = cstm.getMoreResults();
			}

			con.close();
		} catch (Exception e) {
			System.out.println("找回密码检查----checkUser----MYSQL ERROR:" + e.getMessage());
		}

		return friends;
	}

	// 添加好友请求
	public static boolean add_friend_request(int user_id, int friend_id) {
		boolean check = false;
		String result = "";
		Connection con = null; // 定义一个MYSQL链接对象
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			String sql = "{CALL add_friend_request(?,?,?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, user_id);
			cstm.setLong(2, friend_id);
			cstm.registerOutParameter(3, Types.VARCHAR);
			cstm.execute();
			result = cstm.getString(3);
			if (result.equals("true"))
				check = true;
			con.close();
		} catch (Exception e) {
			System.out.println("添加好友请求----MYSQL ERROR:" + e.getMessage());
		}
		return check;
	}

	// 显示好友请求
	public static List<Friend_request> show_friend_request(int idUser) {
		int userid = 0;
		int requestid = 0;
		String username = "", userpre = "", userintro = "", date = "";
		List<Friend_request> frs = new ArrayList<>();
		Connection con = null; // 定义一个MYSQL链接对象
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			String sql = "{CALL show_friend_request(?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, idUser);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					username = rs.getString("user_name");
					userpre = rs.getString("preference");
					userid = rs.getInt("id_user");
					userintro = rs.getString("decribe");
					date = rs.getString("date");
					requestid = rs.getInt("requestid");
					Friend_request fr = new Friend_request(requestid, userid + 10000, username, userpre, userintro,
							date);
					frs.add(fr);
				}
				hadResults = cstm.getMoreResults();
			}
			con.close();
		} catch (Exception e) {
			System.out.println("显示好友请求----MYSQL ERROR:" + e.getMessage());
		}
		return frs;
	}

	// 同意好友请求
	public static void agree_add_friend(int idresquest) {
		boolean check = false;
		String result = "";
		Connection con = null; // 定义一个MYSQL链接对象
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			String sql = "{CALL agree_add_friend(?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, idresquest);
			cstm.execute();
			con.close();
		} catch (Exception e) {
			System.out.println("同意好友请求----MYSQL ERROR:" + e.getMessage());
		}
	}

}
