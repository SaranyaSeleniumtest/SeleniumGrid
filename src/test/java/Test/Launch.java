package Test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.Base;

public class Launch extends Base {
	
	@Parameters({"browser"})
	@Test
	public void launchrome(String value) throws IOException {
		System.out.println(value);
		 initialize(value);
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertTrue(title.contains("Automation Exercise"));		
	}
	
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
