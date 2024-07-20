package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.WebUtil;

public class LoginPage extends LoginPageOR{
	private WebUtil wt;
	
	public LoginPage(WebUtil wa) {
		super(wa);
		this.wt=wa;	
		
	}
	
	public void clickOnBookFreeDemo() {
		wt.Click(Book_A_Free_Demo);
	}
	
	public void validCredential() {
		String stName=wt.getPropertyData("name");
		wt.Sendkeys(fullName, stName);
		
		String stEmail=wt.getPropertyData("email");
		wt.Sendkeys(buisnessEmail, stEmail);
		
		String stMobNumber=wt.getPropertyData("mobileNumber");
		wt.Sendkeys(phoneNumber, stMobNumber);
		
		wt.mySwitchToIframe(checkRemote, "I am not a Robot check box");
		
		wt.Click(checkRemote);
		
		wt.Click(get_A_Free_Demo);
		
		
	}
}
