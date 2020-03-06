package application;

import java.io.InputStream;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.movie_d;
import entity.people;

public class File_Processing {
	// 转换数据格式
	public static String getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

	public static void main(String[] args) {

	}

	// 开始文件处理
	public static int[] startProcess(String filePath) throws IOException {
		ArrayList<movie_d> movie_data_t = new ArrayList<movie_d>();
		// type_data_t，对第[i][j]个数组代表的是第i个电影的第j个类型， count1 辅助计数，代表最大值
		// type直接存的就是字符串表示类型
		String[][] type_data_t = new String[10000][20];
		int count1 = 0;
		// people_data_t，对第[i][j]个数组代表的是第i个电影的第j个人员信息， count2 辅助计数，代表最大值

		// people[i][j].String name; char credit; // A为演员，D为导演 char gender; String
		// birth; String breif_intro;

		people[][] people_data_t = new people[10000][30];
		int count2 = 0;
		File file = new File(filePath);
		InputStream is = new FileInputStream(file);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		// 获取每一个工作薄
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// 获取当前工作薄的每一行
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {

					XSSFCell one = xssfRow.getCell(1); // 第2列数据 上映时间
					String released_data = getValue(one);
					XSSFCell two = xssfRow.getCell(2); // 第3列数据 电影名称
					String title = getValue(two);
					XSSFCell three = xssfRow.getCell(3); // 第4列数据 国家
					String country = getValue(three);
					XSSFCell four = xssfRow.getCell(4); // 第5列数据 评分
					String s = getValue(four);
					double star = Double.valueOf(s.toString());
					XSSFCell five = xssfRow.getCell(5); // 第6列数据 简介
					String intro = getValue(five);
					XSSFCell six = xssfRow.getCell(6); // 第7列数据 时长
					String str = getValue(six);
					double time = Double.valueOf(str.toString());
					int runtime = (int) time;
					movie_d tMovie = new movie_d(released_data, title, country, star, intro, runtime);
					movie_data_t.add(tMovie);

					XSSFCell eight = xssfRow.getCell(7); // 第8列数据 种类
					String type1 = getValue(eight);
					String[] typeArray = null;
					typeArray = type1.split(",");
					type_data_t[count1++] = typeArray;

					XSSFCell eleven = xssfRow.getCell(10); // 第11列数据 people信息
					String people_info = getValue(eleven);
					String[] peopleArray1 = null;
					peopleArray1 = people_info.split("/");
					for (int i = 0; i < peopleArray1.length; i++) {
						String[] peopleArray2 = null;
						peopleArray2 = peopleArray1[i].split(",");

						String p_name = peopleArray2[0]; // 姓名
						String credit = peopleArray2[1];// 职业
						String gender = peopleArray2[2]; // 性别
						String birth = peopleArray2[3]; // 出生日期
						String p_info = peopleArray2[4]; // 简介

						people p = new people(p_name, credit, gender, birth, p_info);
						people_data_t[count2][i] = p;
					}
					count2++;
				}
			}
		}

		int a[] = database.Database_insert_movie.insertmovie(movie_data_t, type_data_t, people_data_t);
		return a;

//		for (int i = 0; i < movie_data_t.size(); i++) {
//			System.out.println(movie_data_t.get(i).getReleased_data() + " " + movie_data_t.get(i).getTitle() + " "
//					+ movie_data_t.get(i).getCountry() + " " + movie_data_t.get(i).getStar() + " "
//					+ movie_data_t.get(i).getRuntime() + " " + movie_data_t.get(i).getIntro());
//			for (int j = 0; j < type_data_t[i].length; j++) {
//				System.out.print(type_data_t[i][j] + " ");
//			}
//			System.out.println("");
//			for (int j = 0; j < people_data_t[i].length; j++) {
//				if (people_data_t[i][j] != null) {
//					System.out.print(people_data_t[i][j].getName() + " " + people_data_t[i][j].getCredit() + " "
//							+ people_data_t[i][j].getGender() + " " + people_data_t[i][j].getBirth() + " "
//							+ people_data_t[i][j].getBreif_intro());
//				}
//			}
//			System.out.println("");
//			System.out.println("");
//		}

	}
}
