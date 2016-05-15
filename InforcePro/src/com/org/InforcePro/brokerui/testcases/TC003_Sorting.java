package com.org.InforcePro.brokerui.testcases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.org.InforcePro.brokerui.pages.HomePage;
import com.org.InforcePro.brokerui.pages.LoginPage;
import com.org.InforcePro.brokerui.utils.BrowserDriver;
import com.org.InforcePro.brokerui.utils.InforceProUtilities;
import com.org.InforcePro.brokerui.utils.PropertyReader;



public class TC003_Sorting {
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
				e.printStackTrace();
				System.exit(1);
			}
			
		}
    @Test(description="TC003_Sorting")
	public void test(){
			// Click on Login
			loginpage.clickLoginLink();
			Assert.assertTrue(loginpage.isUsernameAndPasswordTextboxPresent(),"Login Click Unsuccessfull");
			
			loginpage.fillUserName(testData.getProperty("username"));
			loginpage.fillPassword(testData.getProperty("password"));
			
			loginpage.clickLoginSubmit();
			Assert.assertEquals(loginpage.isVisibleLogout(), true);
			
			homepage.performSort();
			InforceProUtilities.getScreenShot(com.org.InforcePro.brokerui.testcases.TC003_Sorting.class.getSimpleName(), driver);
			List<WebElement> sortedData = homepage.getSortedPolicyNumber();
			if(sortedData.size()==0 || sortedData.size()==1){
				Assert.assertTrue(false,"Sorting cannot be checked for this data, there should be atleast two records");
			}
			
			//As discussed storing the sorted data to a file
			FileWriter file = null;
			try {
				file = new FileWriter("SortedPolicyNumber.txt");
				for(WebElement data : sortedData){
					 file.write(data.getText()+"\n");
					 
				}
				file.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			try{
			for(int i=2;i<sortedData.size();i++){
				if(!(Integer.valueOf(sortedData.get(i-1).getText()) < Integer.valueOf(sortedData.get(i).getText()))){
						Assert.assertTrue(false,"Policy Number are not in sorted form");
				    }
			}
			}
			catch(NumberFormatException e ){
				Assert.assertTrue(false,"Policy Number contains String also, cannot be sorted");
			}
			
			loginpage.clickLogout();
			Assert.assertEquals(loginpage.isVisibleLoginLink(), true);
			
			
	}
}
