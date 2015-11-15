/**
 * 
 */
package com.citrix.core;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.citrix.ui.exception.UIAutomationException;

public class UIAutomationDriver implements WebDriver{
	//private static Logger logger = Logger.getLogger(UIAutomationDriver.class); 
	private RemoteWebDriver driver;
	private UIAutomationFactory automationFactoryObject;

	/**
	 * 
	 */
	public UIAutomationDriver() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * 
	 */
	public UIAutomationDriver(RemoteWebDriver driver, UIAutomationFactory uiAutomationFactory) {
		this.automationFactoryObject=uiAutomationFactory;
		this.setDriver(driver);
	}

	/**
	 * @param desiredCapabilities
	 */
	public UIAutomationDriver(Capabilities desiredCapabilities) {
		setDriver(new RemoteWebDriver(desiredCapabilities));
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param executor
	 * @param desiredCapabilities
	 */
	public UIAutomationDriver(CommandExecutor executor,
			Capabilities desiredCapabilities) {
		setDriver(new RemoteWebDriver(executor, desiredCapabilities));
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param remoteAddress
	 * @param desiredCapabilities
	 */
	public UIAutomationDriver(URL remoteAddress,Capabilities desiredCapabilities, UIAutomationFactory uiAutomationFactory) {
		this.automationFactoryObject=uiAutomationFactory;
		setDriver(new RemoteWebDriver(remoteAddress, desiredCapabilities));

	}


	/**
	 * @return the driver
	 */
	public RemoteWebDriver getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(RemoteWebDriver driver) {
		this.driver = driver;
		String browser = automationFactoryObject.getTestConfigProperty("browserName");
		if(!(browser.equalsIgnoreCase("chrome")|| browser.equalsIgnoreCase("gc"))){
			setImplicitWait();
			setPageLoadTimeout();
		}

	}

	/**
	 * 
	 */
	public void setImplicitWait() {
		this.setImplicitWait(Long.parseLong(automationFactoryObject.getConfigProperty("implicitWait")));
	}

	/**
	 * 
	 * @param timeout
	 */
	public void setImplicitWait(long timeout) {
		if(!automationFactoryObject.getTestConfigProperty("browserName").equalsIgnoreCase("android")){
			this.driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * 
	 */
	public void setPageLoadTimeout() {
		if(!automationFactoryObject.getTestConfigProperty("browserName").equalsIgnoreCase("android")){
			this.setPageLoadTimeout(Long.parseLong(automationFactoryObject.getConfigProperty("pageLoadTimeout")));
		}
	}

	/**
	 * 
	 * @param timeout
	 */
	public void setPageLoadTimeout(long timeout) {
		this.driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
	}

	

	public By getBy(String locatorName)
	{
		By by = null;

		int start = locatorName.indexOf("=");
			String key = locatorName.split("=")[0];
			
			String value = locatorName.substring(start+1);
			if (key.equalsIgnoreCase("id")) {
				by = By.id(value);
			} else if (key.equalsIgnoreCase("name")) {
				by = By.name(value);
			} else if (key.equalsIgnoreCase("css")) {
				by = By.cssSelector(value);
			} else if (key.equalsIgnoreCase("xpath")) {
				by = By.xpath(value);
			} else if (key.equalsIgnoreCase("class")) {
				by = By.className(value);
			} else if (key.equalsIgnoreCase("link")) {
				by = By.linkText(value);
			} else if (key.equalsIgnoreCase("tag")) {
				by = By.tagName(value);
			} else if (key.equalsIgnoreCase("plink")) {
				by = By.partialLinkText(value);
			} else {
				Assert.assertTrue("Locator doesn't start with one of these values id,name,css,xpath,link,tag,plink. Example id=something, name=something etc", false);
			}

			// fail the test if by is null
			Assert.assertNotNull("By object can not be null. Please verify the value of locator " + locatorName, by);
		

		return by;

	}


	/**
	 * This method would wait for the element to be present in DOM
	 *
	 * 
	 *
	 */
	public boolean waitForElement(String locator) throws TimeoutException{
		WebElement element=null;
		WebDriverWait elementWait = new WebDriverWait(getDriver(), Long.parseLong(automationFactoryObject.getConfigProperty("implicitWait")));
		element= elementWait.until(ExpectedConditions.presenceOfElementLocated(getBy(locator)));
		if(element==null)
			return false;
		return true;
	}

	/**
	 * This method would wait for the element to be present in DOM
	 * This method is mainly used in the UIAutomationElementWait aspect
	 *
	 */
	public boolean waitForElement(By by) throws TimeoutException{
		WebElement element=null;
		WebDriverWait elementWait = new WebDriverWait(getDriver(), Long.parseLong(automationFactoryObject.getConfigProperty("implicitWait")),800);
		element= elementWait.until(ExpectedConditions.visibilityOfElementLocated(by));
		if(element==null)
			return false;
		return true;
	}
	
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getConfigProperty(String key)
	{
		return automationFactoryObject.getConfigProperty(key);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getTestConfigProperty(String key)
	{
		return automationFactoryObject.getTestConfigProperty(key);
	}

	public void get(String url) {
		getDriver().get(url);
		
	}

	public String getCurrentUrl() {
		return getDriver().getCurrentUrl();
	}

	public String getTitle() {
		return getDriver().getTitle();
	}

	public List<WebElement> findElements(By by) {
		return getDriver().findElements(by);
	}

	public WebElement findElement(By by) {
		return getDriver().findElement(by);
	}

	public String getPageSource() {
		return getDriver().getPageSource();
	}

	public void close() {
		getDriver().close();
		
	}

	public void quit() {
		getDriver().quit();
		
	}

	public Set<String> getWindowHandles() {
		return getDriver().getWindowHandles();
	}

	public String getWindowHandle() {
		// TODO Auto-generated method stub
		return getDriver().getWindowHandle();
	}

	public TargetLocator switchTo() {
		
		return getDriver().switchTo();
	}

	public Navigation navigate() {
		// TODO Auto-generated method stub
		return getDriver().navigate();
	}

	public Options manage() {
		// TODO Auto-generated method stub
		return getDriver().manage();
	}

	public void waitForPageLoad() throws UIAutomationException, InterruptedException{
		
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		String status =  (String) js.executeScript("return document.readyState");

		int maxWaitTimeInSec =  Integer.parseInt(automationFactoryObject.getConfigProperty("pageLoadTimeout")); // need to externalize this so that suite wide default configuration can be provided
		long waittime = maxWaitTimeInSec * 1000;
		long starttime = System.currentTimeMillis();
		try {
			while (!status.equals("complete") && System.currentTimeMillis() < starttime + waittime) {
				Thread.sleep(500);
				status =  (String) js.executeScript("return document.readyState");
			}
		}catch (Exception e){

		}
		
		status = (String) js.executeScript("return document.readyState");
		if(!status.equals("complete"))
			Assert.assertTrue("The page has taken more than "+maxWaitTimeInSec+" seconds load", false); //this is to make sure that tests dont get hung while waiting for a pageload to complete
	}
	
}
