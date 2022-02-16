package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.AddressPageObject;
import pageObjects.BackInStockSubscriptionsPageObject;
import pageObjects.ChangePasswordPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.DownloadableProductsPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyProductReviewsPageObject;
import pageObjects.OrdersPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageObjects.RewardPointsPageObject;

public class User_05_Switch_Page extends BaseTest {

	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private AddressPageObject addressPage;
	private OrdersPageObject ordersPage;
	private DownloadableProductsPageObject downloadPage;
	private BackInStockSubscriptionsPageObject subsciptionsPage;
	private RewardPointsPageObject rewardPointsPage;
	private ChangePasswordPageObject changePasswordPage;
	private MyProductReviewsPageObject productReviewsPage;
	WebDriver driver;
	private String firstName, lastName, email, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

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
		registerPage = homePage.clickToResgisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");

		homePage = registerPage.clickToLogOutLink();
	}
	
	@Test
	public void TC_02_Login() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		homePage = PageGeneratorManager.getHomePage(driver);
		Assert.assertEquals(homePage.getTopicBlockTitle(), "Welcome to our store");
	}
	
	@Test
	public void TC_03_Customer_Info() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}
	
	@Test
	public void TC_04_Switch_Page() {
		addressPage = customerInfoPage.openAddressPage(driver);
		Assert.assertTrue(addressPage.isAddressesPageDisplayed());
		
		ordersPage = addressPage.openOrdersPage(driver);
		Assert.assertTrue(ordersPage.isOrdersPageDisplayed());
		
		downloadPage = ordersPage.openDownloadableProductsPage(driver);
		Assert.assertTrue(downloadPage.isDownloadableProductsPageDisplayed());
		
		subsciptionsPage = downloadPage.openBackInStockSubsciptionsPage(driver);
		Assert.assertTrue(subsciptionsPage.isBackInStockSubscriptionsPageDisplayed());
		
		rewardPointsPage = subsciptionsPage.openRewardPointsPage(driver);
		Assert.assertTrue(rewardPointsPage.isRewardPointsPageDisplayed());
		
		changePasswordPage = rewardPointsPage.openChangePasswordPage(driver);
		Assert.assertTrue(changePasswordPage.isChangePasswordPageDisplayed());
		
		productReviewsPage = changePasswordPage.openMyProductReviewsPage(driver);
		Assert.assertTrue(productReviewsPage.isMyProductReviewsPageDisplayed());
		
		customerInfoPage = productReviewsPage.openCustomerInfoPage(driver);
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
