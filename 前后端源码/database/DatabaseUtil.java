package database;

import java.sql.*;

public class DatabaseUtil {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 驱动
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // 地址
	private static final String USER = "root"; // 用户名
	private static final String PASS = "123456qwer"; // 密码

	// 管理员登录验证
	public static boolean checkAdmin(int account, String password) {
		boolean checkbool = false;
		Connection con = null; // 定义一个MYSQL链接对象
		Statement stmt = null; // 创建声明
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			stmt = con.createStatement();
			String password_fromDb;

			String selectSql = "SELECT pass FROM admin where id_Admin='" + account + "' limit 1;";
			ResultSet selectRes = stmt.executeQuery(selectSql);
			if (selectRes.next()) {
				password_fromDb = selectRes.getString("pass");
				if (password_fromDb.equals(password)) {
					checkbool = true;
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println("管理员登陆检查----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return checkbool;
	}

	// 用户登录验证
	public static boolean checkUser(int account, String password) {
		boolean checkbool = false;
		Connection con = null; // 定义一个MYSQL链接对象
		Statement stmt = null; // 创建声明
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			stmt = con.createStatement();
			String password_fromDb;

			String selectSql = "SELECT password FROM user where id_user='" + account + "' limit 1;";
			ResultSet selectRes = stmt.executeQuery(selectSql);
			if (selectRes.next()) {
				password_fromDb = selectRes.getString("password");
				if (password_fromDb.equals(password)) {
					checkbool = true;
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println("用户登陆检查----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return checkbool;
	}

	// 用户注册，得到用戶的信息，向数据库传送
	public static int userSignUp(String[] info) {
		int key = -1;
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			String sql = "INSERT INTO user (user_name,password,decribe) VALUES(?,?,?)";// SQL语句
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// 用来执行sql语句查询，对SQL语句进行预编译处理,获取自动增加的id号
			pst.setString(1, info[0]);
			pst.setString(2, info[1]);
			pst.setString(3, info[2]);
			int line = pst.executeUpdate();// 可执行增删改语句，返回受到影响的行数
//			System.out.println(line);
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next())
				key = rs.getInt(1);
			System.out.println("key:" + key);
			con.close();
		} catch (Exception e) {
			System.out.println("用户注册----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return key;
	}

	// 用户密保问题答案
	public static int userAnswer(int key, String[] ans) {
		int line = 0;
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			String sql = "INSERT INTO question_answer (user_id,question_id,answer) VALUES(?,?,?)";// SQL语句
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// 用来执行sql语句查询，对SQL语句进行预编译处理,获取自动增加的id号
			for (int i = 1; i <= 3; i++) {
				pst.setLong(1, key);
				pst.setLong(2, i);
				pst.setString(3, ans[i - 1]);
				line += pst.executeUpdate();// 可执行增删改语句，返回受到影响的行数
			}

//			System.out.println(line);
			con.close();
		} catch (Exception e) {
			System.out.println("用户注册----checkUser----MYSQL ERROR:" + e.getMessage());
		}

		return line;
	}

	// 用户喜好列表
	public static int userPreference(int key, int[] pre) {
		int line = -1;
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			String sql_delete = "delete from preference where id_user= " + key + " ;";
			PreparedStatement pst_delete = con.prepareStatement(sql_delete);
			pst_delete.executeUpdate();

			String sql = "INSERT INTO preference(id_user,movie_type) VALUES(?,?)";// SQL语句
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// 用来执行sql语句查询，对SQL语句进行预编译处理,获取自动增加的id号
			pst.setInt(1, key);
			int type = 0;
			for (int i = 0; i < 10; i++) {
				if (pre[i] != 0) {
					switch (i) {
					case 0:
						type = 1;
						break;
					case 1:
						type = 2;
						break;
					case 2:
						type = 3;
						break;
					case 3:
						type = 4;
						break;
					case 4:
						type = 5;
						break;
					case 5:
						type = 6;
						break;
					case 6:
						type = 9;
						break;
					case 7:
						type = 19;
						break;
					case 8:
						type = 18;
						break;
					case 9:
						type = 22;
						break;
					default:
						type = 1;
						break;
					}
					pst.setInt(2, type);
					line += pst.executeUpdate();// 可执行增删改语句，返回受到影响的行数
//					System.out.println("key:"+key+" type:"+type);
				}

			}

			System.out.println(line);
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next())
				key = rs.getInt(1);
//			System.out.println("key:" + key);
			con.close();
		} catch (Exception e) {
			System.out.println("用户注册----checkUser----MYSQL ERROR:" + e.getMessage());
		}

		return line;
	}

	// 更新用户信息
	public static boolean updateUserInfo(int key, String intro) {
		boolean flag = false;
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			String sql = "update user set decribe= '" + intro + "' where id_user= " + key + " ;";// SQL语句
			PreparedStatement pst = con.prepareStatement(sql);// 用来执行sql语句查询，对SQL语句进行预编译处理,获取自动增加的id号
			int line = pst.executeUpdate();// 可执行增删改语句，返回受到影响的行数
			if (line > 0)
				flag = true;
//			System.out.println(line);
			con.close();
		} catch (Exception e) {
			System.out.println("用户注册----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return flag;
	}

	// 更新用户密码
	public static boolean updateUserPass(int key, String pass) {
		boolean flag = false;
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			String sql = "update user set password= '" + pass + "' where id_user= " + key + " ;";// SQL语句
			PreparedStatement pst = con.prepareStatement(sql);// 用来执行sql语句查询，对SQL语句进行预编译处理,获取自动增加的id号
			int line = pst.executeUpdate();// 可执行增删改语句，返回受到影响的行数
			if (line > 0)
				flag = true;
//			System.out.println(line);
			con.close();
		} catch (Exception e) {
			System.out.println("用户注册----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return flag;
	}

}
