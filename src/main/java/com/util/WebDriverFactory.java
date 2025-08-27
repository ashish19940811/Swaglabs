package com.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;

public class WebDriverFactory {

	public static WebDriver getDriver(String browser) {
		WebDriver driver;

		switch (browser.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Admin\\eclipse-workspace\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "opera":
			System.setProperty("webdriver.opera.driver",
					"C:\\Users\\HP\\eclipse-workspace\\Drivers\\operadriver_win64\\operadrive.exe");
			
			driver = new OperaDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\HP\\eclipse-workspace\\geckodriver-v0.33.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\HP\\eclipse-workspace\\Drivers\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		default:
			throw new IllegalArgumentException("Invalid browser type");
		}

		return driver;
	}
}
