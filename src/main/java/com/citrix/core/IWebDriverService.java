package com.citrix.core;

/**
 * 
 */


import com.citrix.ui.exception.UIAutomationException;


public interface IWebDriverService {

	/* creates the thread specific driver instance */
	public UIAutomationDriver createDriver() throws UIAutomationException;

	/* Gets the thread specific driver instance */
	public UIAutomationDriver getDriver() throws UIAutomationException;

	/* Quits the driver instances */
	public void quitDriver() throws UIAutomationException;

	public void quitAllDriver() throws UIAutomationException;
}
