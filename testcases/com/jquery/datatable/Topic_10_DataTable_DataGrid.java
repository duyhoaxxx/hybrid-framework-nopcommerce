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
		Assert.assertTrue(homePage.isPageNumberActived("10"));
		homePage.openPagingByPageNumber("15");
		Assert.assertTrue(homePage.isPageNumberActived("15"));
		homePage.openPagingByPageNumber("3");
		Assert.assertTrue(homePage.isPageNumberActived("3"));
		homePage.openPagingByPageNumber("1");
		Assert.assertTrue(homePage.isPageNumberActived("1"));
	}

	@Test
	public void TC_02_Login_User() {
		homePage.enterToHeaderTextboxByLabel("Females","777");
		homePage.sleepInSecond(2);
	}

	@Test
	public void TC_03_Get_Value() {
		//homePage.getValueEachRowAtAllPage();
		homePage.getCountryAtAllPage();
	}
	
	@Test
	public void TC_04_Action_At_Any_Row() {
		homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		homePage.clickLoadDataButton();
		homePage.enterToTextboxByColumnNameAtRowNumber("Album", "1", "Summer");
		homePage.enterToTextboxByColumnNameAtRowNumber("Artist", "2", "Kane");
		homePage.enterToTextboxByColumnNameAtRowNumber("Year", "3", "1990");
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "1", "Japan");
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "1", "US");
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "1");
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "3");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "2");
		homePage.clickToIconByRowNumber("1","Insert Row Above");
		homePage.sleepInSecond(3);
		homePage.clickToIconByRowNumber("1","Remove Current Row");
		homePage.sleepInSecond(3);
		homePage.clickToIconByRowNumber("1","Move Up");
		homePage.sleepInSecond(3);
		homePage.clickToIconByRowNumber("1","Move Down");
		homePage.sleepInSecond(3);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
