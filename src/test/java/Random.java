import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Random extends CommonMethods {


	public Logger log;


	@Test(priority=2,enabled=false)
	public void login() throws InterruptedException {
		//		Thread.sleep(3000);
		scrollviewelement(driver.findElement(By.linkText("Test Login Page")));
		try {
			driver.findElement(By.linkText("Test Login Page")).click();
			scrollviewelement(driver.findElement(By.id("username")));
			driver.findElement(By.id("username")).sendKeys("practice");
			driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
			//scrollviewelement(driver.findElement(By.xpath("//button[text()='Login']")));
			driver.findElement(By.xpath("//button[text()='Login']")).click();
			Assert.assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Secure Area page for Automation Testing Practice']")).isDisplayed());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail("Login to app failed---Error message-"+ e.getMessage());
		}

	}

	@Test(enabled=false)
	public void radio_click() throws InterruptedException {
		scrollviewelement(driver.findElement(By.linkText("Radio Buttons")));
		driver.findElement(By.linkText("Radio Buttons")).click();
		scrollviewelement(driver.findElement(By.xpath("//input[@name='color']/following-sibling::label")));
		rd(driver.findElements(By.xpath("//input[@name='color']/following-sibling::label")),"Blue", "fav colour");
		rd(driver.findElements(By.xpath("//input[@name='sport']/following-sibling::label")),"Football","fav sport");
	}
	
	public void check_click() throws InterruptedException {
		scrollviewelement(driver.findElement(By.linkText("Check Boxes")));
		driver.findElement(By.linkText("Check Boxes")).click();
		chk(driver.findElements(By.xpath("//input[@id='checkbox1']")), null, null);
	}

	@Test(enabled=false)
	public void webinputs() throws InterruptedException {
		scrollviewelement(driver.findElement(By.linkText("Web inputs")));
		driver.findElement(By.linkText("Web inputs")).click();
		scrollviewelement(driver.findElement(By.id("input-number")));
		driver.findElement(By.id("input-number")).sendKeys("12");
		driver.findElement(By.id("input-text")).sendKeys("Testing");
		driver.findElement(By.id("input-password")).sendKeys("test");
		driver.findElement(By.id("btn-display-inputs")).click();
		
		String outputnum=getele("//div[@class='col']/child::strong[@id='output-number']").getText();
		Assert.assertEquals(outputnum, "14","output num not matched");
		
		String outputtxt = getele("//div[@class='col']/child::strong[@id='output-text']").getText();
		Assert.assertEquals(outputtxt, "Testing","outputtxt not matched");
		
		String pass = getele("//div[@class='col']/child::strong[@id='output-password']").getText();
		Assert.assertEquals(pass,"tes","Password not matched");
	}
	
	@Test(enabled=false)
	public void list() throws InterruptedException {
		driver.get("https://practice.expandtesting.com/dropdown");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//		scrollviewelement(driver.findElement(By.linkText("Dropdown List")));
//		driver.findElement(By.linkText("Dropdown List")).click();
		scrollviewelement(driver.findElement(By.id("country")));
		sel_val(driver.findElement(By.id("dropdown")), "2", "simpledrop");
		sel_visibletxt(driver.findElement(By.id("country")),"Albania", "country");
		sel_visibletxt(driver.findElement(By.id("elementsPerPageSelect")),"20", "DOB");
		driver.close();
	}
	
	@Test(enabled=false)
	public void learnassert() {
		driver.get("https://practice.expandtesting.com/assertions/should-be");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//1.enable
		Assert.assertTrue(driver.findElement(By.id("btn3")).isEnabled(),"button is not enabled");
		
		//2.disabled
		Assert.assertFalse(driver.findElement(By.id("btn4")).isEnabled(),"button is not disabled");
		
		//3.visible
		Assert.assertTrue(driver.findElement(By.id("btn1")).isDisplayed(),"btn is not visible");
		
		//4.hiddenbtn
		Assert.assertFalse(driver.findElement(By.id("btn2")).isDisplayed(),"btn is not hidden it is visible");
		
		//5.isempty
		Assert.assertTrue(driver.findElement(By.id("div1")).getText().isEmpty(),"field is not empty");
	}
	
	public void mouseover() {
		
	}
	public void frame() {
		driver.get("https://practice.expandtesting.com/iframe");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.switchTo().frame(0);
		//complete
	}
	
	@Test(enabled=false)
	public void handlewin() {
		driver.get("https://practice.expandtesting.com/windows");
		String firstwindowtitle = driver.getTitle();
		System.out.println("firstwintitle: "+firstwindowtitle);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.linkText("Click Here")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowlst= new ArrayList<String>(windowHandles);
		String firstwindowid = windowlst.get(0);
		String secondwinid = windowlst.get(1);
		driver.switchTo().window(secondwinid);
		System.out.println("second win title "+driver.getTitle());
		Assert.assertEquals(driver.getTitle(),"Example of a new window");
		driver.close();
		driver.switchTo().window(firstwindowid);
		driver.close();
	}
	
	@Test(enabled=false)
	public void autocompletetest() throws InterruptedException {
		try {
		driver.get("https://practice.expandtesting.com/autocomplete");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("country")).sendKeys("a");
		scrollviewelement(driver.findElement(By.xpath("//div[@class='autocomplete-items']/child::div")));
		autocomplete(driver.findElements(By.xpath("//div[@class='autocomplete-items']/child::div//input")),"Albania");
		}catch(Exception e) {
			Assert.fail("Testcase failed due to error--"+e.getMessage());
		}
	}
	
	@Test(enabled=false)
	public void dynamictablecheck() {
		driver.get("https://practice.expandtesting.com/dynamic-table");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement ele = getele("//table[@class='table table-striped']//tbody//tr//td[contains(text(),'Chrome')]//following-sibling::td[contains(text(),'Mbps')]");
		Assert.assertEquals(ele.getText(),"8.7 Mbps","Network value mismatched");
	}
	
	@Test(enabled=false)
	public void dynamicload() {
		driver.get("https://practice.expandtesting.com/dynamic-loading/2");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//div[@id='start']//button")).click();
		//WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("loading"))));
		Assert.assertEquals(driver.findElement(By.id("finish")).getText(),"Hello World!");
	}

	@Test(enabled=false)
	public void contextclick() {
		driver.get("https://practice.expandtesting.com/context-menu");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		Actions act= new Actions(driver);
		act.contextClick(driver.findElement(By.id("hot-spot"))).click().perform();
		
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		Assert.assertEquals(alert.getText(),"You selected a context menu");
		alert.accept();
		driver.close();
	}
	
	@Test(enabled=false)
	public void javascriptdialog() {
		driver.get("https://practice.expandtesting.com/js-dialogs");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.xpath("//button[@id='js-alert']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"I am a Js Alert");
		alert.accept();
		
		
		driver.findElement(By.xpath("//button[@id='js-confirm']")).click();
		Alert alert2 = driver.switchTo().alert();
		alert2.dismiss();
		
		
		driver.findElement(By.xpath("//button[@id='js-prompt']")).click();
		Alert alert3 = driver.switchTo().alert();
		alert3.sendKeys("Testing prompt");
		alert3.accept();
		
		
		Assert.assertEquals(driver.findElement(By.id("dialog-response")).getText(),"Testing prompt","Alert prompt not equal");
	}
	
	
	@Test
	public void tablecheck2() {
		
		driver.get("https://practice.expandtesting.com/tables");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement ele = getele("//table[@id='table1']//tbody//tr//td[contains(text(),'Conway')]/following-sibling::td[contains(text(),'@')]");
		System.out.println(ele.getText());
	}
}

