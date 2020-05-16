package com.arc.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.arc.util.ExcelReader;
import com.arc.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	
	public static WebDriver driver=null;
	public static Properties or=new Properties();
	public static Properties config=new Properties();
	public static FileInputStream fis;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\TestData.xlsx");
	public static WebDriverWait wait;
	public static ExtentReports rep=ExtentManager.getInstance();
	public static ExtentTest test;

	
	
	@BeforeSuite
	public void setUp()
	{
		if(driver==null)
		{
			try {
				fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\or.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				or.load(fis);
				log.debug("OR file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config File loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(config.getProperty("browser").equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				driver=new ChromeDriver();
			}
			
			else if(config.getProperty("browser").equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
				driver=new FirefoxDriver();
			}
			
			driver.get(config.getProperty("testurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			wait=new WebDriverWait(driver, Duration.ofSeconds(500));
		}
			
	}
	
	public static boolean isElementPresent(By by)
	{
		try
		{
			    driver.findElement(by);
				return true;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static void click(String locator)
	{
		if(locator.endsWith("xpath"))
		{
			driver.findElement(By.xpath(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "Clicking on: "+locator);
		}
		else if(locator.endsWith("css"))
		{
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "Clicking on: "+locator);
		}
		else if(locator.endsWith("id"))
		{
			driver.findElement(By.id(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "Clicking on: "+locator);
		}
		else {
			test.log(LogStatus.INFO, "No Locator Found");
		}
	}
	
	public static void type(String locator, String value)
	{
		if(locator.endsWith("xpath"))
		{
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
			test.log(LogStatus.INFO, "Typing value:"+value+" on: "+locator);
		}
		else if(locator.endsWith("css"))
		{
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
			test.log(LogStatus.INFO, "Typing value:"+value+" on: "+locator);
		}
		else if(locator.endsWith("id"))
		{
			driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
			test.log(LogStatus.INFO, "Typing value:"+value+" on: "+locator);
		}
		else {
			test.log(LogStatus.INFO, "No Locator Found to be typed");
		}
		
	}
	
	@AfterSuite
	public void tearDown()
	{
		if(driver!=null)
		{
			driver.close();
		}
		
	}
	

}
