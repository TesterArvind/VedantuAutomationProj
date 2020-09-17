package com.qa.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	Logger logger = LogManager.getLogger(LandingPage.class);
	public WebDriver driver;
	Robot robot;

	@FindBy(xpath="//input[contains(@class,'create-room-form-input')]")
	WebElement searchbox;

	@FindBy(xpath="//button[text()='Start a chat']")
	WebElement startchatbutton;

	@FindBy(xpath="//button/span[text()='Allow camera access']")
	WebElement allowCamerAaccessButton;

	@FindBy(xpath="//button/span[text()='Allow microphone access']")
	WebElement allowMicrophoneAccessButton;

	@FindBy(xpath="//button[text()='Join Call']")
	WebElement joinCallButton;		
				
	


	public LandingPage(WebDriver driver) {  
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


    /*The Purpose of this method is to enter chat room name in textbox at the centre of landing page and click on 
      Start chat button */
	public ChatPage enterRoomName(String name) throws Exception{
		logger.info("User is on landing page");	

		searchbox.sendKeys(name);
		logger.info("User has entered meeting room name");
		startchatbutton.click();
		if(allowCamerAaccessButton.getText().equals("Allow camera access")){
			allowCamerAaccessButton.click();
			allowAccess("camera");
		}	
		logger.info("User has selected allow access camera button");
		if(allowMicrophoneAccessButton.getText().equals("Allow microphone access")){
			allowMicrophoneAccessButton.click();	
			allowAccess("mic");
		}
		logger.info("User has selected allow access mic button");
		joinCallButton.click();
		logger.info("User has selected join call button");
		return new ChatPage(driver);
	}


    /*The purpose of this method is to handle browser's pop such allow microphone/camera */
	public void allowAccess(String name) throws InterruptedException{
		try {
			robot = new Robot();
			if(name.equals("mic")){
				int count =0;
				while(count<3){
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					robot.keyRelease(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					count++;
				}	
			}else if (name.equals("camera")){
				int counter =0;
				while(counter<2){
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					robot.keyRelease(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					counter++;
				}	
			}
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
