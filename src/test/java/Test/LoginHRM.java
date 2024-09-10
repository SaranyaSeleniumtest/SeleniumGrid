package Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.Base;
import Utilities.POM_login;


public class LoginHRM extends Base {
	
	@Test(dataProvider="data")
	public void logintest(String user,String pass) {
		POM_login login= new POM_login(driver);
		logger.info("1.Launched url");
		login.enteruser(user);
		logger.info("2. Entered user");
		login.enterpass(pass);
		logger.info("3. Entered password");
		login.clicklogin();
		logger.info("4. validate the login info");
		Assert.assertTrue(login.validatedash(),"Dashboad page not displayed. Login failed for user "+user);
		logger.info("5. Login successfull for the user "+user );
	
	}

	@DataProvider(indices = 0)
	public Object[][] data() {
	
		Object[][]obj= {{"Admin","admin123"},{"user","pass"}};
		return obj;
	}
}
