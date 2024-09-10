package Test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import Base.Base;

public class Login_dataprovider extends Base {
	
	@Parameters({"browser"})
	@BeforeMethod
	public void launchrome(String value) throws IOException {
		System.out.println(value);
		 initialize(value);
		Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));		
	}
	

	@Test(dataProvider="getdata")
	public void login(String user,String password) {
		driver.findElement(By.name("email")).sendKeys(user);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//*[text()='Login']")).click();
		String txt = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]")).getText();
		Assert.assertTrue(txt.contains("Your email or password is incorrect!"));
	}
	
	@DataProvider
	public Object[][] getdata() {
		Object[][] data= {{"user1@gmail.com","pass1"},{"user2@gmail.com","pass2"}};
		return data;
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
