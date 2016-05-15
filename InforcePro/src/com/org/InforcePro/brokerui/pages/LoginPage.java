package com.org.InforcePro.brokerui.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public LoginPage(WebDriver driver) {
		this.driver = driver; 
	}
	
	private WebDriver driver = null;

	public void clickLoginLink(){
		driver.findElement(By.xpath("//li[@id='login']//a[@href='https://inforcepro.com/login']")).click();
	}
	public boolean isVisibleLoginLink(){
		return driver.findElement(By.xpath("//li[@id='login']//a[@href='https://inforcepro.com/login']")).isDisplayed();
	}
	public boolean isUsernameAndPasswordTextboxPresent(){
		return driver.findElement(By.xpath("//input[@id='l_email']")).isDisplayed() & driver.findElement(By.xpath("//input[@id='l_email']")).isDisplayed();
	}
	
	public void fillUserName(String username){
		driver.findElement(By.xpath("//input[@id='l_email']")).clear();
		driver.findElement(By.xpath("//input[@id='l_email']")).sendKeys(username);
	}
	
	public void fillPassword(String password){
		driver.findElement(By.xpath("//input[@id='l_password']")).clear();
		driver.findElement(By.xpath("//input[@id='l_password']")).sendKeys(password);
	}
	public void clickLoginSubmit(){
		driver.findElement(By.xpath("//form[@action='https://inforcepro.com/system/index.php']/button[@type='submit']")).click();
	}
	public void clickLogout(){
		driver.findElement(By.xpath("//div[@id='info']/div/a[@href='https://inforcepro.com/system/logout' and text()='Logout']")).click();
	}
	public boolean isVisibleLogout(){
		return driver.findElement(By.xpath("//div[@id='info']/div/a[@href='https://inforcepro.com/system/logout' and text()='Logout']")).isDisplayed();
	}
	}
