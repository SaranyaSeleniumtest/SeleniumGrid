import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class CommonMethods  {
	public WebDriver driver;

	public void scrollviewelement(WebElement ele) throws InterruptedException {
		//		WebElement element = driver.findElement(By.id("id_of_element"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(500); 
	}

	public void scroll() {

		JavascriptExecutor jse=  (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1000)");
		//		jse.executeScript("window.scrollBy("+x+","+y);
	}

	public WebElement getele(String path) {
		try {
		WebElement element = driver.findElement(By.xpath(path));
		return element;
		}catch(Exception e) {
			Assert.fail();
		}
		return null;

		
	}
	public void rd(List<WebElement>ele,String cond,String field) {
		try {
			for(WebElement opt:ele) {
				if(opt.getText().equalsIgnoreCase(cond)){
					opt.click();

					break;

				}
			}
		}catch(Exception e) {
			Assert.fail("radio button "+field+"not clicked due to error: "+ e.getMessage());

		}
	}
	public void sel_val(WebElement ele,String val,String field) {
		try {
		Select obj= new Select(ele);
		
		obj.selectByValue(val);
		}catch(Exception e) {
			Assert.fail("unable to select value "+field+" due to error-- " +e.getMessage());
		}
	}

	public void sel_visibletxt(WebElement ele,String visible,String field) {
		try {
		Select obj= new Select(ele);
		
		obj.selectByVisibleText(visible);
		}catch(Exception e) {
			Assert.fail("unable to select value "+field+" due to error-- " +e.getMessage());
		}
	}
	
	public void autocomplete(List<WebElement>ele,String cond) {
		boolean optfound=false;
		for(WebElement opt: ele) {
			if(opt.getAttribute("value").equalsIgnoreCase(cond)){
				System.out.println("inside loop");
			//	driver.execute_script("arguments[0].style.display = 'block';", opt);
				opt.click();
				optfound=true;
				break;
			}
		}
		if(optfound==false) {
			Assert.fail("option-"+cond+"not found");
		}
		
	}
	
	
	public void chk(List<WebElement>ele,String cond,String field) {
		try {
			for(WebElement opt: ele) {
				if(opt.getText().equalsIgnoreCase(cond)) {
					opt.click();
					break;
				}
			}
		}catch(Exception e) {
			Assert.fail(field+" -checkbox is not clicked due to error-->"+e.getMessage());
		}
	}

	@BeforeMethod
	public void launchurl() throws MalformedURLException {
		String browser="chrome";
		String env="remote";
		if(env.equalsIgnoreCase("remote")){
			DesiredCapabilities dc= new DesiredCapabilities();
			dc.setBrowserName(browser);
			driver = new RemoteWebDriver(new URL("http://localhost:4444"),dc);
		}
		else if(env.equalsIgnoreCase("local")) {
			if(browser.equalsIgnoreCase("Chrome")) {
				driver= new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("edge")) {
				driver= new EdgeDriver();
			}
		}
		
		try {

			
			driver.get("https://practice.expandtesting.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			Assert.assertEquals(driver.getTitle(),"Practice Test Automation WebSite");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail("Launch url failed");
		}

	}

	//@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
