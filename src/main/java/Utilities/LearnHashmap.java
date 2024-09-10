package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LearnHashmap {

	public static void main(String[] args) throws IOException {
		int rownum=3;
		HashMap<String,String> hm= new HashMap<String,String>();
		//		hm.put("148170", "Saranya");
		//		hm.put("148160", "Sam");
		//		hm.put("148180","Tom");
//		System.out.println(hm.get("148170"));
		String path=".\\testdata\\sample.xlsx";
		FileInputStream fis= new FileInputStream(path);
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheet("sheet1");
		int rowcnt = sheet.getLastRowNum();
		System.out.println(rowcnt);
		int colcnt = sheet.getRow(0).getLastCellNum();
		System.out.println(colcnt);
		for(int col=0;col<colcnt;col++) {
				sheet.getRow(rownum).getCell(col).setCellType(CellType.STRING);
				String datacolumnname=sheet.getRow(0).getCell(col).toString();
				String datavalue=sheet.getRow(rownum).getCell(col).toString();
				hm.put(datacolumnname,datavalue);
				System.out.println(hm);
//				break;
		}
		System.out.println(hm.get("Pass"));
	}

}
