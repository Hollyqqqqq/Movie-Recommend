package database;

import java.io.FileOutputStream;
import java.sql.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class database_download_data {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 驱动
	private static final String DB_URL = "jdbc:mysql://localhost:3306/project2?serverTimezone=GMT%2B8"; // 地址
	private static final String USER = "root"; // 用户名
	private static final String PASS = "123456qwer"; // 密码

	public static void main(String[] args) {
	}

	/**
	 * 将数据库dname中的表名为tname的数据表写入Excel表格
	 * 
	 * @param dname
	 * @param tname
	 */
	public static String userData() {
		String path = "E:/user.xls";

		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("表");
		try {
			Class.forName(JDBC_DRIVER);
			Connection con;
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement st = con.createStatement();
			String sql = "SELECT (id_user + 10000) AS '用户账号', user_name AS '用户名',decribe AS '用户简介',signUpDate AS '注册时间' FROM user;";
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();// 得到结果集的字段名
			int c = rsmd.getColumnCount();// 得到数据表的结果集的字段的数量
			// 生成表单的第一行，即表头
			HSSFRow row0 = sheet.createRow(0);// 创建第一行
			for (int i = 0; i < c; i++) {
				HSSFCell cel = row0.createCell(i);// 创建第一行的第i列
				cel.setCellValue(rsmd.getColumnName(i + 1));
//				cel.setCellStyle(style);
			}
			// 将数据表中的数据按行导入进Excel表中
			int r = 1;
			while (rs.next()) {
				HSSFRow row = sheet.createRow(r++);// 创建非第一行的其他行
				for (int i = 0; i < c; i++) {// 仍然是c列，导入第r行的第i列
					HSSFCell cel = row.createCell(i);
					// 以下两种写法均可
//					cel.setCellValue(rs.getString(rsmd.getColumnName(i+1)));
					cel.setCellValue(rs.getString(i + 1));
				}
			}
			// 用文件输出流类创建名为table的Excel表格
			FileOutputStream out = new FileOutputStream(path);
			book.write(out);// 将HSSFWorkBook中的表写入输出流中
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public static String moviesData() {
		String path = "E:/movies.xls";

		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("表");
		try {
			Class.forName(JDBC_DRIVER);
			Connection con;
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "call export_movies;";
			CallableStatement cstm = con.prepareCall(sql);
			cstm.execute();
			ResultSet rs = cstm.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();// 得到结果集的字段名
			int c = rsmd.getColumnCount();// 得到数据表的结果集的字段的数量
			// 生成表单的第一行，即表头
			HSSFRow row0 = sheet.createRow(0);// 创建第一行
			for (int i = 0; i < c; i++) {
				HSSFCell cel = row0.createCell(i);// 创建第一行的第i列
				cel.setCellValue(rsmd.getColumnName(i + 1));
//				cel.setCellStyle(style);
			}
			// 将数据表中的数据按行导入进Excel表中
			int r = 1;
			while (rs.next()) {
				HSSFRow row = sheet.createRow(r++);// 创建非第一行的其他行
				for (int i = 0; i < c; i++) {// 仍然是c列，导入第r行的第i列
					HSSFCell cel = row.createCell(i);
					// 以下两种写法均可
//					cel.setCellValue(rs.getString(rsmd.getColumnName(i+1)));
					cel.setCellValue(rs.getString(i + 1));
				}
			}
			// 用文件输出流类创建名为table的Excel表格
			FileOutputStream out = new FileOutputStream(path);
			book.write(out);// 将HSSFWorkBook中的表写入输出流中
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

}
