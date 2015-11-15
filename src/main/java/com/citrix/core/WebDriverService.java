/**
 * 
 */
package com.citrix.core;



import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.citrix.ui.exception.UIAutomationException;




public class WebDriverService implements IWebDriverService {

	
	private UIAutomationFactory automationFactoryObject;
	private static WebDriverService webDriverService = null;
	private static ThreadLocal<UIAutomationDriver> threadLocalWebDriver = new InheritableThreadLocal<UIAutomationDriver>();
	

	/**
	 * 
	 * @param automationFactoryObject
	 * @return
	 */
	public static synchronized WebDriverService getInstance(
			UIAutomationFactory automationFactoryObject) {
		if (webDriverService == null)
			webDriverService = new WebDriverService(automationFactoryObject);

		return webDriverService;

	}

	public WebDriverService(UIAutomationFactory automationFactoryObject) {
		this.automationFactoryObject = automationFactoryObject;
	}

	public UIAutomationDriver createDriver() throws UIAutomationException {

		
		UIAutomationDriver driver = threadLocalWebDriver.get();

		try {
			System.out.println("THREAD :" + Thread.currentThread()
					+ " BEFORE SESSION STARTED:" + driver);

			if (driver == null) {
				driver = getDriverByCapability();
				threadLocalWebDriver.set(driver);
			}
			System.out.println("THREAD :" + Thread.currentThread()
					+ " AFTER SESSION STARTED:"
					+ driver);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UIAutomationException(
					"Error while creating session for thread:"
							+ Thread.currentThread(), e);
		}

		return driver;

	}

	public UIAutomationDriver getDriver() throws UIAutomationException {
		UIAutomationDriver webDriver = null;
		if (threadLocalWebDriver.get() == null) {
			webDriver = this.createDriver();
		} else {
			webDriver = threadLocalWebDriver.get();
		}
		System.out.println("THREAD :" + Thread.currentThread() + " SESSION:"
				+ webDriver.toString());
		return webDriver;
	}

	public void quitDriver() throws UIAutomationException {
		//WebDriver driver = threadLocalWebDriver.get();
		System.out.println("QUITING DRIVER: "+Thread.currentThread().getName()+" " + getDriver());
		if (getDriver() != null) {
			getDriver().quit();
			threadLocalWebDriver.remove();
		} else {
			throw new UIAutomationException(
					"Unable to quit the driver session this "
							+ Thread.currentThread().getName() + " thread");
		}
		
		
	}

	public void quitAllDriver() throws UIAutomationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Get the Remote web driver instance based on driver capability mentioned
	 * as per driver config file
	 * 
	 * @return {@link WebDriver}
	 * @throws Exception 
	 */

	private UIAutomationDriver getDriverByCapability()
			throws Exception {
		System.out.println("Creating driver capability:"+ this.automationFactoryObject);

		String browser = automationFactoryObject.getTestConfigProperty("browserName");
		System.out.println("Browser name ->" + browser);

		String version = automationFactoryObject.getTestConfigProperty("browserVersion");
		System.out.println("Browser version ->" + version);

		Platform platform = this.getPlatform();
		System.out.println("platform ->" + platform.toString());

		return getLocalDriver(browser, platform);
		
	}

	/**
	 * Get the local web driver instance based on the browser provided as per
	 * driver config file.It does not check for the browser version.
	 * 
	 * @return {@link WebDriver}
	 * @throws Exception 
	 */
	private UIAutomationDriver getLocalDriver(String browserName, Platform platform)
			throws Exception {
		
		UIAutomationDriver webDriver = null;
		browserName = browserName.trim();
		
		if (browserName.toLowerCase().contains("chrome") || browserName.equalsIgnoreCase("gc")) {
			// start the chrome driver service
			startChromeDriverService(platform);
			DesiredCapabilities capabilities =DesiredCapabilities.chrome();
			LoggingPreferences logs = new LoggingPreferences();
			logs.enable(org.openqa.selenium.logging.LogType.DRIVER, java.util.logging.Level.OFF);
			
		      capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
			String switches[] = { "--ignore-certificate-errors",
					"--disable-popup-blocking", "--disable-traslate",
			"--start-maximized","--disable-extensions", "--silent" };
			capabilities.setCapability("chrome.switches", switches);
			
			if(automationFactoryObject.getTestConfigProperty("platform").contains("win")) {
				capabilities.setCapability("chrome.binary", System.getProperty("user.home")+File.separator+"\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe --silent");
			System.setProperty("webdriver.chrome.driver", FilenameUtils.separatorsToSystem(FilenameUtils.normalize("./src/main/resources/ChromeDriver/win/chromedriver.exe")));
			}
			else {
				capabilities.setCapability("chrome.binary","/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
				System.setProperty("webdriver.chrome.driver", FilenameUtils.separatorsToSystem(FilenameUtils.normalize("./src/main/resources/ChromeDriver/mac/chromedriver")));
			}
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			capabilities.setJavascriptEnabled(true);
		//	capabilities.setCapability(CapabilityType.PROXY, proxy);
			capabilities.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
			capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.PLATFORM, platform);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			webDriver = new UIAutomationDriver(new ChromeDriver(capabilities), automationFactoryObject);
            
		}
		
		
		return webDriver;
	}

	private String getAbsolutePath(String path)
	{
		String absPath=null;
		if(path.startsWith("."))
			path=path.substring(2, path.length());
		
		try {
			File directory = new File (path);
			File cwd = new File(".");
			absPath=cwd.getCanonicalPath()+File.separator+directory.getPath();
		}catch(Exception e) {
			System.out.println("Exceptione is ="+e.getMessage());
		}
		return absPath;
	}

	
	/**
	 * 
	 * @param platform
	 * @return
	 */
	private Platform getPlatform() {
		String platform;
		Properties sysProperties = System.getProperties();
		platform=sysProperties.getProperty("os.name").toLowerCase();
		if (platform.contains("windows 7")) {
			return Platform.WINDOWS;
		} else if (platform.contains("mac")) {
			return Platform.MAC;
		} else if (platform.contains("xp")) {
			return Platform.XP;
		} else {
			return Platform.ANY;
		}
	}

	
	private void startChromeDriverService(Platform platform) throws IOException {
		String driverPath = null;
		// get the executable path of the chromedriver based on the platform
		if (platform.is(Platform.MAC))
			driverPath = getAbsolutePath(automationFactoryObject.getConfigProperty("chromeMacLocation"));
		else if (platform.is(Platform.WINDOWS))
			driverPath = getAbsolutePath(automationFactoryObject.getConfigProperty("chromeWinLocation"));
			
		// set the system property as required to start the chrome driver
		System.setProperty("webdriver.chrome.driver", driverPath);

	}

	
}
