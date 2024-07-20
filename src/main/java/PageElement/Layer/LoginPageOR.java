package PageElement.Layer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPageOR {
	
	
	@FindBy(name = "username")
	protected WebElement locUserName;
	
	@FindBy(name = "password")
	protected WebElement locPassword;
	
	@FindBy(xpath = "//button[@type=\"submit\"]")
	protected WebElement locsubmitBtn;
	
	@FindBy(xpath = "//span[text()='Dashboard']")
	protected WebElement locDashboard;

}
