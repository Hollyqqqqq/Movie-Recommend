package database;

import java.sql.*;

public class Database_userInfo {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // ����
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // ��ַ
	private static final String USER = "root"; // �û���
	private static final String PASS = "123456qwer"; // ����

	// ��ʾ�û���Ϣ
	public static String[] get_user_info(int id) {
		String user_name = "", preference = "", describe = "";

		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "{CALL show_user_info(?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, id);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					user_name = rs.getString("user_name");
					preference = rs.getString("preference");
					describe = rs.getString("decribe");
				}
				hadResults = cstm.getMoreResults();
			}

			con.close();
		} catch (Exception e) {
			System.out.println("MYSQL ERROR:" + e.getMessage());
		}
		String[] user_info = { user_name, preference, describe };
//		System.out.println(user_info[0] + "," + user_info[1] + "," + user_info[2] + "-----");
		return user_info;
	}

	// ��ʾ���ڹۿ�
	/*public static String show_recently_watched(int id) {
		String movie_title = "";
		Connection con = null; // ����һ��MYSQL���Ӷ���
		try {
			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "{CALL show_recently_watched(?)}";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.setLong(1, id);
			boolean hadResults = cstm.execute();
			while (hadResults) {
				ResultSet rs = cstm.getResultSet();
				while (rs != null && rs.next()) {
					movie_title = rs.getString("title");
				}
				hadResults = cstm.getMoreResults();
			}

			con.close();
		} catch (Exception e) {
			System.out.println("MYSQL ERROR:" + e.getMessage());
		}
		return movie_title;
	}*/

	// ��ʾ�û�ϲ���б�
	public static boolean[] getUserPre(int id) {
		boolean[] pre_list = new boolean[10];
//		select movie_type from preference where id_user=3;

		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement st = con.createStatement();
			String sql = "select movie_type from preference where id_user= " + id + " ; ";
			ResultSet rst = st.executeQuery(sql);
			int type = 0;
//CheckBox[] box = { box_mx, box_qh, box_zn };
			while (rst.next()) {
				type = rst.getInt("movie_type");
				switch (type) {
				case 1:
					pre_list[0] = true;
					break;
				case 2:
					pre_list[1] = true;
					break;
				case 3:
					pre_list[2] = true;
					break;
				case 4:
					pre_list[3] = true;
					break;
				case 5:
					pre_list[4] = true;
					break;
				case 6:
					pre_list[5] = true;
					break;
				case 9:
					pre_list[6] = true;
					break;
				case 19:
					pre_list[7] = true;
					break;
				case 18:
					pre_list[8] = true;
					break;
				case 22:
					pre_list[9] = true;
					break;
				default:
					break;
				}
			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pre_list;
	}
}
