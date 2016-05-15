package com.org.InforcePro.brokerui.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * This class is for reading property file
 * @author saurabh
 *
 */
public class PropertyReader {

	private static final String MASTERCONFIG = "MasterConfig"; 
	private static final String CONFIGFILE = "config.properties"; 

	private PropertyReader() throws Exception{
		throw new Exception("Instance creation not allowed");
	}

	/**
	 * @author saurabh
	 * @param filename
	 * @return Property object
	 * @throws IOException
	 */
	public static Properties getProperty(String filename) throws IOException{
		Properties prop = new Properties();
		try {
			if(filename.equalsIgnoreCase(MASTERCONFIG))
				prop.load(new FileInputStream("./"+CONFIGFILE));
			else
				prop.load(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FileNotFoundException("Can't find the property file");
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException();
		}
		return prop;
	}
	/**
	 * 
	 * @param testCaseName
	 * @return Property
	 * @throws IOException
	 * This Method get the Property which are present in Testdata folder
	 */
	public static Properties getTestdataProperty(String testCaseName) throws IOException{
		return getProperty("./TestData/"+testCaseName+".properties");
	}

	/**
	 * 
	 * @param PageName
	 * @return Property
	 * @throws IOException
	 */

	public static Properties getPagedataProprty(String PageName) throws IOException{
		return getProperty("./ElementRepository/"+PageName+".properties");
	}
}