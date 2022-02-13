package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPageObject clickToLoginButton() {
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getLoginPage(driver);
	}

	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, LoginPageUI.EMAIL_EMPTY_ERR_MESSAGE);
	}

	public void inputToEmailTextbox(String invalidEmail) {
		senkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, invalidEmail);
	}

	public String getErrorMessageLoginUnsuccessful() {
		return getElementText(driver, LoginPageUI.LOGIN_ERR_MESSAGE);
	}

	public void inputToPasswordTextbox(String password) {
		senkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

}
