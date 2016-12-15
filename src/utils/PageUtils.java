package utils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageUtils {
	public static int IMPLICIT_WAIT = 20;
	
	private static Logger logger = Logger.getLogger(PageUtils.class);
	private static String alphaChars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	
	public static void setImplicitWait(WebDriver driver, int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}
	
	public static void turnOnImplicitWait(WebDriver driver) {
		setImplicitWait(driver, IMPLICIT_WAIT);
	}
	
	public static void clickOnMenuItem(WebDriver driver, String link) {
		WebElement item = PageUtils.waitForElement(driver,
				By.linkText(link));
		if (item != null) {
			JavascriptExecutor ex = (JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", item);
		} else {
			logger.error("Menu item '" + link + "' does not exist.");
		}
	}
	
	public static WebElement waitForElement(WebDriver driver, By expectedElement) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, IMPLICIT_WAIT);
		element = wait.until(ExpectedConditions
				.presenceOfElementLocated(expectedElement));
		return element;
	}
	
	public static void typeText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
	public static String getRandomString(int iLength, String sAllowedChars) {

		Random rng = new Random();
		char[] text = new char[iLength];
		for (int i = 0; i < iLength; i++) {
			text[i] = sAllowedChars.charAt(rng.nextInt(sAllowedChars.length()));
		}
		return new String(text);
	}
	public static String getRandomAlphaCharactersString(int iLength) {
		return getRandomString(iLength, alphaChars);
	}
}
