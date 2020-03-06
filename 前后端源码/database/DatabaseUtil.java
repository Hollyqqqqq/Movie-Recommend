package database;

import java.sql.*;

public class DatabaseUtil {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // ����
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // ��ַ
	private static final String USER = "root"; // �û���
	private static final String PASS = "123456qwer"; // ����

	// ����Ա��¼��֤
	public static boolean checkAdmin(int account, String password) {
		boolean checkbool = false;
		Connection con = null; // ����һ��MYSQL���Ӷ���
		Statement stmt = null; // ��������
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
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
			System.out.println("����Ա��½���----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return checkbool;
	}

	// �û���¼��֤
	public static boolean checkUser(int account, String password) {
		boolean checkbool = false;
		Connection con = null; // ����һ��MYSQL���Ӷ���
		Statement stmt = null; // ��������
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
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
			System.out.println("�û���½���----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return checkbool;
	}

	// �û�ע�ᣬ�õ��Ñ�����Ϣ�������ݿ⴫��
	public static int userSignUp(String[] info) {
		int key = -1;
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "INSERT INTO user (user_name,password,decribe) VALUES(?,?,?)";// SQL���
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// ����ִ��sql����ѯ����SQL������Ԥ���봦��,��ȡ�Զ����ӵ�id��
			pst.setString(1, info[0]);
			pst.setString(2, info[1]);
			pst.setString(3, info[2]);
			int line = pst.executeUpdate();// ��ִ����ɾ����䣬�����ܵ�Ӱ�������
//			System.out.println(line);
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next())
				key = rs.getInt(1);
			System.out.println("key:" + key);
			con.close();
		} catch (Exception e) {
			System.out.println("�û�ע��----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return key;
	}

	// �û��ܱ������
	public static int userAnswer(int key, String[] ans) {
		int line = 0;
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "INSERT INTO question_answer (user_id,question_id,answer) VALUES(?,?,?)";// SQL���
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// ����ִ��sql����ѯ����SQL������Ԥ���봦��,��ȡ�Զ����ӵ�id��
			for (int i = 1; i <= 3; i++) {
				pst.setLong(1, key);
				pst.setLong(2, i);
				pst.setString(3, ans[i - 1]);
				line += pst.executeUpdate();// ��ִ����ɾ����䣬�����ܵ�Ӱ�������
			}

//			System.out.println(line);
			con.close();
		} catch (Exception e) {
			System.out.println("�û�ע��----checkUser----MYSQL ERROR:" + e.getMessage());
		}

		return line;
	}

	// �û�ϲ���б�
	public static int userPreference(int key, int[] pre) {
		int line = -1;
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql_delete = "delete from preference where id_user= " + key + " ;";
			PreparedStatement pst_delete = con.prepareStatement(sql_delete);
			pst_delete.executeUpdate();

			String sql = "INSERT INTO preference(id_user,movie_type) VALUES(?,?)";// SQL���
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// ����ִ��sql����ѯ����SQL������Ԥ���봦��,��ȡ�Զ����ӵ�id��
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
					line += pst.executeUpdate();// ��ִ����ɾ����䣬�����ܵ�Ӱ�������
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
			System.out.println("�û�ע��----checkUser----MYSQL ERROR:" + e.getMessage());
		}

		return line;
	}

	// �����û���Ϣ
	public static boolean updateUserInfo(int key, String intro) {
		boolean flag = false;
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "update user set decribe= '" + intro + "' where id_user= " + key + " ;";// SQL���
			PreparedStatement pst = con.prepareStatement(sql);// ����ִ��sql����ѯ����SQL������Ԥ���봦��,��ȡ�Զ����ӵ�id��
			int line = pst.executeUpdate();// ��ִ����ɾ����䣬�����ܵ�Ӱ�������
			if (line > 0)
				flag = true;
//			System.out.println(line);
			con.close();
		} catch (Exception e) {
			System.out.println("�û�ע��----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return flag;
	}

	// �����û�����
	public static boolean updateUserPass(int key, String pass) {
		boolean flag = false;
		try {

			Class.forName(JDBC_DRIVER);// MYSQL����
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS); // ���ӱ���MYSQL
			String sql = "update user set password= '" + pass + "' where id_user= " + key + " ;";// SQL���
			PreparedStatement pst = con.prepareStatement(sql);// ����ִ��sql����ѯ����SQL������Ԥ���봦��,��ȡ�Զ����ӵ�id��
			int line = pst.executeUpdate();// ��ִ����ɾ����䣬�����ܵ�Ӱ�������
			if (line > 0)
				flag = true;
//			System.out.println(line);
			con.close();
		} catch (Exception e) {
			System.out.println("�û�ע��----checkUser----MYSQL ERROR:" + e.getMessage());
		}
		return flag;
	}

}
