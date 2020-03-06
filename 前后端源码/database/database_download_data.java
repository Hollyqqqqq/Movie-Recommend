package database;

import java.io.FileOutputStream;
import java.sql.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class database_download_data {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // ����
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // ��ַ
	private static final String USER = "root"; // �û���
	private static final String PASS = "123456qwer"; // ����

	public static void main(String[] args) {
	}

	/**
	 * �����ݿ�dname�еı���Ϊtname�����ݱ�д��Excel���
	 * 
	 * @param dname
	 * @param tname
	 */
	public static String userData() {
		String path = "E:/user.xls";

		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("��");
		try {
			Class.forName(JDBC_DRIVER);
			Connection con;
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement st = con.createStatement();
			String sql = "SELECT (id_user + 10000) AS '�û��˺�', user_name AS '�û���',decribe AS '�û����',signUpDate AS 'ע��ʱ��' FROM user;";
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();// �õ���������ֶ���
			int c = rsmd.getColumnCount();// �õ����ݱ�Ľ�������ֶε�����
			// ���ɱ��ĵ�һ�У�����ͷ
			HSSFRow row0 = sheet.createRow(0);// ������һ��
			for (int i = 0; i < c; i++) {
				HSSFCell cel = row0.createCell(i);// ������һ�еĵ�i��
				cel.setCellValue(rsmd.getColumnName(i + 1));
//				cel.setCellStyle(style);
			}
			// �����ݱ��е����ݰ��е����Excel����
			int r = 1;
			while (rs.next()) {
				HSSFRow row = sheet.createRow(r++);// �����ǵ�һ�е�������
				for (int i = 0; i < c; i++) {// ��Ȼ��c�У������r�еĵ�i��
					HSSFCell cel = row.createCell(i);
					// ��������д������
//					cel.setCellValue(rs.getString(rsmd.getColumnName(i+1)));
					cel.setCellValue(rs.getString(i + 1));
				}
			}
			// ���ļ�������ഴ����Ϊtable��Excel���
			FileOutputStream out = new FileOutputStream(path);
			book.write(out);// ��HSSFWorkBook�еı�д���������
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public static String moviesData() {
		String path = "E:/movies.xls";

		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("��");
		try {
			Class.forName(JDBC_DRIVER);
			Connection con;
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "call export_movies;";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.execute();
			ResultSet rs = cstm.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();// �õ���������ֶ���
			int c = rsmd.getColumnCount();// �õ����ݱ�Ľ�������ֶε�����
			// ���ɱ��ĵ�һ�У�����ͷ
			HSSFRow row0 = sheet.createRow(0);// ������һ��
			for (int i = 0; i < c; i++) {
				HSSFCell cel = row0.createCell(i);// ������һ�еĵ�i��
				cel.setCellValue(rsmd.getColumnName(i + 1));
//				cel.setCellStyle(style);
			}
			// �����ݱ��е����ݰ��е����Excel����
			int r = 1;
			while (rs.next()) {
				HSSFRow row = sheet.createRow(r++);// �����ǵ�һ�е�������
				for (int i = 0; i < c; i++) {// ��Ȼ��c�У������r�еĵ�i��
					HSSFCell cel = row.createCell(i);
					// ��������д������
//					cel.setCellValue(rs.getString(rsmd.getColumnName(i+1)));
					cel.setCellValue(rs.getString(i + 1));
				}
			}
			// ���ļ�������ഴ����Ϊtable��Excel���
			FileOutputStream out = new FileOutputStream(path);
			book.write(out);// ��HSSFWorkBook�еı�д���������
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

}
