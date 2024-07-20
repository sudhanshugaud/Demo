package BaseTestRunner;

import org.testng.annotations.Test;
import PageWise.Laser.LoginPage;

public class LoginPageRunner extends BaseClass{

	
	@Test
	public void validSignin() {
		LoginPage login = new LoginPage(util);
		login.enterUserName(propObj.getProperty("UserName"));
		login.enterPassword(propObj.getProperty("Password"));
		login.clickOnSubmitBtn();
	}

}
