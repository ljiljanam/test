package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentMessagesPage extends BasePage {

	public SentMessagesPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(className = "designer_dropdown_name")
	private WebElement logOutIcon;
	
	public WebElement getLastSentMessage(){
		return driver.findElements(By.xpath("//tr[starts-with(@id, 'j-message')]")).get(0);
	}
	
	public String getRecepientFromLastSentMessage(){
		WebElement row = getLastSentMessage();
		WebElement recepient = row.findElement(By.xpath("//td[1]"));
		return recepient.getText();
	}
	
	public String getSubjectAndMessageFromLastSentMessage(){
		WebElement row = getLastSentMessage();
		WebElement recepient = row.findElement(By.xpath("//td[3]"));
		return recepient.getText();
	}
	
	public void logOut(){
		logOutIcon.click();
		driver.findElement(By.linkText("Logout")).click();
	}
}
