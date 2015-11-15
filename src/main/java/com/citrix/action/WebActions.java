package com.citrix.action;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.citrix.pageobjects.ScheduleMeetingPO;
import com.citrix.service.AutomationService;
import com.citrix.ui.exception.UIAutomationException;
import com.citrix.web.utility.TimeFormate;

public class WebActions extends AutomationService{
	
	public static void openpage(String url) throws UIAutomationException, InterruptedException {
	    // read the url from the testconfig.properties file
	    String appUrl = getUiAutomationFactory().getTestConfigProperty("appUrl");

	    if(url.equalsIgnoreCase("$server") || url.isEmpty()) {
			url=appUrl;
		} else if (!url.startsWith("http://") && !url.startsWith("https://")) {
		    
		    String httpString = appUrl.substring(0, appUrl.indexOf("//") + "//".length());
			url=httpString.concat(url);
		}
		
		System.out.println("Opening page : "+url);
		session().get(url);
		System.out.println("Waiting for page : "+url+" to load");
		session().waitForPageLoad();
	}

	public static void verifybuttontext(String value,String locator,String text) throws UIAutomationException
	{
		locator = getLocator(locator,value);
		System.out.println("verifying presence of button text "+text);
		String actualText= session().findElement(session().getBy(locator)).getText();
		Assert.assertEquals(actualText.contains(text),true, "verifybuttontext with text "+text+" failed. Found actual text :"+actualText+" on the button");
	
		System.out.println("button with text "+text+" present");
	}
	
	public static void clickbutton(String locator , String val) throws UIAutomationException, InterruptedException
	{
		session().waitForPageLoad();
		
		locator = getLocator(locator, val);
		session().findElement(session().getBy(locator)).click();
		System.out.println("Waiting for page to render due to click on button with \""+val+"\"");
		session().waitForPageLoad();

	}
	
	public static void clickcheckbox(String locator, String val) throws UIAutomationException
	{
		System.out.println("Inside clickcheckbox...");
		locator = getLocator(locator, val);
		
			session().findElement(session().getBy(locator)).click();
	}
	
	public static void clickradiobutton(String locator, String value) throws UIAutomationException, InterruptedException
	{
		locator = getLocator(locator, value);
		
		if(!session().findElement(session().getBy(locator)).isSelected()) {
			System.out.println("selecting radion button referred by "+value);
			//session().Element(radioButtonId).click();
			session().findElement(session().getBy(locator)).click();
		}
		session().waitForPageLoad();
	}
	public static void wait(String seconds) throws NumberFormatException, UIAutomationException, InterruptedException
	{
		System.out.println("Waiting for "+seconds+" seconds...");
		Thread.sleep(Integer.parseInt(seconds)*1000);
	}

	
	public static void setText(String locator, String value, String text) throws UIAutomationException, InterruptedException
	{
		
		if(text==null || text.isEmpty())
			return;
		locator = getLocator(locator, value);

		WebElement element =session().findElement(session().getBy(locator));
		element.click();

		
		System.out.println("Typing text \""+text+"\" in textfield with "+value+"...");
       
        element.clear();
        
          //session().switchTo().activeElement().sendKeys("");
          session().switchTo().activeElement().sendKeys(text);
                    session().switchTo().activeElement().sendKeys(Keys.TAB);
                    

		session().waitForPageLoad();
		wait("1");
	}
	
	public static void setTextVal(String locator, String value, String text) throws UIAutomationException, InterruptedException
	{
		
		if(text==null || text.isEmpty())
			return;
		locator = getLocator(locator, value);

		WebElement element =session().findElement(session().getBy(locator));
		element.click();

		
		System.out.println("Typing text \""+text+"\" in textfield with "+value+"...");
       
        element.clear();
        
          session().switchTo().activeElement().sendKeys(text);
                    session().switchTo().activeElement().sendKeys(Keys.TAB);
                    

		session().waitForPageLoad();
		wait("1");
	}
	private static String getLocator(String locator, String value){
		return locator.concat("=").concat(value);
	}
	
	public static void pickDate(String month,String year,String day) throws UIAutomationException, InterruptedException {
		ScheduleMeetingPO meeting = new ScheduleMeetingPO();
		String yearLocator = getLocator(meeting.getMEETING_DATEPICKER_YEAR_LOCATOR(), meeting.getMEETING_DATEPICKER_YEAR_VALUE());
		String currentYear = session().findElement(session().getBy(yearLocator)).getText();
		String monthLocator = getLocator(meeting.getMEETING_DATEPICKER_MONTH_LOCATOR(), meeting.getMEETING_DATEPICKER_MONTH_VALUE());
		String currentMonth = session().findElement(session().getBy(monthLocator)).getText();
		while(!currentYear.equalsIgnoreCase(year) || !currentMonth.equalsIgnoreCase(month))
		{
			clickbutton(meeting.getMEETING_DATEPICKER_NEXT_LOCATOR(), meeting.getMEETING_DATEPICKER_NEXT_VALUE());
			currentYear = session().findElement(session().getBy(yearLocator)).getText();
			currentMonth = session().findElement(session().getBy(monthLocator)).getText();;
		}
		String dayLocator = getLocator(meeting.getMEETING_DATEPICKER_DAY_LOCATOR(), meeting.getMEETING_DATEPICKER_DAY_VALUE());
		List<WebElement> list = session().findElements(session().getBy(dayLocator));
		for(WebElement elem : list){
			if(elem.getText().equals(day)){
				((RemoteWebElement)elem).click();
				break;
			}
		}
	}
	

	
	public static void select(String locator, int index) throws UIAutomationException {
		//locator = getLocator(locator, value);
		for(int i=0;i<index;i++)
		{
			session().getDriver().switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);
		}
		session().getDriver().switchTo().activeElement().sendKeys(Keys.ENTER);

		//System.out.println("Selected the Desired Option: " + optionText + ", Under the Select Dropdown: " + value);
	}
}
