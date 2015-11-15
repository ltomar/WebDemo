/**
 * 
 */
package com.citrix.core;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.testng.log4testng.Logger;

import com.citrix.ui.exception.UIAutomationException;


public class UIAutomationFactory {
	
	private Logger logger = Logger.getLogger(UIAutomationFactory.class);

	private static Properties configProperties;
	private static Properties testConfigProperties;
	private WebDriverService webDriverService;

	 public static UIAutomationFactory uiAutomationFactory = null;


	public synchronized static UIAutomationFactory getInstance(String testconfigFile, String configFile)
			throws UIAutomationException, JAXBException {
		uiAutomationFactory= new UIAutomationFactory(testconfigFile, configFile);
		return uiAutomationFactory;
	}

	private UIAutomationFactory(String testconfigFile, String configFile) throws UIAutomationException, JAXBException {
		loadTestConfig(testconfigFile);
		loadDriverConfig(configFile);
		
		

		// start the WebDriverService
		setWebDriverService(WebDriverService.getInstance(this));

		
	}

	/**
	 * Loads the config file
	 * 
	 * @param configFile
	 * @throws UIAutomationException
	 */
	private void loadDriverConfig(String configFile)
			throws UIAutomationException {
		logger.info("loading config file:" + configFile);
		configProperties = new Properties();
		try {
			configProperties.load(new FileReader(new File(
					configFile)));
		} catch (IOException e) {
			throw new UIAutomationException(
					"Error while loading the driver property file:"
							+ configFile, e.fillInStackTrace());
		}

	}
	
	/**
	 * Loads the config file
	 * 
	 * @param configFile
	 * @throws UIAutomationException
	 */
	private void loadTestConfig(String testconfigFile)
			throws UIAutomationException {
		logger.info("loading test config file:" + testconfigFile);
		testConfigProperties = new Properties();
		try {
			testConfigProperties.load(new FileReader(new File(
					testconfigFile)));
		} catch (IOException e) {
			throw new UIAutomationException(
					"Error while loading the driver property file:"
							+ testconfigFile, e.fillInStackTrace());
		}

	}

	/**
	 * @return the webDriverService
	 */
	public WebDriverService getWebDriverService() {
		return webDriverService;
	}

	/**
	 * @param webDriverService the webDriverService to set
	 */
	public void setWebDriverService(WebDriverService webDriverService) {
		this.webDriverService = webDriverService;
	}


	/**
	 * Gets the property value from driver config property file
	 * 
	 * @param key
	 *            - Propert key
	 * @return {@link String}
	 */
	public String getConfigProperty(String key) {
		return configProperties.getProperty(key);
	}

	/**
	 * Sets a new property
	 * 
	 * @param key
	 *            - Property key
	 * @param value
	 *            - Property value
	 * @return {void}
	 */

	public void setConfigProperty(String key, String value) {
		configProperties.setProperty(key, value);
	}
	
	
	/**
	 * Gets the property value from driver config property file
	 * 
	 * @param key
	 *            - Propert key
	 * @return {@link String}
	 */
	public String getTestConfigProperty(String key) {
		return testConfigProperties.getProperty(key);
	}

	public void setTestConfigProperty(String key, String value) {
		testConfigProperties.setProperty(key, value);
	}


}
