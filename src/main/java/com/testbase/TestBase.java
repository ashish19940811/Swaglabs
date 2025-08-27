package com.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.util.ScreenRecorderUtil;
import com.util.WebDriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class TestBase {

	public static WebDriver driver;
	private final static String SCREENSHOT_LOCATION = "C:\\Users\\Admin\\eclipse-workspace\\Failure Screenshoot\\eclipse-workspacetest-image.png";
	public static WebDriverWait wait;
	public static ExtentTest test;
	static com.aventstack.extentreports.ExtentTest logger;
	public static Properties properties;

	// Environment value fetched from POM with 'careersIn' and 'production' being
	// the valid values
	public static final String ENV = System.getProperty("env", "Production");

	// BROWSER value fetched from POM with Chrome being the default value
	// private static final String BROWSER = System.getProperty("browser",
	// "Chrome");
	private static final String BROWSER = System.getProperty("browser", "opera");

	@BeforeMethod
	public void setup() throws Exception {

		ScreenRecorderUtil.startRecording("Testing The Project");

		driver = WebDriverFactory.getDriver("chrome"); // or specify browser type

		// driver = WebDriverFactory.getDriver("opera");
		// driver = WebDriverFactory.getDriver("firefox");
		// driver = WebDriverFactory.getDriver("edge");

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		Thread.sleep(5000);

		driver.get("https://www.saucedemo.com/");
	}

	@AfterMethod
	public static void captureScreenshot(ITestResult result) throws IOException, InterruptedException {
		// Check if the test failed
		if (result.getStatus() == ITestResult.FAILURE) {
			// Capture the screenshot
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File screenshotLocation = new File(SCREENSHOT_LOCATION);
			FileUtils.copyFile(screenshot, screenshotLocation);

			// Optionally, add delay if necessary
			Thread.sleep(2000);

			// Read the screenshot file
			InputStream is = new FileInputStream(screenshotLocation);
			byte[] imageBytes = IOUtils.toByteArray(is);

			// Convert the screenshot to Base64
			String base64 = Base64.getEncoder().encodeToString(imageBytes);

			// Log the failure and attach the screenshot in ExtentReports
			test.log(Status.FAIL, "Test Failed, Snapshot below: "
					+ logger.addScreenCaptureFromPath(screenshotLocation.getAbsolutePath()));
		} else {
			// Optionally log passed/skipped status in the report
			test.log(Status.PASS, "Test Passed, no screenshot taken.");
		}
	}

	@AfterSuite
	public void teardown() throws Exception {

		ScreenRecorderUtil.stopRecording();

		driver.quit();

	}

}
