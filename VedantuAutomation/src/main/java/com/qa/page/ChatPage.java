package com.qa.page;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ChatPage {
	
	public WebDriver driver;
	Logger logger = LogManager.getLogger(ChatPage.class);

	@FindBy(xpath="//input[@placeholder='Your name (click to edit)']")
	WebElement yourNameTextBox;
	
	@FindBy(xpath="//span[contains(@class,'gSVBBi')]")
	WebElement chatCallerName;
	
	public ChatPage(WebDriver driver) {  
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
     /* The purpose of this method is to enter caller name */
	public void enterCallerName(String callerName){
		logger.info("User is on chat room page");
		yourNameTextBox.sendKeys(callerName);
		logger.info("User has entered his name");
	}
	
	/*The purpose of this test is to validate caller name.*/
	public ChatPage validateCallerName(String expectedCallerName){
		String actualCallerName = chatCallerName.getText();
		Assert.assertEquals(actualCallerName, expectedCallerName);	
		logger.info("Caller name is verified.");
		return this;
	}	
}
