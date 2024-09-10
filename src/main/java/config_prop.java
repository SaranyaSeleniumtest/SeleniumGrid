import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config_prop {

	public static void properties() throws IOException {
		FileInputStream	 fis= new FileInputStream(".\\config.properties");
		Properties prop = new Properties();
		prop.load(fis);
		System.out.println(prop.getProperty("Environment"));
		
	}
}
	
