package utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import utils.ConfigParameters;

public class Config {
	
	private static Logger logger = Logger
			.getLogger(Config.class);
	
	private Properties properties;
	
	public Config() {
		properties = new Properties();
	}
	
	public Config(String configFile) {
		this();
		loadProperties(configFile);
	}
	
	public String getProperty(ConfigParameters parameter) {
		return properties.getProperty(parameter.getParameterName());
	}
	public String getProperty(String parameterName) {
		return properties.getProperty(parameterName);
	}
	
	public void loadProperties(String configFile) {
		try {			
			properties.load(new FileInputStream(configFile));
		} catch (Exception e) {
			logger.error("Cannot load " + configFile + " file: ", e);
		}
	}
}