package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class config {

	Properties prop;
	public void property() {
		prop= new Properties();
		String path= System.getProperty("user.dir"+"\\config.properties");
		try {
			FileInputStream fis= new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
