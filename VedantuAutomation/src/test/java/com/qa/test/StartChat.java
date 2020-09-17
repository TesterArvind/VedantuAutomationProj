package com.qa.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.util.ExtentReportListner;
import com.qa.util.TestBase;



@Listeners(ExtentReportListner.class)
public class StartChat extends TestBase {
	TestBase base = new TestBase();
	
	@Test
	public void validateStartChatFunctionality() throws Exception {
		base
		.lauchFirstChromeBrowser()
		.enterRoomName(prop.getProperty("ChatRoomName")).enterCallerName(prop.getProperty("callerName"));
		
		
		base
		.lauchSecondChromeBrowser()
		.enterRoomName(prop.getProperty("ChatRoomName")).validateCallerName(prop.getProperty("expectedCallerName"));
	 }
}
