package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POM_login extends GenericMethods {

	
	@FindBy(name="username")
	WebElement txt_user;
	
	@FindBy(name="password")
	WebElement txt_pass;
	
	@FindBy(xpath="//button[@type='submit']") 
	WebElement btnlogin;
	
	@FindBy(xpath="//div[@class='oxd-topbar-header-title']")
	WebElement title_dash;
	
	
	public POM_login(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
				
	public void enteruser(String user) {
		enterval(txt_user,user, "Username");
	}
	
	public void enterpass(String password) {
		enterval(txt_pass,password, "Password");
	}
	
	
	public void clicklogin() {
		doclick(btnlogin, "Login");
	}

	public boolean validatedash() {
		return validate_isdisplayed(title_dash,"Dashboard page");
		
	}
}
