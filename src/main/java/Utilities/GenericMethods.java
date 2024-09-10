package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class GenericMethods  {
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static Select sel;

	public static void Basicstart(String url) {
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
	}
	
	public static void Selectcalenderdate_Byloc(By locator,String expmon,String expyear,String expdate) {
		String monthyear = driver.findElement(locator).getText();
		System.out.println(monthyear);

		if(expmon.equals("February")&& (Integer.parseInt(expdate)>29)){
			System.out.println("Wrong date entered "+expdate);
			return;
		}

		if(Integer.parseInt(expdate)>31){
			System.out.println("Wrong date entered "+expdate);
			return;
		}
		while (!(splitdate(monthyear)[0].equals(expmon)&&(splitdate(monthyear)[1].equals(expyear)))){
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			monthyear = driver.findElement(locator).getText();
		}

		try {
			driver.findElement(By.xpath("//a[text()='"+expdate+"']")).click();
		}catch(Exception e) {
			System.out.println("Wrong date entered "+e.getMessage());
		}
	}
	

	public static void Selectcalenderdate(WebElement locator,String expmon,String expyear,String expdate) {
		String monthyear = locator.getText();
		System.out.println(monthyear);

		if(expmon.equals("February")&& (Integer.parseInt(expdate)>29)){
			System.out.println("Wrong date entered "+expdate);
			return;
		}

		if(Integer.parseInt(expdate)>31){
			System.out.println("Wrong date entered "+expdate);
			return;
		}
		while (!(splitdate(monthyear)[0].equals(expmon)&&(splitdate(monthyear)[1].equals(expyear)))){
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			monthyear = locator.getText();
		}

		try {
			driver.findElement(By.xpath("//a[text()='"+expdate+"']")).click();
		}catch(Exception e) {
			System.out.println("Wrong date entered "+e.getMessage());
		}
	}

	public static void Webdriverwait(WebElement ele) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean elefound=false;
		try {
		wait.until(ExpectedConditions.visibilityOf(ele));
		elefound=true;
		}catch(Exception e) {
			Assert.fail(e.getMessage()+"element not found");
		}
	}
	public static String[] splitdate(String monthyear) {
		String[] my = monthyear.split(" ");
		return my;

	}

	//todo try catch
	public static void doclick(WebElement ele,String fieldname) {
		if(ele.isEnabled()) {
			ele.click();
			System.out.println(fieldname+ " element is clicked");
		}
	}


	public static void enterval(WebElement ele,String val,String fieldname) {
		try {
			ele.clear();
			ele.sendKeys(val);
			System.out.println(val +" value is entered in "+ fieldname );

		}catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(val +" value not entered in " +fieldname+ e.getMessage());
		}
	}

	
	public WebElement getelement(WebElement ele) {
		return ele;
	}
	
