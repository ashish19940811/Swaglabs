package com.page;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class LoginPage {

	@FindBy(id = "user-name")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "login-button")
	WebElement loginbutton;

	@FindBy(xpath = "//*[@id=\"logoutTooltip\"]")
	WebElement logoutlink;

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LoginPage setlogin1(ExtentTest test) throws InterruptedException {

		try {

			username.sendKeys("standard_user");
			Thread.sleep(2000);

			password.sendKeys("secret_sauce");
			Thread.sleep(5000);

			// test.log(Status.INFO, "Entered username and Password");

			loginbutton.click();
			Thread.sleep(5000);
			// test.log(Status.INFO, "Clicked login Button");
			
			if(driver.switchTo().alert() != null)
			{
			    Alert alert = driver.switchTo().alert();
			    String alertText = alert.getText();
			    alert.accept(); // alert.accept();

			}
			
			Thread.sleep(5000);

			try {

				// Check for success message
				WebElement successMessage = driver.findElement(By.xpath("(//*[contains(text(),'Swag Labs')])[2]"));
				Thread.sleep(5000);

				// Check user name is available or not
				// WebElement usernamereq =
				// driver.findElement(By.xpath("//*[contains(text(),'User name is
				// required')]"));
				Thread.sleep(5000);

				// Check password is available or not
				// WebElement passwordreq =
				// driver.findElement(By.xpath("//*[contains(text(),'Password is required')]"));
				Thread.sleep(5000);

				if (successMessage.isDisplayed()) {
					test.log(Status.PASS, "Login was successful. Message: " + successMessage.getText());

				}

				/*
				 * else if (usernamereq.isDisplayed()) {
				 * 
				 * test.log(Status.FAIL, "Username is Required: " + usernamereq.getText());
				 * 
				 * }
				 */

				/*
				 * else if (passwordreq.isDisplayed()) { test.log(Status.FAIL,
				 * "Password is required:" + passwordreq.getText());
				 * 
				 * }
				 */
				else {
					test.log(Status.FAIL, "An error occurred during the login process:");
				}

			} catch (Exception e) {
				test.log(Status.FAIL, "An error occurred during the login process:" + e.getMessage());

			}

		} catch (Exception e) {

			test.log(Status.FAIL, "An error occurred during the login process:" + e.getMessage());

		}
		
	   
      
		return new LoginPage(driver);

	}

	public void clickonlogout(ExtentTest test) {

		logoutlink.click();
		test.log(Status.PASS, "Logout Successfully");

	}

}
