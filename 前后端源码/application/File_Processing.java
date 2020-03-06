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
	// ת�����ݸ�ʽ
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

	// ��ʼ�ļ�����
	public static int[] startProcess(String filePath) throws IOException {
		ArrayList<movie_d> movie_data_t = new ArrayList<movie_d>();
		// type_data_t���Ե�[i][j]�����������ǵ�i����Ӱ�ĵ�j�����ͣ� count1 �����������������ֵ
		// typeֱ�Ӵ�ľ����ַ�����ʾ����
		String[][] type_data_t = new String[10000][20];
		int count1 = 0;
		// people_data_t���Ե�[i][j]�����������ǵ�i����Ӱ�ĵ�j����Ա��Ϣ�� count2 �����������������ֵ

		// people[i][j].String name; char credit; // AΪ��Ա��DΪ���� char gender; String
		// birth; String breif_intro;

		people[][] people_data_t = new people[10000][30];
		int count2 = 0;
		File file = new File(filePath);
		InputStream is = new FileInputStream(file);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		// ��ȡÿһ��������
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// ��ȡ��ǰ��������ÿһ��
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {

					XSSFCell one = xssfRow.getCell(1); // ��2������ ��ӳʱ��
					String released_data = getValue(one);
					XSSFCell two = xssfRow.getCell(2); // ��3������ ��Ӱ����
					String title = getValue(two);
					XSSFCell three = xssfRow.getCell(3); // ��4������ ����
					String country = getValue(three);
					XSSFCell four = xssfRow.getCell(4); // ��5������ ����
					String s = getValue(four);
					double star = Double.valueOf(s.toString());
					XSSFCell five = xssfRow.getCell(5); // ��6������ ���
					String intro = getValue(five);
					XSSFCell six = xssfRow.getCell(6); // ��7������ ʱ��
					String str = getValue(six);
					double time = Double.valueOf(str.toString());
					int runtime = (int) time;
					movie_d tMovie = new movie_d(released_data, title, country, star, intro, runtime);
					movie_data_t.add(tMovie);

					XSSFCell eight = xssfRow.getCell(7); // ��8������ ����
					String type1 = getValue(eight);
					String[] typeArray = null;
					typeArray = type1.split(",");
					type_data_t[count1++] = typeArray;

					XSSFCell eleven = xssfRow.getCell(10); // ��11������ people��Ϣ
					String people_info = getValue(eleven);
					String[] peopleArray1 = null;
					peopleArray1 = people_info.split("/");
					for (int i = 0; i < peopleArray1.length; i++) {
						String[] peopleArray2 = null;
						peopleArray2 = peopleArray1[i].split(",");

						String p_name = peopleArray2[0]; // ����
						String credit = peopleArray2[1];// ְҵ
						String gender = peopleArray2[2]; // �Ա�
						String birth = peopleArray2[3]; // ��������
						String p_info = peopleArray2[4]; // ���

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
