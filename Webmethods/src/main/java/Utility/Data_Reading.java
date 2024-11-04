package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import Base.Driver;

public class Data_Reading {

	public static XSSFWorkbook Workbook;
	public static XSSFSheet Sheet;
	public static Row row;
	public static Cell cell;
	public static FileInputStream file;
	public static ArrayList<String> B2B_map_list;
	public static String Name;
	public static int num;

	public static void Read_B2B_patnerdata() throws IOException {
		Driver.Data_prop();
		B2B_map_list = new ArrayList();
		file = new FileInputStream(Driver.path + Driver.prop.getProperty("Excel_path"));
		Workbook = new XSSFWorkbook(file);
		Sheet = Workbook.getSheetAt(1);
		Iterator Ritr = Sheet.rowIterator();

		Ritr.next();
		while (Ritr.hasNext()) {
			row = (Row) Ritr.next();
			Iterator Citr = row.iterator();
			while (Citr.hasNext()) {
				cell = (Cell) Citr.next();
				String data = cell.getStringCellValue();
				B2B_map_list.add(data);

			}
			Name = B2B_map_list.get(1);
			System.out.println(Name);
			B2B_map_list.clear();
		}
	}

	// Method to import data from a specific row in an Excel sheet to an ArrayList
	public static ArrayList<String> importRowData(String filePath, int rowNum, int sheetnumber) {
		ArrayList<String> rowData = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(filePath); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheetAt(sheetnumber);
			Row row = sheet.getRow(rowNum);
			num = sheet.getLastRowNum();
//            System.out.println("___________"+ num);

			if (row != null) {
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case STRING:
						rowData.add(cell.getStringCellValue());
						break;
					case NUMERIC:
						rowData.add(String.valueOf(cell.getNumericCellValue()));
						break;
					case BOOLEAN:
						rowData.add(String.valueOf(cell.getBooleanCellValue()));
						break;
					}
				}
			} else {
				System.out.println("Row number is out of bounds.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return rowData;
	}

	public static void main(String[] args) throws IOException {
		Driver.Data_prop();
//		ArrayList<String> list2=Data_Reading.importRowData(Driver.path+Driver.prop.getProperty("Excel_path"),2,2);
//		System.out.println(list2);
		ArrayList<String> list1 = Data_Reading.importRowData(Driver.path + Driver.prop.getProperty("Excel_path"), 1, 2);

		for (int i = 1; i < Data_Reading.num; i++) {
			ArrayList<String> list2 = Data_Reading.importRowData(Driver.path + Driver.prop.getProperty("Excel_path"), i,
					2);
			System.out.println(list2);
		}

	}
}
