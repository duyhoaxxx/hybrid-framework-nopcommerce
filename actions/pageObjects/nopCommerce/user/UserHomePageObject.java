package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.HomePageUI;

public class UserHomePageObject extends BasePage {

	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject clickToResgisterLink() {
		clickToElement(driver, HomePageUI.REGISTER_CLICK);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	public UserLoginPageObject clickToLoginLink() {
		clickToElement(driver, HomePageUI.LOGIN_CLICK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	public String getTopicBlockTitle() {
		return getElementText(driver, HomePageUI.TOPIC_BLOCK_TITLE);
	}

	public UserCustomerInfoPageObject clickToMyAccountLink() {
		clickToElement(driver, HomePageUI.MY_ACCOUNT_CLICK);
		return PageGeneratorManager.getUserMyAccountPage(driver);
	}

}
