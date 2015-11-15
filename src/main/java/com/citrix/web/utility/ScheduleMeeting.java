package com.citrix.web.utility;

import com.citrix.action.WebActions;
import com.citrix.model.Webinar;
import com.citrix.pageobjects.ScheduleMeetingPO;
import com.citrix.ui.exception.UIAutomationException;

public class ScheduleMeeting {

	public static void scheduleMeeting(Webinar meeting) throws UIAutomationException, InterruptedException{
		
		ScheduleMeetingPO meeting1 = new ScheduleMeetingPO();
		WebActions.clickbutton(meeting1.getMEETING_SCHEDULE_LOCATOR(), meeting1.getMEETING_SCHEDULE_VALUE());
		WebActions.setText(meeting1.getMEETING_NAME_LOCATOR(), meeting1.getMEETING_NAME_VALUE(), meeting.getName());
		WebActions.setText(meeting1.getMEETING_DESCRIPTION_LOCATOR(), meeting1.getgetMEETING_DESCRIPTION_VALUE(), meeting.getDescription());
		WebActions.clickbutton(meeting1.getMEETING_RECCURENCE_LINK_LOCATOR(), meeting1.getMEETING_RECCURENCE_LINK_VALUE());
		WebActions.select(meeting1.getMEETING_RECCURENCE_LOCATOR(), getOccuranceCode(meeting.getOccurance().toString()));
		WebActions.clickbutton(meeting1.getMEETING_START_DATE_LOCATOR(), meeting1.getMEETING_START_DATE_VALUE());
		WebActions.pickDate(meeting.getStartMonth(), meeting.getStartYear(), meeting.getStartDay());
		WebActions.setText(meeting1.getMEETING_START_TIME_LOCATOR(), meeting1.getMEETING_START_TIME_VALUE(), meeting.getStartTime());
		WebActions.clickbutton(meeting1.getMEETING_SCHEDULE_START_AMPM_BUTTON_LOCATOR(), meeting1.getMEETING_SCHEDULE_START_AMPM_BUTTON_VALUE());
		WebActions.select(meeting1.getMEETING_START_AMPM_LOCATOR(), getAMPMCOde(meeting.getStartTime_AMPM()));
		WebActions.clickbutton(meeting1.getMEETING_SCHEDULE_END_AMPM_BUTTON_LOCATOR(), meeting1.getMEETING_SCHEDULE_END_AMPM_BUTTON_VALUE());
		WebActions.select(meeting1.getMEETING_END_AMPM_LOCATOR(), getAMPMCOde(meeting.getEndTime_AMPM()));
		WebActions.setText(meeting1.getMEETING_END_TIME_LOCATOR(), meeting1.getMEETING_END_TIME_VALUE(), meeting.getEndTime());
		WebActions.clickbutton(meeting1.getMEETING_LANGUAGE_BUTTON_LOCATOR(), meeting1.getMEETING_LANGUAGE_BUTTON_VALUE());
		WebActions.clickbutton(meeting1.getMEETING_SCHEDULE_BUTTON_LOCATOR(), meeting1.getMEETING_SCHEDULE_BUTTON_VALUE());
		
		
	}
	
	private static int getOccuranceCode(String occurance){
		if(occurance.equalsIgnoreCase("daily"))
			return 1;
		else if(occurance.equalsIgnoreCase("Weekly"))
			return 2;
		else if(occurance.equalsIgnoreCase("Monthly"))
			return 3;
		else if(occurance.equalsIgnoreCase("Custom"))
			return 4;
		else return 0;
	}
	
	private static int getAMPMCOde(String timeCode){
		if(timeCode.equalsIgnoreCase("AM"))
			return 0;
		else
			return 1;
	}
      
}
