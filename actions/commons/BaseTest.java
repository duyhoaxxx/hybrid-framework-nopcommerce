package commons;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	private enum BrowserList {
		FIREFOX, H_FIREFOX, CHROME, H_CHROME, EDGE, OPERA, COCCOC, PHANTOM
	};

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		if (browserList == BrowserList.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();

			// Add Extension
			//
			// FirefoxProfile profile = new FirefoxProfile();
			// File translate = new File(GlobalConstants.PROJECT_PATH + File.separator + "browserExtensions" + File.separator + "ticktick_todo_task_list_reminder.xpi");
			// profile.addExtension(translate);
			// FirefoxOptions options = new FirefoxOptions();
			// options.setProfile(profile);
			// driver = new FirefoxDriver(options);

			// Add Browser Log in Console to File
			//
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
					GlobalConstants.PROJECT_PATH + File.separator + "browserLogs" + File.separator + "Firefox.log");

			driver = new FirefoxDriver();

		} else if (browserList == BrowserList.H_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");

			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
					GlobalConstants.PROJECT_PATH + File.separator + "browserLogs" + File.separator + "Firefox.log");

			driver = new FirefoxDriver(options);
		} else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();

			// Add Extension
			//
			// File file = new File(GlobalConstants.PROJECT_PATH + File.separator + "browserExtensions" + File.separator + "toDoListExtension.crx");
			// ChromeOptions options = new ChromeOptions();
			// options.addExtensions(file);
			// driver = new ChromeDriver(options);

			// Add Browser Log in Console to File
			//
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");

			driver = new ChromeDriver();

		} else if (browserList == BrowserList.H_CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if (browserList == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserList == BrowserList.OPERA) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else if (browserList == BrowserList.COCCOC) {
			WebDriverManager.chromedriver().driverVersion("93.0.4577.63").setup();
			ChromeOptions options = new ChromeOptions();
			if (GlobalConstants.OS_NAME.startsWith("Windows")) {
				options.setBinary("C:\\Program File (x86)\\CocCoc\\Browser\\Application\\browser.exe");
				driver = new ChromeDriver(options);
			} else {
				options.setBinary("Mac OS...");
			}

		} else
			throw new RuntimeException("Browser is not support.");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		if (browserList == BrowserList.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserList == BrowserList.H_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserList == BrowserList.H_CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if (browserList == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserList == BrowserList.OPERA) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else if (browserList == BrowserList.COCCOC) {
			WebDriverManager.chromedriver().driverVersion("93.0.4577.63").setup();
			ChromeOptions options = new ChromeOptions();
			if (GlobalConstants.OS_NAME.startsWith("Windows")) {
				options.setBinary("C:\\Program File (x86)\\CocCoc\\Browser\\Application\\browser.exe");
				driver = new ChromeDriver(options);
			} else {
				options.setBinary("Mac OS...");
			}

		} else
			throw new RuntimeException("Browser is not support.");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(appUrl);
		return driver;
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	@BeforeTest
	public void deleteAllFilesInReportNGScreenshot() {
		log.info("delete file in folder Report Image");
		try {
			String pathFolder = GlobalConstants.PROJECT_PATH + File.separator + "ReportNGImage";
			File file = new File(pathFolder);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}

		log.info("delete file in folder Allure-json");
		try {
			String pathFolder = GlobalConstants.PROJECT_PATH + File.separator + "allure-json";
			File file = new File(pathFolder);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	protected void cleanBrowserAndDriver() {
		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.quit();
		}

		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + osName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected void showBrowserConsoleLogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				log.info("~~~~~~~~~~~~~" + logging.getLevel().toString() + "~~~~~~~~~~~~~ \n" + logging.getMessage());
			}
		}
	}
}
