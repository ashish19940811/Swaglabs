package com.test;

import org.openqa.selenium.support.PageFactoryFinder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extentreport.ExtentReportManager;
import com.page.LoginPage;
import com.testbase.TestBase;

public class LoginPageTest extends TestBase {

	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;

	LoginPage loginPage;
	// HomePage homePage;

	// Log4j configuration
	// private static final Logger log = LogManager.getLogger(LoginTest.class);

	@BeforeTest
	public void initialize() {
		extent = ExtentReportManager.getReport();
		test = extent.createTest("Login Test Case");

	}

	// WebDriver driver;
	public LoginPageTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void Logintest() throws InterruptedException {

		// test.log(Status.INFO, " Verifying successful login");

		Thread.sleep(5000);
		LoginPage loginPage = new LoginPage(driver);

		Thread.sleep(10000);
		loginPage.setlogin1(test);

		// loginPage.clickonlogout(test);

	}

	@AfterTest
	public void finish() {
		extent.flush();

	}

}
