package com.arc.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.DataProvider;

import com.arc.base.TestBase;

public class TestUtils extends TestBase{
	
	public static String scereenshotname;
	public static void captureScreenshot() throws IOException, WebDriverException {
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d=new Date();
		scereenshotname=d.toString().replaceAll(" ","_").replaceAll(":", "_")+".jpg";
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+scereenshotname));
	}
	
	
	@DataProvider(name = "dp")
	public Object[][] getData(Method m)
	{
		String sheetname=m.getName();
		
		int cols=excel.getColumnCount(sheetname);
		int rows=excel.getRowCount(sheetname);
		
		Hashtable<String, String> table=null;
		
		Object[][] data=new Object[rows-1][1];
		
		for(int i=2;i<=rows;i++)
		{
			table=new Hashtable<String, String>();
			for(int j=0;j<cols;j++)
			{
				table.put(excel.getCellData(sheetname, j, 1), excel.getCellData(sheetname, j, i));
				data[i-2][0]=table;
			}
		}
		
		return  data;		
	}
	
	
	public static boolean isTestRunnable(String tcname, ExcelReader excel)
	{
		String sheetname="test_suite";
		int rows=excel.getRowCount(sheetname);
		
		for(int rownum=2;rownum<=rows;rownum++)
		{
			String testcase=excel.getCellData(sheetname, "tcid", rownum);
			if(testcase.equalsIgnoreCase(tcname))
			{
				String runmode=excel.getCellData(sheetname, "runmode", rownum);
				if(runmode.equalsIgnoreCase("Y"))
					return true;
				else return false;
			}
		}
		return false;
		
	}
	

}
