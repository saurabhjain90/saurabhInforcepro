package com.org.InforcePro.brokerui.testcases;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.org.InforcePro.brokerui.pages.HomePage;
import com.org.InforcePro.brokerui.pages.LoginPage;
import com.org.InforcePro.brokerui.utils.BrowserDriver;
import com.org.InforcePro.brokerui.utils.InforceProUtilities;
import com.org.InforcePro.brokerui.utils.PropertyReader;



public class TC001_Login {
	private LoginPage loginpage = null;
	private Properties testData = null;
	private WebDriver driver = null;
	
		/**
		 * Setting up 
		 * 1) Browser Driver
		 * 2) Navigate to URL
		 * 3) initialize testData
		 */
    @BeforeSuite
	public void initialize(){
			try {
				driver = BrowserDriver.getDriver();
				loginpage = new LoginPage(driver);
				testData = PropertyReader.getTestdataProperty(TC001_Login.class.getSimpleName());
				driver.get(testData.getProperty("url"));
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			
		}
    @Test(description="TC001_Login Logout")
    public void test(){
			// Click on Login
			loginpage.clickLoginLink();
			Assert.assertTrue(loginpage.isUsernameAndPasswordTextboxPresent(),"Login Click Unsuccessfull");
			
			loginpage.fillUserName(testData.getProperty("username"));
			loginpage.fillPassword(testData.getProperty("password"));
			
			loginpage.clickLoginSubmit();
			Assert.assertEquals(loginpage.isVisibleLogout(), true);
			InforceProUtilities.getScreenShot(com.org.InforcePro.brokerui.testcases.TC001_Login.class.getSimpleName(), driver);
			loginpage.clickLogout();
			Assert.assertEquals(loginpage.isVisibleLoginLink(), true);
			
			
			
	}
    @AfterSuite
	public void tearDown(){
		driver.quit();
	}
}
