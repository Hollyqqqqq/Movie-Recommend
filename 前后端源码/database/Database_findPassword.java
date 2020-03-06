package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class Database_findPassword {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 驱动
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // 地址
	private static final String USER = "root"; // 用户名
	private static final String PASS = "123456qwer"; // 密码

	// 找回密码验证
	public static String checkAnswer(int account, String[] ans) {
		String result = "";
		Connection con = null; // 定义一个MYSQL链接对象
		try {

			Class.forName(JDBC_DRIVER);// MYSQL驱动
			con = DriverManager.getConnection(DB_URL, USER, PASS); // 链接本地MYSQL
			CallableStatement cs = con.prepareCall("{call check_answer(?,?,?,?,?)}");
			cs.setLong(1, account);
			cs.setString(2, ans[0]);
			cs.setString(3, ans[1]);
			cs.setString(4, ans[2]);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			result = cs.getString(5);
//			System.out.println(result);

			con.close();
		} catch (Exception e) {
			System.out.println("找回密码检查----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return result;
	}

}
