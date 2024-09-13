package Test;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseUpdates;
import Dataprovider.dataprovider;

public class verifylogin_1209 extends BaseUpdates {
	
	@Test(dataProvider="hashdata",dataProviderClass=dataprovider.class)
	public void logintest(Object obj) {
			HashMap<String,String> hm1= (HashMap<String,String>)obj;
		System.out.println("This is first test");
		try {
		driver.findElement(By.id("user-name")).sendKeys(hm1.get("user"));
		driver.findElement(By.id("password")).sendKeys(hm1.get("pass"));
		driver.findElement(By.id("login-button")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Products']")).isDisplayed(),"Login not successful");
		}catch(Exception e) {
			Assert.fail("Login not successful "+e.getMessage());
		}
		
	}

}
