package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.ChangePasswordPageUI;

public class ChangePasswordPageObject extends BasePage {
	private WebDriver driver;

	public ChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isChangePasswordPageDisplayed() {
		return isElementDisplay(driver, ChangePasswordPageUI.CHANGE_PASSWORD_HEADER);
	}
}
