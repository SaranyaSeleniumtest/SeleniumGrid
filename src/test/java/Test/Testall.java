package Test;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Dataprovider.Excelutils;
import Dataprovider.dataprovider;

public class Testall {

	public WebDriver driver;
	public Logger logger;

	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser) throws IOException {
		config cg= new config();
		
		cg.getprop();
		
		if( cg.prop.getProperty("Environment").equalsIgnoreCase("local")) {
			if(browser.equalsIgnoreCase("chrome")) {
				driver=new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("edge")) {
				driver=new EdgeDriver();
			}
			
		}
		
		else if(cg.prop.getProperty("Environment").equalsIgnoreCase("remote")) {
				DesiredCapabilities dc= new DesiredCapabilities();
					if(browser.equalsIgnoreCase("chrome")) {
						dc.setBrowserName("chrome");
					}
					else if(browser.equalsIgnoreCase("Edge")) {
						dc.setBrowserName("MicrosoftEdge");
				}
				driver= new RemoteWebDriver(new URL("http://localhost:4444"), dc);
		
		}
		
		driver.get(cg.prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	
	@Test(priority=0,groups = "smoke",dataProvider="dp",dataProviderClass = dataprovider.class)
	public void TC1_login(String user,String pass) {
		System.out.println("This is first test");
		try {
		driver.findElement(By.id("user-name")).sendKeys(user);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.id("login-button")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Products']")).isDisplayed(),"Login not successful");
		}catch(Exception e) {
			Assert.fail("Login not successful "+e.getMessage());
		}
	}
	
	@Test(priority=1,groups = "smoke",enabled=false)
	public void TC2() {
		System.out.println("This is second test");
	}
	
	@Test(priority=2,groups = "Regression",enabled=false)
	public void TC3() {
		System.out.println("This is third test");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
