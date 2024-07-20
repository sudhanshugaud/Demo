package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebUtil;

public class CommonPageOR {

	
	public CommonPageOR(WebUtil wt) {
	  PageFactory.initElements(wt.getDriver(), this);
	}
	@FindBy(xpath="//a[text()='Sign Out']")
	protected WebElement logoutBT;
	
	
	
	
	
}
