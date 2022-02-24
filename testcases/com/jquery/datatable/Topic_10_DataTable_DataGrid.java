package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageUIs.jQuery.PageGeneratorManager;

public class Topic_10_DataTable_DataGrid extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appurl) {
		driver = getBrowserDriver(browserName, appurl);
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.zoomOut(driver);
	}

	@Test
	public void TC_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(2);
		homePage.openPagingByPageNumber("15");
		homePage.sleepInSecond(2);
		homePage.openPagingByPageNumber("3");
		homePage.sleepInSecond(2);
	}

	@Test
	public void TC_02_Login_User() {
	}

	@Test
	public void TC_03_Login_Admin() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
