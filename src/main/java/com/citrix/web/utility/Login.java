package com.citrix.web.utility;

import com.citrix.action.WebActions;
import com.citrix.pageobjects.LoginPO;
import com.citrix.ui.exception.UIAutomationException;

public class Login {
	
	//Login to app
	
	public static void login(String userName, String password) throws UIAutomationException, InterruptedException{
		LoginPO login = new LoginPO();
		WebActions.setText(login.getUSERNAMELOCATOR(), login.getUSERNAMEVALUE(), userName);
        WebActions.setText(login.getPASSWORDLOCATOR(), login.getPASSWORDVALUE(), password);
        WebActions.clickbutton(login.getUSERNAMELOCATOR(), login.getSUBMITVALUE());
	}

}
