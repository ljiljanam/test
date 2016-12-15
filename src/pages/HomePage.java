package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.PageUtils;

public class HomePage extends BasePage {
	
	private static Logger logger = Logger.getLogger(HomePage.class);
	
	@FindBy(className="login-button")
	private WebElement loginButton; 
	
	@FindBy(id = "login-name")
	private WebElement userNameTextBox;
	
	@FindBy(name = "data[User][password]")
	private WebElement userPasswordTextBox;
	
	@FindBy(id = "sign-in-form")
	private WebElement signInForm;
	
	@FindBy(css="input[type='submit'][value='Sign In']")
	private WebElement signInButton;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	public HomePage(WebDriver driver, String server) {
		super(driver, server);
	}

	public boolean isLoginButtonDisplayed(){
		return loginButton.isDisplayed();
	}
	
	public boolean isLoginFormDisplayed(){
		return signInForm.isDisplayed();
	}
	
	public void clickOnLoginButton(){
		if(isLoginButtonDisplayed())
			loginButton.click();		
	}
	
	public void loginUser(String username, String password) {
		clickOnLoginButton();
		if (isLoginFormDisplayed()) {
			userNameTextBox.sendKeys(username);
			userPasswordTextBox.sendKeys(password);
			signInButton.click();
		}
			
	}
	
	public MessagesPage goToMessagesPage(){
		PageUtils.clickOnMenuItem(driver, "Messages");
		return PageFactory.initElements(driver, MessagesPage.class);
	}
	
}
