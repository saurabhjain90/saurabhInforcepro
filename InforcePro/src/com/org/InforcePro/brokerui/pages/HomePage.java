package com.org.InforcePro.brokerui.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	public HomePage(WebDriver driver) {
		this.driver = driver; 
	}
	
	private WebDriver driver = null;
	private Properties properties = null;

	public String[] getSearchData(){
		String data[] = new String[2];
		data[0]=driver.findElement(By.xpath("//table[@id='row1']/tbody/tr[2]/td[1]")).getText();
		data[1]=driver.findElement(By.xpath("//table[@id='row1']/tbody/tr[2]/td[4]")).getText();
		return data;
	}
	public void search(String searchString){
		 driver.findElement(By.xpath("//input[@id='search']")).sendKeys(searchString);
		 driver.findElement(By.xpath("//input[@id='search']/../input[2][@value=' Search ']")).click();
	}
	public List<WebElement> getPostSearchData(){
		return driver.findElements(By.xpath("//table[@id='row1']/tbody/tr"));
	}
	public void performSort(){
		driver.findElement(By.xpath("//table[@id='row1']/tbody/tr[1]/td[1]/a[contains(text(),'Policy Number')]")).click();
	}
	public List<WebElement> getSortedPolicyNumber(){
		return driver.findElements(By.xpath("//table[@id='row1']//tr[*]/td[1]"));
	}
}
