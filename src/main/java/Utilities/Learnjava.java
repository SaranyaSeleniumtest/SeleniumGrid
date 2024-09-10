package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Learnjava {
	static WebDriver driver;
	
	public static void main(String[] args) {
		driver= new ChromeDriver();
		driver.get("https://letcode.in/");
		driver.manage().window().maximize();
		screen();
	}
	
	public static void screen() {
		Date dt= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss");
		String format = sdf.format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File screenshotAs = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File targetfile= new File(".\\reports\\Automationreport_"+format+".png");
		screenshotAs.renameTo(targetfile);
	}

}
