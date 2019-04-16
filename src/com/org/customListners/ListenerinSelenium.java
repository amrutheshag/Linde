package com.org.customListners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.org.testBase.TestBase;



public class ListenerinSelenium extends TestBase implements ITestListener
{
	
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		if(!result.isSuccess())
		{
			 Calendar calendar=Calendar.getInstance();
	    	 SimpleDateFormat formater=new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
	    	 String methodname=result.getName();
	    	 File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	 try {                                                                             
	    	File destFile=new File(System.getProperty("user.dir")+"/src/main/java/com/org/actiTime/failureScreenshot/"+methodname +"_"+formater.format(calendar.getTime())+".png");
	    	FileUtils.copyFile(srcFile, destFile);
	    	Reporter.log("<a href= '" +destFile+"' ><img src='" +destFile +"' height='100' wideth='100'/></a>");
			} catch (Exception e) {
				
			}
		}
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		if(result.isSuccess())
		{
			 Calendar calendar=Calendar.getInstance();
	    	 SimpleDateFormat formater=new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
	    	 String methodname=result.getName();
	    	 File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	 try {                                                                             
	    	 File destFile=new File(System.getProperty("user.dir")+"/src/main/java/com/org/actiTime/successScreenshot/"+methodname+"_"+formater.format(calendar.getTime())+".png");
	    	FileUtils.copyFile(srcFile, destFile);
	    	Reporter.log("<a href= '" +destFile+"' ><img src='" +destFile +"' height='100' wideth='100'/></a>");
			} catch (Exception e) {
				
			}
		}
		
	}

}

