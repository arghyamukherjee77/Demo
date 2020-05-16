package com.arc.listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.arc.base.TestBase;
import com.arc.util.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test= rep.startTest(result.getName().toUpperCase());
		if(!(TestUtils.isTestRunnable(result.getName(), excel)))
		{
			throw new SkipException("Skipping the Test Case: "+result.getName().toUpperCase()+" as Runmode is set to NO");
		}
		
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output", "fasle");
		Reporter.log(result.getName()+" testcase successfully executed");
		try {
			TestUtils.captureScreenshot();
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Reporter.log("<a target=\"_blank\" href="+TestUtils.scereenshotname+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtils.scereenshotname+"><img src="+TestUtils.scereenshotname+" height=200 width=200></img></a>");
		test.log(LogStatus.PASS, result.getName().toUpperCase()+" Testcase is executed and it is PASEED");
		test.log(LogStatus.PASS, test.addScreenCapture(TestUtils.scereenshotname));
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output", "fasle");
		Reporter.log(result.getName()+" Test Case failed");
		try {
			TestUtils.captureScreenshot();
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("<a target=\"_blank\" href="+TestUtils.scereenshotname+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtils.scereenshotname+"><img src="+TestUtils.scereenshotname+" height=200 width=200></img></a>");
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+" Testcase is executed and it is FAILED" +result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.scereenshotname));
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output", "fasle");
		Reporter.log(result.getName()+" testcase Skipped");
		try {
			TestUtils.captureScreenshot();
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("<a target=\"_blank\" href="+TestUtils.scereenshotname+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtils.scereenshotname+"><img src="+TestUtils.scereenshotname+" height=200 width=200></img></a>");
		test.log(LogStatus.SKIP, result.getName().toUpperCase()+" Testcase is executed and it is SKIPPED");
		test.log(LogStatus.SKIP, test.addScreenCapture(TestUtils.scereenshotname));
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
