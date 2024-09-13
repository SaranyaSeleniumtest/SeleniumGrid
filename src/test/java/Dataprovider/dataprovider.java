package Dataprovider;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.DataProvider;

public class dataprovider {
	
	@DataProvider(name="dp")
	public Object[][] testdatalogin() {
		
		Object[][]obj= {{"standard_user","secret_sauce"},
				{"visual_user","secret_sauce"},
				{"locked_out_user","secret_sauce"}};
		return obj;
	}
	
	
	@DataProvider(name="hashdata")
	public Object[][] testdata() throws IOException {
		Excelutils excel= new Excelutils("logindata");
		int rowcnt = excel.getrow();
		Object[][] data= new Object[rowcnt][1];
		for (int row=1;row<=rowcnt;row++) {
			//add code for hashmap
			HashMap<String, String> hm = excel.excel_gethash(row);
			data[row-1][0]=hm;
		}
		
		return data;
	}

}
