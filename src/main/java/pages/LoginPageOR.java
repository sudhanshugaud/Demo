package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebUtil;

public class LoginPageOR {

	public LoginPageOR(WebUtil wt) {
		PageFactory.initElements(wt.getDriver(), this);

	}
	@FindBy(xpath="(//button[text()='Book a Free Demo'])[2]")
	protected WebElement Book_A_Free_Demo; 

	@FindBy(xpath="//input[@name='FullName']")
	protected WebElement fullName;
	
	@FindBy(xpath="//input[@name='Email']")
	protected WebElement buisnessEmail;
	
	@FindBy(xpath="//input[@name='Contact']")
	protected WebElement phoneNumber;
	                            
	@FindBy(xpath="//div[@class='recaptcha-checkbox-border']")
	protected WebElement checkRemote;
	
	@FindBy(xpath="//input[@name='action_submitForm']")
	protected WebElement get_A_Free_Demo;






}
