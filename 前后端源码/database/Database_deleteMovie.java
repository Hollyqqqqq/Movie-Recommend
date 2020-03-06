package database;

import java.sql.*;

public class Database_deleteMovie {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // ����
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // ��ַ
	private static final String USER = "root"; // �û���
	private static final String PASS = "123456qwer"; // ����

	// ɾ����Ӱ����Ҫ�ȵ���search_movie,Ȼ��ѡ��id������
	public static boolean delete_movie(int id) {
		boolean delete = false;
		int line = 0;
		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "{CALL delete_movie(?,?)}";
			CallableStatement cstm = con.prepareCall(sql);

			cstm.setLong(1, id);
			cstm.registerOutParameter(2, Types.INTEGER);
			cstm.execute();
			delete = cstm.getInt(2) == 1;

			con.close();
		} catch (Exception e) {
			System.out.println("ɾ����Ӱ MYSQL ERROR:" + e.getMessage());
		}
		return delete;
	}

}
