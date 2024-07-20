package PageWise.Laser;

import org.openqa.selenium.support.PageFactory;

import PageElement.Layer.LoginPageOR;
import Webutil.Layer.WebUtil;

public class LoginPage extends LoginPageOR {

	private WebUtil util;

	public LoginPage(WebUtil utilObj) {
		PageFactory.initElements(utilObj.getDriver(), this);
		util=utilObj;
	}


	public void enterUserName(String userName) {
		util.sendKeys(locUserName, userName, "User Name");
	}

	public void enterPassword(String password) {
		util.sendKeys(locPassword, password, "Password");
	}

	public void clickOnSubmitBtn() {
		util.click(locsubmitBtn, "Submit");
	}









}
