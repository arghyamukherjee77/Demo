package com.arc.testcase;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.arc.base.TestBase;
import com.arc.util.TestUtils;

public class LoginwithValidCredentials extends TestBase{
	
	@Test(dataProviderClass = TestUtils.class,dataProvider = "dp")
	public void loginwithValidCredentials(Hashtable<String, String> data)
	{
		type("username_xpath",data.get("username"));
		type("password_xpath",data.get("password"));
		click("signinBtn_xpath");
		log.debug("Login Test Case Executed");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("campusmenu_xpath"))));
		Assert.assertTrue(isElementPresent(By.xpath(or.getProperty("campusmenu_xpath"))),"Login not Successfull");
		
	}
	
	
}
