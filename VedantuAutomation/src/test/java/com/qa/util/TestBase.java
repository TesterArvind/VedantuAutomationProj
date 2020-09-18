package com.qa.util;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import com.qa.page.LandingPage;

/**
 * This is a base util class for initialising the driver and property file.
 *
 * @author Arvind Kumar
 */


public class TestBase {

	public  WebDriver driver;
	public  Properties prop ;
	public WebDriver driver2;

    private static List<WebDriver> drivers= new ArrayList<WebDriver>();
  
	public TestBase(){
		prop = new Properties();
		try {
			InputStream input = new FileInputStream(System.getProperty("user.dir")+"\\input\\config.properties");
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String log4jpath= System.getProperty("user.dir")+"\\log4j.properties" ;
		PropertyConfigurator.configure(log4jpath);
	}


	public  LandingPage  lauchFirstChromeBrowser(){
				driver=initDriver();
            return new LandingPage(driver);
	}
	
	public  LandingPage  lauchSecondChromeBrowser(){
		driver2=initDriver();
		return new LandingPage(driver2);
	}
	
	public  WebDriver  initDriver(){
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\resource\\chromedriver\\chromedriver.exe");	
		
		ChromeDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		drivers.add(driver);
		return driver;
	}
	
	@AfterMethod
	public void tearDown(){
		for(WebDriver driver : drivers){
			driver.quit();
		}
	}
	
}
