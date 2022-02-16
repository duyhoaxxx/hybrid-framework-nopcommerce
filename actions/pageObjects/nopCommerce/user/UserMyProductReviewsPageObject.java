package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.MyProductReviewsPageUI;

public class UserMyProductReviewsPageObject extends BasePage {
	private WebDriver driver;

	public UserMyProductReviewsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isMyProductReviewsPageDisplayed() {
		return isElementDisplay(driver, MyProductReviewsPageUI.MY_PRODUCT_REVIEWS_HEADER);
	}
}
