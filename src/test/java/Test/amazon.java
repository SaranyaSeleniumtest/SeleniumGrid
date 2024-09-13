package Test;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Base.BaseUpdates;
import Pages.Amazonhome;

public class amazon extends BaseUpdates {

	@Test
	public void testamazon() {
		Amazonhome amz= new Amazonhome(driver);
		amz.Enteritem("iphone 15pro max");
		amz.clicksearch();
		amz.clicksearchresult();
//		driver.findElement(By.name("field-keywords")).sendKeys("iphone 15pro max");
//		driver.findElement(By.id("nav-search-submit-button")).click();
//		driver.findElement(By.xpath("//a/child::span[text()='Apple iPhone 15 Pro Max (256 GB) - Black Titanium']")).click();
	}
	

	
}
