package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RewardPointsPageUI;

public class RewardPointsPageObject extends BasePage {
	private WebDriver driver;

	public RewardPointsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isRewardPointsPageDisplayed() {
		return isElementDisplay(driver, RewardPointsPageUI.REWARD_POINTS_HEADER);
	}
}
