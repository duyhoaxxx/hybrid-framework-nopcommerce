package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserBackInStockSubscriptionsPageObject;
import pageObjects.nopCommerce.user.UserChangePasswordPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserDownloadableProductsPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObjects.nopCommerce.user.UserOrdersPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;

public class Seminar_AutoTest extends BaseTest {
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressPageObject addressPage;
	private UserOrdersPageObject ordersPage;
	private UserDownloadableProductsPageObject downloadPage;
	private UserBackInStockSubscriptionsPageObject subsciptionsPage;
	private UserRewardPointsPageObject rewardPointsPage;
	private UserChangePasswordPageObject changePasswordPage;
	private UserMyProductReviewsPageObject productReviewsPage;
	WebDriver driver;
	private String firstName, lastName, email, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		homePage.zoomOut(driver);
		firstName = "Kane";
		lastName = "Pham";
		email = fakeEmail();
		password = "123456";
	}

	private String fakeEmail() {
		return "AutoTest" + String.valueOf((new Random().nextInt(9999))) + "@gmail.com";
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		registerPage = homePage.clickToResgisterLink();
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorEmptyMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		registerPage = homePage.clickToResgisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@456");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSummaryErrorMessage(), "Wrong email");
	}

	@Test
	public void TC_04_Register_Password_Less_6Characters() {
		registerPage = homePage.clickToResgisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void TC_05_Register_Password_NotMatch_ConfirmPassword() {
		registerPage = homePage.clickToResgisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox("123578");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),
				"The password and confirmation password do not match.");
	}

	@Test
	public void TC_06_Register_Valid() {
		registerPage = homePage.clickToResgisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");
		log.info("Register Success with email" + email + "   Pass: 123456");
		homePage = registerPage.ClickToLogoutLinkAtUserPage(driver);
	}

	@Test
	public void TC_07_Login_Empty_Data() {
		loginPage = homePage.clickToLoginLink();
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void TC_08_Login_Empty_Password() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(email);
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_09_Login_Wrong_Password() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox("111111");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_10_Login_Success() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		log.info("Login Success with email" + email + "   Pass: 123456");
		Assert.assertEquals(homePage.getTopicBlockTitle(), "Welcome to our store");
	}

	@Test
	public void TC_11_Customer_Info() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}

	@Test
	public void TC_12_Switch_Page() {
		addressPage = customerInfoPage.openAddressPage(driver);
		log.info("Switch to Addresses Page");
		Assert.assertTrue(addressPage.isAddressesPageDisplayed());

		ordersPage = addressPage.openOrdersPage(driver);
		log.info("Switch to Orders Page");
		Assert.assertTrue(ordersPage.isOrdersPageDisplayed());

		downloadPage = ordersPage.openDownloadableProductsPage(driver);
		log.info("Switch to Downloadable Products Page");
		Assert.assertTrue(downloadPage.isDownloadableProductsPageDisplayed());

		subsciptionsPage = downloadPage.openBackInStockSubsciptionsPage(driver);
		log.info("Switch to Back In Stock Subscriptions Page");
		Assert.assertTrue(subsciptionsPage.isBackInStockSubscriptionsPageDisplayed());

		rewardPointsPage = subsciptionsPage.openRewardPointsPage(driver);
		log.info("Switch to Reward Points Page");
		Assert.assertTrue(rewardPointsPage.isRewardPointsPageDisplayed());

		changePasswordPage = rewardPointsPage.openChangePasswordPage(driver);
		log.info("Switch to Change Password Page");
		Assert.assertTrue(changePasswordPage.isChangePasswordPageDisplayed());

		productReviewsPage = changePasswordPage.openMyProductReviewsPage(driver);
		log.info("Switch to My Product Reviews Page");
		Assert.assertTrue(productReviewsPage.isMyProductReviewsPageDisplayed());

		customerInfoPage = productReviewsPage.openCustomerInfoPage(driver);
		log.info("Switch to Customer Info Page");
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
