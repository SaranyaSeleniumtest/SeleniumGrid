package Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config {
	
	public Properties prop;
	public FileInputStream fis;
	
	
	public void getprop() throws IOException {
		String path=System.getProperty("user.dir")+"\\config.properties";
		FileInputStream fis=new FileInputStream(path);
		 prop= new Properties();
		 prop.load(fis);
	
	}

}