//	public static String getheading(WebElement ele) {
//		String msg = ele.getText();
//		return msg;
//	}
	
	
	public static String gettitle() { 
		 String title=driver.getTitle(); //
	  return title; 
	  }
	 
	
	
	public static String getmessage(WebElement ele) {
		try {
		String txt=ele.getText();
		return txt;
		}catch(Exception e) {
			Assert.fail(e.getMessage()+"check element");
		}
		return null;
	}
	
	public boolean validate_isdisplayed(WebElement ele,String Fieldname) {
		boolean disp=false;
		try {
			ele.isDisplayed();
			disp=true;
			
			return disp;
		}catch(Exception e) {
//			System.out.println(e.getMessage());
			Assert.fail(Fieldname +" not displayed "+ e.getMessage());
		}
		return disp;
	}
	
	public static WebElement getElementByXpathContainsText(String xpath)
	{
		try {
			return	driver.findElement(By.xpath(xpath));
		}catch(Exception e){
			System.out.println(e.getMessage());
			Assert.fail("element: "+xpath+ e.getMessage());
		}
		return null;


	}

	public static void tc1() {
		driver.findElement(By.xpath("//div[contains(@class,'h_menu_drop_button')]//a")).click();
		driver.findElement(By.xpath("//div[@role='complementary']//ul[@class='menu']//a[normalize-space()='FLIGHTS']")).click();
	}
	public static void handlewindows() {
		//https://youtu.be/zlcQTTjsgGI?si=wZV1_oxmKN5otoSi
		String win1title = driver.getTitle();
		System.out.println(win1title);
		Set<String> windowHandles = driver.getWindowHandles();
		Object[] array = windowHandles.toArray();
		String win1=array[0].toString();
		String win2=array[1].toString();
		driver.switchTo().window(win2);
		System.out.println("title of window2"+driver.getTitle());
		driver.switchTo().window(win1);
		System.out.println("title of window1"+driver.getTitle());
	}

	public static void handlewindows1() {
		String win1title = driver.getTitle();
		System.out.println(win1title);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> win=new ArrayList<String>(windowHandles);

		String win1=win.get(0);
		String win2=win.get(1);
		driver.switchTo().window(win2);
		System.out.println("title of window2  "+driver.getTitle());
		driver.switchTo().window(win1);
		System.out.println("title of window1  "+driver.getTitle());
	}

	
	



	public static void selectdrop(WebElement ele,String val) {
		sel= new Select(ele);
		try {
			sel.selectByValue(val);
			System.out.println("Selected value is : "+sel.getFirstSelectedOption().getText());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}

	}

	public static void selectdrop(WebElement ele,int val) {
		sel= new Select(ele);
		try {
			sel.selectByIndex(val);
			System.out.println("Selected value by index is : "+val);
		}catch(Exception e) {
			e.getMessage();
		}

	}

	public static void selectdrop(WebElement ele,String visibletxt,String isMultiple) {
		sel= new Select(ele);
		if (isMultiple.equals("No")) {
			try {
				sel.selectByVisibleText(visibletxt);
				System.out.println("Selected value by visible text is : "+visibletxt);
			}catch(Exception e) {
				Assert.fail(visibletxt+" is not selected");
			}
		}	
		else if(sel.isMultiple() && isMultiple.equals("Yes")) {
			List<WebElement> options = sel.getOptions();
			System.out.println("Selected multiple options : ");
			try {
				for (WebElement opt : options) {
					sel.selectByVisibleText(opt.getText());
					System.out.println(opt.getText());

				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}



	public static void handleradio(String attribute) {
		try {
			driver.findElement(By.id(attribute)).click();
		}catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}


	public static boolean handlebootstrapdrop(){
		boolean clicked=false;
		String xpath="//ul[@class='dropdown-menu']//li/a";
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		for (WebElement ele : elements) {
			if(ele.getText().equalsIgnoreCase("CSS")){
				ele.click();
				clicked=true;
				break;
			}

		}
		//Assert.fail();
		return clicked;

	}

	public static void mouseactions(WebElement ele1,WebElement ele2) throws Exception {
		Actions act= new Actions(driver);
		try {
			act.moveToElement(ele1).moveToElement(ele2).click().perform();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * @Method name: Method to click on radio button
	 * @param element: pass list of radio button
	 * @param value: pass the value of radio button
	 */
	public void handleradiobutton(List<WebElement> element,String value) {
		for (WebElement opt : element) {
			if(opt.getText().equalsIgnoreCase(value)) {
				opt.click();
				System.out.println(value +"radio button is clicked");
				break;
			}
			Assert.fail(value +"not clicked");
			
		}
		
	}
	
	/**
	 * @Method name: Method to select on multiple checkboxes
	 * @param element pass list of checkbox 
	 * @param values pass the value of checkboxes using delimitter comma(,)
	 */
	public void handleCheckbox(List<WebElement> element,String values) {
	String[]array=values.split(",");
	for (String arr : array) {
		for (WebElement opt : element) {
			if(opt.getText().equalsIgnoreCase(arr)) {
				opt.click();
				System.out.println(arr+" checkbox is clicked");
				break;
			}
			Assert.fail(arr+" checkbox is not clicked");
		}
		
	}
	}
	
	

	public static void dragdrop(WebElement source,WebElement dest) {
		Actions act= new Actions(driver);
		act.dragAndDrop(source, dest).perform();
	}

	public static void Multiplemouseover(WebElement ele1,WebElement ele2) {
		Actions act= new Actions(driver);
		act.moveToElement(ele1).moveToElement(ele2).click().perform();
	}

	public static void performaction(WebElement ele,String action) {
		Actions act= new Actions(driver);
		switch (action) {
		case "rightclick":
			act.contextClick(ele).perform();;
			break;
		case "doubleclick":
			act.doubleClick(ele).perform();;
		case "mouseover":
			act.moveToElement(ele).perform();

		default:
			break;
		}


	}

	public static void lnkclick(WebElement ele) {
		try {
				ele.click();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void drop(List<WebElement>options,String value) {
		for (WebElement opt : options) {
			if(opt.getText().equals(value)) {
				opt.click();
				break;
			}

		}
	}

	public static void scroll(int x,int y) {

		JavascriptExecutor jse=  (JavascriptExecutor)driver;
//		jse.executeScript("window.scrollBy(0,700)");
		jse.executeScript("window.scrollBy("+x+","+y);
	}

	
	public void handlealert() {
		try {
		Alert alert = driver.switchTo().alert();
		String alertmsg = alert.getText();
		System.out.println(alertmsg);
		alert.accept();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void handleCheckbox(String attribute) {
		try {
			driver.findElement(By.id(attribute)).click();
		}catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void generate_randomnum() {
		Date dt= new Date();
		System.out.println(dt.getTime());
	}
	
	public static boolean validate() {
		return driver.findElement(By.id("code")).isSelected();
	}
	

	public static String getvaluefromTable(int colcompare,String tableid_xpath,String condition,int getcol) {
		//get row cnt
		boolean valfound=false;
		String txt=null;
		List<WebElement> rowele = driver.findElements(By.xpath(tableid_xpath+"//tbody//tr"));
		int rowcnt = rowele.size();
		for(int i=2 ;i<=rowcnt;i++) {
			String exptext = driver.findElement(By.xpath(tableid_xpath+"//tbody//tr["+i+"]//td["+colcompare+"]")).getText();
			if(exptext.equalsIgnoreCase(condition)) {
				txt=driver.findElement(By.xpath(tableid_xpath+"//tbody//tr["+i+"]//td["+getcol+"]")).getText();
				valfound=true;
				return txt;
			}
		}
		Assert.fail(condition+" Value not found in webtable");
		valfound=false;
		return null;
	}

	public static boolean performactionTable(int colcompare,String tableid_xpath,String condition,int getcol,String tagtype) {
		//get row cnt
		boolean valfound=false;
		List<WebElement> rowele = driver.findElements(By.xpath(tableid_xpath+"//tbody//tr"));
		int rowcnt = rowele.size();
		for(int i=2 ;i<=rowcnt;i++) {
			String exptext = driver.findElement(By.xpath(tableid_xpath+"//tbody//tr["+i+"]//td["+colcompare+"]")).getText();
			if(exptext.equalsIgnoreCase(condition)) {

				driver.findElement(By.xpath(tableid_xpath+"//tbody//tr["+i+"]//td["+getcol+"]//"+tagtype)).click();

				valfound=true;

				break;
			}
		}
		Assert.fail(condition+" Value not found in webtable");
		return valfound;

	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\Reports\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}
}
