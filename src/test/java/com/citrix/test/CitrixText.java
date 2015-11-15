
package com.citrix.test;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.citrix.action.WebActions;
import com.citrix.model.Occurance;
import com.citrix.model.Webinar;
import com.citrix.model.WebinarTime;
import com.citrix.pageobjects.ScheduleMeetingPO;
import com.citrix.service.AutomationService;
import com.citrix.ui.exception.UIAutomationException;
import com.citrix.web.utility.Login;
import com.citrix.web.utility.ScheduleMeeting;
import com.citrix.web.utility.WebinarObjectFactory;



@SuppressWarnings("unused")
public class CitrixText extends AutomationService
{	

   
    @Test(groups = "runnable")
    public static void testCreateWebinar()
        throws UIAutomationException, InterruptedException, NumberFormatException
    {
    	ScheduleMeetingPO meeting = new ScheduleMeetingPO();
    	String userName=session().getConfigProperty("userName");
    	String password =session().getConfigProperty("password");
    	String meetingName="M_"+new Object().hashCode();
    	String description = "My test meeting";
    	
    	//get app url property from testconfig.properties file
    	String appUrl=session().getTestConfigProperty("appUrl");
    	//open citrix app
        WebActions.openpage(appUrl);
        //Login to app using Login helper
        Login.login(userName, password);
        //Construct schedule object
        WebinarTime schedule = new WebinarTime();
        schedule.setStartDay("12");
        schedule.setStartMonth("December");
        schedule.setStartYear("2015");
        schedule.setStartTime("1");
        schedule.setStartTime_AMPM("AM");
        schedule.setEndTime("4");
        schedule.setEndTime_AMPM("PM");
        schedule.setTimeZone("(GMT-09:00) Alaska");
        //get webinar object from factory
        Webinar web =WebinarObjectFactory.getWebinarObject(meetingName, description , Occurance.Daily, schedule, "English");
        ScheduleMeeting.scheduleMeeting(web);
    }


}
