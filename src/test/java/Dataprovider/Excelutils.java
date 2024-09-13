package Dataprovider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

public class Excelutils {

	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	
	public Excelutils(String sheetname) throws IOException {
		String path=".\\testdata\\Data.xlsx";
		FileInputStream fis= new FileInputStream(path);
		
		wb= new XSSFWorkbook(fis);
		 sheet = wb.getSheet(sheetname);
		
	}
	
	public int getrow() {
		System.out.println(sheet.getLastRowNum());
		return sheet.getLastRowNum();
	}
	
	public int getcol() {
		System.out.println(sheet.getRow(0).getLastCellNum());
		return sheet.getRow(0).getLastCellNum();
	}
	
	public HashMap<String, String> excel_gethash(int row) {
		HashMap<String,String> hm= new HashMap<String,String>();
		//add code for hash key that is colname
//		sheet.getRow(0).getCell(col);
		int colcnt = getcol();
		System.out.println(colcnt);
		//add code for hash value that is cellval
		
		for(int i=0;i<colcnt;i++) {
			sheet.getRow(row).getCell(i).setCellType(CellType.STRING);
			hm.put(sheet.getRow(0).getCell(i).toString(),sheet.getRow(row).getCell(i).toString());
			
		}
		
		return hm;
		
		
	}
}
