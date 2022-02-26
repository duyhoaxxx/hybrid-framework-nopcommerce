package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageUIs.jQuery.PageGeneratorManager;

public class Topic_11_Upload_File extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	String fileName1="20221.jpg";
	String fileName2="20222.jpg";
	String fileName3="20223.jpg";
	String fileName4="20224.jpg";
	String fileName5="20225.jpg";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appurl) {
		driver = getBrowserDriver(browserName, appurl);
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.zoomOut(driver);
		homePage.openPageUrl(driver, "https://blueimp.github.io/jQuery-File-Upload/");
	}

	@Test
	public void TC_01_Paging() {
		homePage.uploadMultipleFiles(driver, fileName1, fileName2, fileName5);
		Assert.assertTrue(homePage.isFileLoadedByName(fileName1, fileName2, fileName5));
		homePage.sleepInSecond(5);
	}

	@Test
	public void TC_02_Login_User() {
		
	}

	@Test
	public void TC_03_Get_Value() {
		
	}
	
	@Test
	public void TC_04_Action_At_Any_Row() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
