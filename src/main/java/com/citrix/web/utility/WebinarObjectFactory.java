package com.citrix.web.utility;

import com.citrix.model.Occurance;
import com.citrix.model.Webinar;
import com.citrix.model.WebinarTime;

final public class WebinarObjectFactory{

	   private WebinarObjectFactory(){
		   
	   }
	   
	   public static Webinar getWebinarObject(String name,String description,Occurance o,WebinarTime time,String language) {
	
		   Webinar web = new Webinar();
		   web.setName(name);
		   web.setDescription(description);
		   web.setStartDay(time.getStartDay());
		   web.setStartMonth(time.getStartMonth());
		   web.setStartYear(time.getStartYear());
		   web.setStartTime(time.getStartTime());
		   web.setStartTime_AMPM(time.getStartTime_AMPM());
		   web.setEndTime(time.getEndTime());
		   web.setEndTime_AMPM(time.getEndTime_AMPM());
		   web.setTimeZone(time.getTimeZone());
		   web.setOccurance(o);
		   web.setLanguage(language);
		   return web;
	   }
}
