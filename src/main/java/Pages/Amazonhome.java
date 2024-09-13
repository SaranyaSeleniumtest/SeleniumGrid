package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.GenericMethods;

public class Amazonhome extends GenericMethods {
	
	@FindBy(name="field-keywords")
	WebElement txt_search;
	
	@FindBy(id="nav-search-submit-button")
	WebElement btn_search;
	
	@FindBy(xpath="//a/child::span[text()='Apple iPhone 15 Pro Max (256 GB) - Black Titanium']")
	WebElement result;
	
	public Amazonhome(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	
	public void Enteritem(String data) {
		enterval(txt_search,data, "searchtxt");
	}
	
	public void clicksearch() {
		doclick(btn_search,"searchbtn");
	}
	
	public void clicksearchresult() {
		doclick(result, "search result");
		handlewindows();
	}
}
