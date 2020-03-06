package database;

import java.sql.*;

public class Database_deleteMovie {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 驱动
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // 地址
	private static final String USER = "root"; // 用户名
	private static final String PASS = "123456qwer"; // 密码

	// 删除电影，，要先调用search_movie,然后选定id传进来
	public static boolean delete_movie(int id) {
		boolean delete = false;
		int line = 0;
		Connection con = null; // 定义一个MYSQL链接对象
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			String sql = "{CALL delete_movie(?,?)}";
			CallableStatement cstm = con.prepareCall(sql);

			cstm.setLong(1, id);
			cstm.registerOutParameter(2, Types.INTEGER);
			cstm.execute();
			delete = cstm.getInt(2) == 1;

			con.close();
		} catch (Exception e) {
			System.out.println("删除电影 MYSQL ERROR:" + e.getMessage());
		}
		return delete;
	}

}
