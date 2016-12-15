package test;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.MessagesPage;
import pages.SendMessagePage;
import pages.SentMessagesPage;
import utils.Browsers;
import utils.PageUtils;

public class TestSendMessage extends BaseTest{
	
	HomePage homePage;
	MessagesPage messagesPage;
	SendMessagePage sendMessage;
	SentMessagesPage sentMessages;
	String username;
	String password;
	String messageSubject = "Test"+ PageUtils.getRandomAlphaCharactersString(5);
	String message = "Test" + PageUtils.getRandomAlphaCharactersString(5);
	String selectedRecipient;
	
	private static Logger logger = Logger.getLogger(TestSendMessage.class);
	
	@BeforeClass
	public void setUp(){
		username = testConfig.getProperty("qa1.account.name");
		password = testConfig.getProperty("qa1.account.pass");
	}
	
    /*	**TEST SCENARIO**
	
	1. Login to staging.joordev.com
	2. Go to Messages
	3. Click on Send Message page
	4. In Compose a Message pop-up choose "Select connection"
	5. Choose recipient from drop down list
	6. Fill in message subject and message body
	7. Click on send button
	
	Verification criteria
	1. After step 7, go to Sent messages
	2. Verify that message sent in step 7 is in Outbox and that message Recipient, subject and body are as expected
	*/
	

	@Test(dataProvider = "browsersDP")
	public void testSendSMS(Browsers browser){
		homePage = getHomePage(webSite, browser, true);
		homePage.loginUser(username, password);
		messagesPage = homePage.goToMessagesPage();
		sendMessage = messagesPage.goToSendMessagesPage();
		selectedRecipient = sendMessage.addRandomRecipient("j");
		sendMessage.setMessageSubject(messageSubject);
		sendMessage.setMessageText(message);
		sendMessage.clickOnSendMessageButton();
		sentMessages = sendMessage.goToSentMessages();
		Assert.assertEquals( selectedRecipient.startsWith(sentMessages.getRecepientFromLastSentMessage()),true);
		Assert.assertEquals(sentMessages.getSubjectAndMessageFromLastSentMessage(), messageSubject + " - " +message);
		sentMessages.logOut();
	}

}
