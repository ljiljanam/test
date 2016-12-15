package utils;

public enum ConfigParameters {
	
	WEB_URL("web.url", "URL of web site"),
	ACCOUNT_NAME("account.name", "Username for account login"),
	ACCOUNT_PASS("account.pass", "Password for account login"),
	WEBDRIVER_DEFAULT("webdriver.default", "Default webdriver to use in test");
	
	String parameterName;
	String description;
	
	private ConfigParameters(String parameterName, String description) {
		this.parameterName = parameterName;
		this.description = description;
	}
	
	public String getParameterName() {
		return parameterName;
	}

	public String getDescription() {
		return description;
	}
	
}
