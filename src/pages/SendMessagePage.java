package pages;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.PageUtils;

public class SendMessagePage extends BasePage{
	
	private static Logger logger = Logger.getLogger(SendMessagePage.class);

	public SendMessagePage(WebDriver driver) {
		super(driver);
	}
	@FindBy(id = "MessageSendToAllConnections")
	private WebElement sendToAllConnectionsRadioButton;
	
	@FindBy(id = "MessageSendToSelectConnections")
	private WebElement sendToSelectedConnectionsRadioButton;
	
	@FindBy(id = "MessageSubject")
	private WebElement messageSubjectTextBox;
	
	@FindBy(id = "MessageBody")
	private WebElement messageBodyTextArea;
	
	@FindBy(name = "recipient")
	private WebElement recipientTextBox;
	
	private String sendButtonText = "SEND";
	
	public boolean isSendToAllConnectionsButtonDisplayed(){
		return sendToAllConnectionsRadioButton.isDisplayed();
	}
	
	public boolean isSendToSelectedConnectionsButtonDisplayed(){
		return sendToSelectedConnectionsRadioButton.isDisplayed();
	}
	
	public void clickOnSendToAllConnectionsButton(){
		if(isSendToAllConnectionsButtonDisplayed())
			sendToAllConnectionsRadioButton.click();
	}
	
	public void clickOnSendToSelectedConnectionsButton(){
		if(isSendToSelectedConnectionsButtonDisplayed())
			sendToSelectedConnectionsRadioButton.click();
	}
	
	public void setMessageSubject(String messageSubject){
		PageUtils.typeText(messageSubjectTextBox, messageSubject);
	}
	
	public void setMessageText(String message){
		PageUtils.typeText(messageBodyTextArea, message);
	}
	
	public void setRecipientName(){
	}
	
	public void clickOnSendMessageButton(){
		driver.findElement(By.linkText(sendButtonText)).click();
	}
	
	public String addRandomRecipient(String firstLetterOfName){
		clickOnSendToSelectedConnectionsButton();
		PageUtils.typeText(recipientTextBox, firstLetterOfName);
		List<WebElement> list = driver.findElements(By.className("ui-menu-item"));
		WebElement element = list.get(0);
		String recipient = element.getText();
		element.click();
		return recipient;
	}
	
	public SentMessagesPage goToSentMessages(){
		PageUtils.clickOnMenuItem(driver, "SENT");
		return PageFactory.initElements(driver, SentMessagesPage.class);
	}
}
