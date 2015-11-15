/**
 * 
 */
package com.citrix.service;

import javax.xml.bind.JAXBException;

import com.citrix.core.UIAutomationDriver;
import com.citrix.core.UIAutomationFactory;
import com.citrix.ui.exception.UIAutomationException;


public class UIAutomation {
	
	
	
	private static ThreadLocal<UIAutomationFactory> uiAutomationFactory = new InheritableThreadLocal<UIAutomationFactory>();
	
	private static ThreadLocal<UIAutomationDriver> session = new InheritableThreadLocal<UIAutomationDriver>();
	
	public static void unset() {
		session.remove();
	}

	public static UIAutomationDriver getSession() {
		return session.get();
		
	}
	
	public static UIAutomationFactory getAutomationFactry() {
		return uiAutomationFactory.get();
		
	}
	
	/**
	 * @throws JAXBException 
	 * @throws UIAutomationException 
	 * 
	 */
	public void init(String testconfigFile, String configFile) throws UIAutomationException, JAXBException
	{
		
		uiAutomationFactory.set(UIAutomationFactory.getInstance(testconfigFile,configFile));
		
	}
	public void Start() throws UIAutomationException
	{
		session.set(getAutomationFactry().getWebDriverService().getDriver());
		session.get().manage().window().maximize();
		
	}
	public void shutDown() throws UIAutomationException
	{
		getAutomationFactry().getWebDriverService().quitDriver();
	}
	
	public static UIAutomationDriver session() throws UIAutomationException
	{
		//System.err.println(Thread.currentThread()+getSession().toString());
		return getSession();
	}

	/**
	 * @return the uiAutomationFactory
	 */
	public static UIAutomationFactory getUiAutomationFactory() {
		return getAutomationFactry();
	}
	
	public static void newSession() throws UIAutomationException
	{
		session.remove();
		session.set(getAutomationFactry().getWebDriverService().getDriver());
	}
	
	public static void closeSession() throws UIAutomationException
	{
		getAutomationFactry().getWebDriverService().quitDriver();
		session.remove();
	}

	
	
	
	
	
	
	 
	
	
}
