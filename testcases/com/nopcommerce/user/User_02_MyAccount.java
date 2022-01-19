package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class User_02_MyAccount extends BasePage {
	
	HomePageObject homePage;
	RegisterPageObject registerPage;
	
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
	public void TC_01_Register_Empty_Data() {
		homePage.clickToResgisterLink();
			
		registerPage.clickToRegisterButton();
		
		registerPage.getErrorMessageAtFirstNameTextbox();
		registerPage.getErrorMessageAtLastNameTextbox();
		registerPage.getErrorMessageAtEmailTextbox();
		registerPage.getErrorMessageAtPasswordTextbox();
		registerPage.getErrorMessageAtConfirmPasswordTextbox();
		
	}
	
	@Test
	public void TC_02_() {
		homePage.clickToResgisterLink();
		
		registerPage.inputToFirstNameTextbox();
		registerPage.inputToLastNameTextbox();
		registerPage.inputToEmailTextbox();
		registerPage.inputToPasswordTextbox();
		registerPage.inputToConfirmPasswordTextbox();
		
		registerPage.clickToRegisterButton();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

		
	}
}
