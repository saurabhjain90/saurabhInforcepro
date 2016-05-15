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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.org.InforcePro.brokerui.pages.HomePage;
import com.org.InforcePro.brokerui.pages.LoginPage;
import com.org.InforcePro.brokerui.utils.BrowserDriver;
import com.org.InforcePro.brokerui.utils.InforceProUtilities;
import com.org.InforcePro.brokerui.utils.PropertyReader;



public class TC002_Search {
	private LoginPage loginpage = null;
	private HomePage homepage = null;
	private Properties testData = null;
	private WebDriver driver = null;
	
		/**
		 * Setting up 
		 * 1) Browser Driver
		 * 2) Navigate to URL
		 * 3) initialize testData
		 */
	@BeforeClass
	public void initialize(){
			try {
			    driver = BrowserDriver.getDriver();
				loginpage = new LoginPage(driver);
				homepage = new HomePage(driver);
				testData = PropertyReader.getTestdataProperty(TC001_Login.class.getSimpleName());
				driver.get(testData.getProperty("url"));
				
			} catch (IOException e) {
				//e.printStackTrace();
				//System.exit(1);
		}
			
		}
    @Test(description="TC002_Search")
	public void test(){
			// Click on Login
			loginpage.clickLoginLink();
			Assert.assertTrue(loginpage.isUsernameAndPasswordTextboxPresent(),"Login Click Unsuccessfull");
			
			loginpage.fillUserName(testData.getProperty("username"));
			loginpage.fillPassword(testData.getProperty("password"));
			
			loginpage.clickLoginSubmit();
			Assert.assertEquals(loginpage.isVisibleLogout(), true);
			
			String preSearchText[] = homepage.getSearchData();
			homepage.search(preSearchText[1]);
			String postSearchText[] = homepage.getSearchData();
			System.out.println("Pre"+preSearchText);
			System.out.println("Post"+postSearchText);
			
			//Assuming here that there is only one Policy for each Client 
			if(!(preSearchText[0].equals(postSearchText[0]) && preSearchText[1].equals(postSearchText[1])))			
					Assert.assertTrue(false,"Search Functionality Not Working");
			
			//Assuming here that there are multiple Policy for each Client 
			List<WebElement> searchData = homepage.getPostSearchData();
			boolean skipFlag =false;
			int counter=0;
			if(searchData.get(1).getText().contains("No records matching search found"))
				Assert.assertTrue(false,"No records matching search found");

			for(WebElement element : searchData){
				if(skipFlag){
					if(!(element.getText().contains(preSearchText[0]) && element.getText().contains(preSearchText[1])))
						Assert.assertTrue(false,"Search Functionality Not Working");
				    }
				else
					skipFlag = true;
			}
			InforceProUtilities.getScreenShot(com.org.InforcePro.brokerui.testcases.TC002_Search.class.getSimpleName(), driver);
			loginpage.clickLogout();
			Assert.assertEquals(loginpage.isVisibleLoginLink(), true);
			
	}
}
