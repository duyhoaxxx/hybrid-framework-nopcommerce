package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToLoginButton() {
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, LoginPageUI.EMAIL_EMPTY_ERR_MESSAGE);
	}

	public void inputToEmailTextbox(String invalidEmail) {
		inputToEmailTextbox(invalidEmail);
		
	}

}
