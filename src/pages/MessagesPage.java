package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import utils.PageUtils;

public class MessagesPage extends BasePage{

	public MessagesPage(WebDriver driver) {
		super(driver);
	}
	
	public SendMessagePage goToSendMessagesPage(){
		PageUtils.clickOnMenuItem(driver, "Send a Message");
		return PageFactory.initElements(driver, SendMessagePage.class);
	}

}
