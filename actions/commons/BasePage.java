package commons;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private int timeOutInSecound = 30;

	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
		;
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
		;
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSecound);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String key) {
		waitForAlertPresence(driver).sendKeys(key);
	}

	public void CloseAllWindowWithoutParent(WebDriver driver, String parentID) {
		var allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			if (!tab.equals(parentID)) {
				driver.switchTo().window(tab);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public void SwitchToWindowByID(WebDriver driver, String id) {
		var allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			if (tab.equals(id)) {
				driver.switchTo().window(tab);
				break;
			}
		}
	}

	public void SwitchToWindowByTitle(WebDriver driver, String title) {
		var allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			driver.switchTo().window(tab);
			if (driver.getTitle().equals(title)) {
				break;
			}
		}
	}

	public void SwitchToWindowByUrl(WebDriver driver, String url) {
		var allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			driver.switchTo().window(tab);
			if (driver.getCurrentUrl().equals(url)) {
				break;
			}
		}
	}

	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	private WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}

	private List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}

	public void clickToElement(WebDriver driver, String xpathLocator) {
		waitForElementClickable(driver, xpathLocator);
		getWebElement(driver, xpathLocator).click();
	}

	public void senkeyToElement(WebDriver driver, String xpathLocator, String key) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(key);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByValue(textItem);
	}

	public String getFirstSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	public List<WebElement> getAllSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getAllSelectedOptions();
	}

	public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SelectItemInCustomDropDown(WebDriver driver, String buttonXpath, String loadXpath, String expected) {
		clickToElement(driver, buttonXpath);
		sleepInSecond(2);

		WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSecound);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(loadXpath)));

		var listWebElements = getListWebElement(driver, loadXpath);
		for (WebElement webElement : listWebElements) {
			if (webElement.getText().trim().equals(expected)) {
				if (webElement.isDisplayed()) {
					webElement.click();
				} else {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
					sleepInSecond(1);
					jsExecutor.executeScript("arguments[0].click();", webElement);
				}
				sleepInSecond(1);
				break;
			}
		}
	}

	public void SelectItemInEditDropDown(WebDriver driver, String buttonXpath, String loadXpath, String expected) {
		senkeyToElement(driver, loadXpath, expected);
		sleepInSecond(2);

		WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSecound);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(loadXpath)));

		var ListWebElements = getListWebElement(driver, loadXpath);
		for (WebElement webElement : ListWebElements) {
			if (webElement.getText().trim().equals(expected)) {
				webElement.click();
				sleepInSecond(2);
				break;
			}
		}
	}

	public void SelectMultiDropDown(WebDriver driver, String buttonXpath, String loadXpath, String[] expected) {
		clickToElement(driver, buttonXpath);
		sleepInSecond(2);

		WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSecound);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(loadXpath)));

		var ListWebElements = getListWebElement(driver, loadXpath);
		for (WebElement webElement : ListWebElements) {
			for (String result : expected) {
				if (webElement.getText().trim().equals(result)) {
					webElement.click();
					sleepInSecond(2);
				}
			}
		}
		clickToElement(driver, buttonXpath);
		sleepInSecond(2);
	}

	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		waitForElementVisible(driver, xpathLocator);
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String xpathLocator) {
		waitForElementVisible(driver, xpathLocator);
		return getWebElement(driver, xpathLocator).getText();
	}

	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		waitForElementVisible(driver, xpathLocator);
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String RGBAValue) {
		return Color.fromString(RGBAValue).asHex();
	}

	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	public void checkToDefaultCheckbox(WebDriver driver, String xpathLocator) {
		if (!isElementSelected(driver, xpathLocator))
			getWebElement(driver, xpathLocator).click();
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String xpathLocator) {
		if (isElementSelected(driver, xpathLocator))
			getWebElement(driver, xpathLocator).click();
	}

	public boolean isElementDisplay(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}

	public boolean isElementEnable(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}

	public void switchToFrameIframe(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void hightlightElement(WebDriver driver, String xpathlocator) {
		WebElement element = getWebElement(driver, xpathlocator);
		String originalStyle = element.getAttribute("style");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathlocator));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void scrollToElementOnDown(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathlocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, xpathlocator));
	}

	public String getElementValidationMessage(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, xpathlocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, xpathlocator));
		if (status) {
			return true;
		}
		return false;
	}

	public void waitForElementVisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSecound);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathlocator)));
	}

	public void waitForAllElementVisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSecound);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathlocator)));
	}

	public void waitForElementInvisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSecound);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathlocator)));
	}

	public void waitForAllElementInvisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSecound);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathlocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSecound);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathlocator)));
	}
}
