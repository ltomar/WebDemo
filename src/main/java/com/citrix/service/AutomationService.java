package com.citrix.service;

import javax.xml.bind.JAXBException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.citrix.ui.exception.UIAutomationException;


public class AutomationService extends UIAutomation {



	@BeforeTest(groups = { "prerequisite" },alwaysRun=true)
	@Parameters({ "testConfigFile", "configFile"})
	public void init(String testConfigFile,String configFile) throws UIAutomationException, JAXBException {
		super.init(testConfigFile,configFile);
	}
    

	@BeforeMethod(groups={"prerequisite"},alwaysRun=true)
	@Parameters({ "proxy" })
	public void start(String proxy) throws UIAutomationException {
		super.Start();
	}
	
	
	@Override
	@AfterTest(groups={"prerequisite"},alwaysRun=true)
	public void shutDown() throws UIAutomationException {
		super.shutDown();
	}
}
