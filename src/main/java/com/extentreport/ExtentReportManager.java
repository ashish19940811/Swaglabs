package com.extentreport;

import static org.testng.Assert.assertEquals;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.util.Assert;
import com.testbase.TestBase;

public class ExtentReportManager extends TestBase {
	public ExtentReportManager() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static ExtentTest logger;
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;
	public static WebDriver driver;

	@Test
	public static ExtentReports getReport() {
		if (extent == null)
			extent = new ExtentReports();
		System.out.println(System.getProperty("dir"));
		extent = new ExtentReports();

		// extent.start
		// logger = ((Object) extent).startTest("Skip Test", "This Test is performed
		// only to show how a skip test works");

		String mydirectorypath = "C:\\Users\\Admin\\eclipse-workspace\\ExtentReport";
		LocalDateTime localdatetime = LocalDateTime.now();
		System.out.println("localdatetime is:" + localdatetime);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		System.out.println("formatter is:" + formatter);

		String time = formatter.format(localdatetime);
		System.out.println("time:" + time);

		File dir = new File(mydirectorypath + "Swag-Labs." + "_" + time.replace(':', '-'));
		System.out.println("dir:" + dir);
		dir.mkdir();

		spark = new ExtentSparkReporter(dir + "\\extentreport.html");
		// spark = new ExtentSparkReporter("FailedTest.html");
		spark.config().setDocumentTitle("Swag-Labs Report");

		spark.config().setEncoding("UTF-8");
		spark.config().setReportName("Swag-Labs Report");
		spark.config().setTheme(Theme.DARK);
		extent.attachReporter(spark);

		return extent;

	}

}
