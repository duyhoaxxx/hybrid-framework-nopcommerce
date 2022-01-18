package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class User_02_MyAccount extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		openPageUrl(driver, "https://demo.nopcommerce.com/");
		//https://docs.google.com/document/d/16N5CVHwX4tVhtgvsKAggNCN6COeZSz2Onlfv8wDFo8E/edit
	}

	@Test
	public void TC_01() {
		System.out.println("hi");
	}
	
	@Test
	public void TC_02() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
