package testscript;

import org.testng.annotations.Test;

import pages.LoginPage;

public class AutomationScript extends BaseTest{

	@Test
	public void Automate() {
		LoginPage lp=new LoginPage(wt);
		lp.clickOnBookFreeDemo();
		lp.validCredential();




	}



}


