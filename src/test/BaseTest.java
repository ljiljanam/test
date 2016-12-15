package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.util.Strings;

import pages.HomePage;
import utils.Browsers;
import utils.Config;
import utils.ConfigParameters;
import utils.PageUtils;

public class BaseTest {

	private static Logger logger = Logger.getLogger(BaseTest.class);
	public final String defaultConfigFile = "resources/conf/test.properties";

	protected static Config testConfig;
	private static Browsers defaultBrowser;
	protected String webSite;
	protected WebDriver driver;
	private static Map<Browsers, WebDriver> browserDriverMap = new HashMap<Browsers, WebDriver>();

	@BeforeClass(alwaysRun = true)
	public void setUpSelenium() throws Exception {
		webSite = testConfig.getProperty(ConfigParameters.WEB_URL);
	}
	
	@BeforeSuite(alwaysRun = true)
	@Parameters({ "test.config" })
	public void configSetUp(@Optional(defaultConfigFile) String configFile) {
		logger.debug("Load test properties from file " + configFile);
		if(Strings.isNullOrEmpty(configFile)) {
			throw new RuntimeException("configFile parameter is null or empty.");
		}
		testConfig = new Config(configFile);
		defaultBrowser = Browsers.valueOf(testConfig.getProperty(ConfigParameters.WEBDRIVER_DEFAULT));
	}
	
	public Config getTestConfig() {
		return testConfig;
	}

	public void setTestConfig(Config testConfig) {
		BaseTest.testConfig = testConfig;
	}
	
	public HomePage getHomePage(String webSite, Browsers browser, boolean secure) {
		driver = initWebDriver(browser);
		setDriverParameters();
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		home.setSite(webSite);
		home.setSecure(secure);
		driver.get(home.getSiteAddress());
		return home;
	}
	
	public WebDriver initWebDriver(Browsers browser) {
		if(browserDriverMap.containsKey(browser)) {
			return browserDriverMap.get(browser);
		}
		WebDriver webDriver = null;
		switch (browser) {
		case FIREFOX:
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			//profile.setAssumeUntrustedCertificateIssuer(false);
			webDriver = new FirefoxDriver(profile);
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver",
					"resources/selenium/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			webDriver = new ChromeDriver(options);
			break;
		}
		browserDriverMap.put(browser, webDriver);
		return webDriver;
	}
	
	private void setDriverParameters() {
		PageUtils.turnOnImplicitWait(driver);
		driver.manage().window().maximize();
	}
	
	public HomePage getHomePage(String webSite, Browsers browser) {
		return getHomePage(webSite, browser, false);
	}

	public HomePage getHomePage(String webSite) {
		return getHomePage(webSite, defaultBrowser);
	}
	
	@DataProvider(name = "browsersDP")
	public Object[][] getAllBrowsers() {
		return new Object[][] { {defaultBrowser},
		};
	}
	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		for (Entry<Browsers, WebDriver> pair : browserDriverMap.entrySet()) {
			if (pair.getValue() != null) {
				pair.getValue().quit();
			}			
		}
		
		Set<Browsers> keys = browserDriverMap.keySet();
		for (Browsers b : keys) {
			browserDriverMap.remove(b);
		}
		
	}

}
