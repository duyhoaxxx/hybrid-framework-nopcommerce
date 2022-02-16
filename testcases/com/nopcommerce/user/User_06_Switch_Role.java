package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class User_06_Switch_Role extends BaseTest {

	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	WebDriver driver;
	private String firstName, lastName, email, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Kane";
		lastName = "Pham";
		email = fakeEmail();
		password = "123456";
	}

	private String fakeEmail() {
		return "AutoTest" + String.valueOf((new Random().nextInt(9999))) + "@gmail.com";
	}

	@Test
	public void TC_01_Register_Valid() {
		userRegisterPage = userHomePage.clickToResgisterLink();

		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(email);
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox(password);

		userRegisterPage.clickToRegisterButton();

		Assert.assertEquals(userRegisterPage.getSuccessRegisterMessage(), "Your registration completed");

		userHomePage = userRegisterPage.clickToLogOutLink();
	}

	@Test
	public void TC_02_Login_User() {
		userLoginPage = userHomePage.clickToLoginLink();
		userHomePage = userLoginPage.LoginAsUser(email, password);

		Assert.assertEquals(userHomePage.getTopicBlockTitle(), "Welcome to our store");
	}

	@Test
	public void TC_03_Login_Admin() {
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		Assert.assertEquals(userHomePage.getTopicBlockTitle(), "Welcome to our store");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
