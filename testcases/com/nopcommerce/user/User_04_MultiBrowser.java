package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class User_04_MultiBrowser extends BaseTest {

	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	WebDriver driver;
	private String firstName, lastName, email, password, invalidEmail, notFoundEmail;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = new HomePageObject(driver);
		// https://docs.google.com/document/d/16N5CVHwX4tVhtgvsKAggNCN6COeZSz2Onlfv8wDFo8E/edit

		firstName = "Kane";
		lastName = "Pham";
		email = fakeEmail();
		password = "123456";
		invalidEmail = "123@.com";
		notFoundEmail = "abc123@gmail.com";
		CreatNewAccount();
	}

	private String fakeEmail() {
		return "AutoTest" + String.valueOf((new Random().nextInt(9999))) + "@gmail.com";
	}
	
	private void CreatNewAccount() {
		homePage.clickToResgisterLink();
		registerPage = new RegisterPageObject(driver);

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");

		registerPage.clickToLogOutLink();
		homePage = new HomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_not_Register() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		loginPage.inputToEmailTextbox(notFoundEmail);
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Empty_Password() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		loginPage.inputToEmailTextbox(email);
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Wrong_Password() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox("111111");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Success() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		loginPage.inputToEmailTextbox("aa@gmail.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		homePage = new HomePageObject(driver);
		Assert.assertEquals(homePage.getTopicBlockTitle(), "Welcome to our store");
		homePage.clickToLogOutLink();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
