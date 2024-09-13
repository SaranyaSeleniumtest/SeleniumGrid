package Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import jdk.internal.org.jline.utils.Log;

public class Verifyall {
	
	public static WebDriver driver;
	public static Logger logger;
	public static WebDriverWait wait;
	public static void main(String[] args) throws InterruptedException {
		driver= new ChromeDriver();
		logger=LogManager.getLogger(Verifyall.class);
		driver.get("https://omayo.blogspot.com/");
//		Log.info("URl is launched");
		driver.manage().window().maximize();
		
//		takescreenshot();
//		driver.findElement(By.xpath("//button[text()='Check this']")).click();
//		WebElement option = waitfunction(driver.findElement(By.id("dte")));
//		option.click();
//		learnalerts();
//		act();
		WebElement ele= driver.findElement(By.id("multiselect1"));
		multisel(ele);
//		System.out.println(driver.findElement(By.xpath("//input[@value='Book']")).getText());
//		check(driver.findElements(By.xpath("//input[@name='accessories']")),"Bag");
//		radio(driver.findElements(By.xpath("//input[@type='radio']")),"Bicycle:");
	//	driver.findElement(By.xpath("//input[@value='car']")).click();
	}
	
	public static WebElement waitfunction(WebElement ele) {
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public static void takescreenshot() {
		TakesScreenshot screen=  (TakesScreenshot)driver;
		File screenshotAs = screen.getScreenshotAs(OutputType.FILE);
		Date dt= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy.mm.dd.hh.ss");
		
		String path= System.getProperty("user.dir")+"\\screenshot\\Automation"+sdf.format(dt)+".png";
		File dest= new File(path);
//		Log.info("Screenshot is taken");
		screenshotAs.renameTo(dest);
	}

	public static void learnalerts() throws InterruptedException {
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("This is test");
		Thread.sleep(3000);
		System.out.println(alert.getText());
		alert.accept();
		driver.findElement(By.id("confirm")).click();
		Alert alert2 = driver.switchTo().alert();
		System.out.println(alert2.getText());
		alert2.accept();
	}
	
	public static void act() {
		Actions action= new Actions(driver);
		action.doubleClick(driver.findElement(By.id("testdoubleclick"))).click();
		
	}
	
	
	public static void check(List<WebElement>ele, String condition) {
		for (WebElement opt : ele) {
			
			System.out.println(opt.getAttribute("value").trim());
			System.out.println(condition.trim());
			if((opt.getAttribute("value").trim()).equalsIgnoreCase(condition.trim())) {
				opt.click();
				break;
			}
		}
	}
	public static void multisel(WebElement ele) {
		
		Select sel= new Select(ele);
		boolean multiple = sel.isMultiple();
		if (multiple==true) {
			List<WebElement> options = sel.getOptions();
			System.out.println(options.size());
				
			for(WebElement opt: options) {
				opt.click();
				
			}
		}
	}
	public static void radio(List<WebElement> ele, String condition) {
		boolean rdfound= false;
		System.out.println(ele.size());
		for(WebElement opt: ele) {
			
			System.out.println(opt.getText());
			if(opt.getText().equalsIgnoreCase(condition)) {
				opt.click();
				rdfound=true;
				break;
			}
		}
		
	}
}
