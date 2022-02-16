package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.LoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserHomePageObject clickToLoginButton() {
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
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
	
	public UserHomePageObject LoginAsUser(String email, String password) {
		inputToEmailTextbox(email);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}

}
