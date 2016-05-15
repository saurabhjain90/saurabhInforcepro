package com.org.InforcePro.brokerui.utils;

import java.util.concurrent.TimeUnit;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.org.InforcePro.brokerui.setup.MasterConfig;

/**
 * This is singleton class, only single object will be created in lifetime 
 * @author saurabh
 *
 */
public class BrowserDriver {

	private static WebDriver driver = null;
	private BrowserDriver() throws Exception {
		throw new Exception("Object Creation Not allowed");
	}
/**
 * 
 * @return driver
 * This method initilize driver based on browser mentioned in config files 
 */
	public static WebDriver getDriver(){
		if (driver == null){
			synchronized (BrowserDriver.class) {
				if(driver == null)
					if(MasterConfig.prop.get(MasterConfig.BROWSER).equals("firefox"))
						driver = new FirefoxDriver();
					else if( MasterConfig.prop.get(MasterConfig.BROWSER).equals("chrome")){
						DesiredCapabilities caps = DesiredCapabilities.chrome();
						ChromeOptions options = new ChromeOptions();
						options.addArguments("test-type");
						caps.setCapability("ignoreZoomSetting", true);
						caps.setCapability("ignoreProtectedModeSettings", true);
						caps.setCapability(ChromeOptions.CAPABILITY, options);
						System.setProperty("webdriver.chrome.driver", "./Setup/chromedriver");
						driver= new ChromeDriver(caps);
					}
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
		}
		return driver;
	}
}
