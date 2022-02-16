package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.RewardPointsPageUI;

public class UserRewardPointsPageObject extends BasePage {
	private WebDriver driver;

	public UserRewardPointsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isRewardPointsPageDisplayed() {
		return isElementDisplay(driver, RewardPointsPageUI.REWARD_POINTS_HEADER);
	}
}
