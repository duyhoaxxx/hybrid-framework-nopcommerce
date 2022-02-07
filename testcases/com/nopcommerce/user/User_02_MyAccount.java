package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class User_02_MyAccount {

	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private String firstName, lastName, email, password, invalidEmail, notFoundEmail;
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageObject(driver);
		homePage.zoomOut(driver);
		// https://docs.google.com/document/d/16N5CVHwX4tVhtgvsKAggNCN6COeZSz2Onlfv8wDFo8E/edit

		firstName = "Kane";
		lastName = "Pham";
		email = fakeEmail();
		password = "123456";
		invalidEmail = "123@.com";
		notFoundEmail = "abc123@gmail.com";

	}

	@Test
	public void Register_01_Empty_Data() {
		homePage.clickToResgisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorEmptyMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		homePage.clickToResgisterLink();
		registerPage = new RegisterPageObject(driver);

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@456");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSummaryErrorMessage(), "Wrong email");
	}

	@Test
	public void Register_03_Valid() {
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
	public void Register_04_Email_Exist() {
		homePage.clickToResgisterLink();
		registerPage = new RegisterPageObject(driver);

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("kanepham@gmail.com");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSummaryErrorMessage(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_6Characters() {
		homePage.clickToResgisterLink();
		registerPage = new RegisterPageObject(driver);

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
	public void Register_06_Password_NotMatch_ConfirmPassword() {
		homePage.clickToResgisterLink();
		registerPage = new RegisterPageObject(driver);

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

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_04_Empty_Password() {

	}

	@Test
	public void Login_05_Wrong_Password() {

	}

	@Test
	public void Login_06_Success() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private String fakeEmail() {
		return "AutoTest" + String.valueOf((new Random().nextInt(9999))) + "@gmail.com";
	}
}
