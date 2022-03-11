package com.nopcommerce.user;

import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class User_06_Switch_Role extends BaseTest {

	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	WebDriver driver;
	public static Set<Cookie> LoginPageCookie;
	private String firstName, lastName, email, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userHomePage.zoomOut(driver);

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

		log.info("Register with email" + email + "   Pass: 123456");
		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(email);
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox(password);

		userRegisterPage.clickToRegisterButton();

		Assert.assertEquals(userRegisterPage.getSuccessRegisterMessage(), "Your registration completed");

		userHomePage = userRegisterPage.ClickToLogoutLinkAtUserPage(driver);
	}

	@Test
	public void TC_02_Login_User() {
		userLoginPage = userHomePage.clickToLoginLink();
		userHomePage = userLoginPage.LoginAsUser(email, password);

		LoginPageCookie = userHomePage.getAllCookies(driver);
		log.info("Verify");
		verifyEquals(userHomePage.getTopicBlockTitle(), "Welcome to our store");
		log.info("Assert");
		Assert.assertEquals(userHomePage.getTopicBlockTitle(), "Welcome to our store");
		userHomePage = userHomePage.ClickToLogoutLinkAtUserPage(driver);
	}

	@Test
	@Step("Login_Admin")
	public void TC_03_Login_Admin() {
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage = adminLoginPage.LoginAsUser(AdminLoginPageUI.EMAIL_ADMIN, AdminLoginPageUI.PASSWORD_ADMIN);
		Assert.assertTrue(adminDashboardPage.isDashboardPageDisplayed());
		adminLoginPage = adminDashboardPage.ClickToLogoutLinkAtAdminPage(driver);

		adminLoginPage.openPageUrl(driver, GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userLoginPage = userHomePage.clickToLoginLink();
		showBrowserConsoleLogs(driver);

		userLoginPage.setAllCookies(driver, User_06_Switch_Role.LoginPageCookie);
		userLoginPage.sleepInSecond(5);
		userLoginPage.refreshCurrentPage(driver);
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	private void afterClass(String browserName) {
		log.info("Post-Condition: Close browser " + browserName);
		cleanBrowserAndDriver();
	}
}
