package Test;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
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
import Utilities.ExcelUtils;

public class Login_xlutils extends Base {
	
	@Parameters({"browser"})
	@BeforeMethod
	public void launchrome(String value) throws IOException {
		System.out.println(value);
		 initialize(value);
		Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));		
	}
	

	@Test(dataProvider="getdata")
	public void login(String user,String password) {
		System.out.println(user);
		System.out.println(password);
		driver.findElement(By.name("email")).sendKeys(user);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//*[text()='Login']")).click();
		String txt = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]")).getText();
		Assert.assertTrue(txt.contains("Your email or password is incorrect!"));
	}
	
	@DataProvider
	public Object[][] getdata() throws IOException {
		ExcelUtils excel= new ExcelUtils();
//		System.out.println(excel.getrow());
//		System.out.println(excel.getcol());
		Object[][]data= new Object[excel.getrow()][excel.getcol()];
		for(int row=1;row<=excel.getrow();row++) {
			for(int col=0;col<=excel.getcol();col++) {
			String txt = excel.sheet.getRow(row).getCell(col).toString();
			System.out.println(row);
			System.out.println(col);
			DataFormatter formatter = new DataFormatter();

			data[row-1][col]=excel.sheet.getRow(row).getCell(col).toString();
			System.out.println(txt);
			}
		}
		//add code to read data and store in array
		return data;
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
