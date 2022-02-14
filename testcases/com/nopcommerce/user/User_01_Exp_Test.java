package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class User_01_Exp_Test {
	WebDriver driver;
	BasePage basePage;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		basePage = BasePage.getBasePage();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
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
