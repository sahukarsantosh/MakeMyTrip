package userDefinedLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite {
	
	public static File src;
	public static FileInputStream fileip;
	public static FileOutputStream fileop;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static String val1;
	public static int row;
	public static XSSFCell cell;
	public static XSSFRow Row;
	public static String category1;
	public static String category2;
	public static String category3;
	public static String category4;
	public static String category5;
	
	public static int readexcel() {
		try {
			src = new File(System.getProperty("user.dir") + "\\src\\test\\java\\dataTables\\MakemytripDetails.xlsx");
			fileip = new FileInputStream(src);
			workbook = new XSSFWorkbook(fileip);
			sheet = workbook.getSheetAt(0);
			for(int i=0;i<=sheet.getLastRowNum();i++) {
				if(i == 1) {
					category1 = (sheet.getRow(i).getCell(0)).getStringCellValue();
					category2 = (sheet.getRow(i).getCell(1)).getStringCellValue();
					category3 = (sheet.getRow(i).getCell(2)).getStringCellValue();
					category4 = (sheet.getRow(i).getCell(3)).getStringCellValue();
					category5 = (sheet.getRow(i).getCell(4)).getStringCellValue();
					row = i;
					break;
				}
			}
		}catch(FileNotFoundException e) {
			FailReport.reportFail(e.getMessage());
		}catch(IOException e) {
			FailReport.reportFail(e.getMessage());
		}
		
		return row;
	}
	
	public static void writeexcel() {
		try {
			fileip.close();
			
			fileop = new FileOutputStream(src);
			workbook.write(fileop);
			fileop.close();
		}catch(FileNotFoundException e) {
			FailReport.reportFail(e.getMessage());
		}catch(IOException e) {
			FailReport.reportFail(e.getMessage());
		}
	}

}
