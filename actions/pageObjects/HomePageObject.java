package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {

	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickToResgisterLink() {
		clickToElement(driver, HomePageUI.REGISTER_CLICK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public LoginPageObject clickToLoginLink() {
		clickToElement(driver, HomePageUI.LOGIN_CLICK);
		return PageGeneratorManager.getLoginPage(driver);
	}

	public String getTopicBlockTitle() {
		return getElementText(driver, HomePageUI.TOPIC_BLOCK_TITLE);
	}

	public HomePageObject clickToLogOutLink() {
		clickToElement(driver, HomePageUI.LOG_OUT_CLICK);
		return PageGeneratorManager.getHomePage(driver);
	}

}
