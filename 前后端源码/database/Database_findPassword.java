package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class Database_findPassword {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // ����
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // ��ַ
	private static final String USER = "root"; // �û���
	private static final String PASS = "123456qwer"; // ����

	// �һ�������֤
	public static String checkAnswer(int account, String[] ans) {
		String result = "";
		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
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
			System.out.println("�һ�������----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return result;
	}

}
