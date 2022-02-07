package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {

	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToResgisterLink() {
		clickToElement(driver, HomePageUI.REGISTER_CLICK);
	}

	public void clickToLoginLink() {
		clickToElement(driver, HomePageUI.LOGIN_CLICK);
	}

}
