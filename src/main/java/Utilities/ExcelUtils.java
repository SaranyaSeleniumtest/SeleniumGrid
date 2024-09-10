package Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	
	public ExcelUtils() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		String datapath= System.getProperty("user.dir")+".\\testdata\\Data.xlsx";
		System.out.println(datapath);
		FileInputStream fis= new FileInputStream(datapath);
		 wb= new XSSFWorkbook(fis);
		 sheet = wb.getSheet("logindata");
	
	}
	
	public int getrow() throws IOException {
			return sheet.getLastRowNum();
	}
	
	public int getcol() {
		return sheet.getRow(1).getLastCellNum();
	}
	
//	public void readdatafromExcel() throws IOException {
//		for(int row=1;row<=getrow();row++) {
//			for(int col=0;col<=getcol();col++) {
//			String txt = sheet.getRow(row).getCell(col).toString();
//			System.out.println(txt);
//			}
//		}
//	}
//	
	
	
}
