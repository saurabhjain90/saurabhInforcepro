package com.org.InforcePro.brokerui.setup;

import java.io.IOException;
import java.util.Properties;

import com.org.InforcePro.brokerui.utils.PropertyReader;

/**
 * 
 * @author saurabh
 *
 */
public class MasterConfig {
	private static final String MASTERCONFIG = "MasterConfig"; 
	public static final String BROWSER = "browser";
	public static final String RECIPIENTS = "recipients";
	
	public static Properties prop = null;
	static{
		try {
			prop = PropertyReader.getProperty(MASTERCONFIG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
