package pages;

import org.openqa.selenium.WebDriver;


public class BasePage {
	
	protected WebDriver driver;
	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * @return the secure
	 */
	public boolean isSecure() {
		return secure;
	}

	/**
	 * @param secure the secure to set
	 */
	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	protected String site;
	protected boolean secure;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public BasePage(WebDriver driver, String site) {
		this(driver);
		this.site = site;
	}
	
	public String getSiteAddress() {
		String protocol = secure ? "https" : "http";
		String url = protocol + "://" + site + "/";
		return url;
	}
	public void closePage() {
		driver.close();
	}
}
