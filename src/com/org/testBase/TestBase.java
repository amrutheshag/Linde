package com.org.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.org.ExcelLibrary.ExcelLibrary;
import com.org.customListners.ListenerinSelenium;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase 
{
	public static final Logger log=Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	public ExcelLibrary xllib;
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	public Properties OR=new Properties();
	 static
	   {
		   Calendar calender=Calendar.getInstance();
		   SimpleDateFormat formater=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		   extent = new ExtentReports(System.getProperty("user.dir")+"/src/com/org/reports/test"+formater.format(calender.getTime())+".html", false);
	   }
	@BeforeClass
	public void launch() throws IOException
	{
		
		String log4jConfigPath="log4j.properties";
	    PropertyConfigurator.configure(log4jConfigPath);
	    loadData();
		xllib = new ExcelLibrary();
		if(OR.getProperty("Browser").equals("chrome"))
		{

			System.setProperty("webdriver.chrome.driver", "./BrowserDriver//chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(OR.getProperty("Browser").equals("firefox"))
		{		
			
			System.setProperty("webdriver.gecko.driver", "./BrowserDriver//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(OR.getProperty("Browser").equals("ie")){
			System.setProperty("webdriver.ie.driver", "./BrowserDriver/IEDriverServer.exe");
			driver= new InternetExplorerDriver();
		}
		driver.get(OR.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);	
		driver.manage().window().maximize();
		
	}
	public void loadData() throws IOException
	{
		File file=new File(System.getProperty("user.dir")+"/src/com/org/config/config.properties");
		FileInputStream f=new FileInputStream(file);
		OR.load(f);
	}
   public void getScreenShot(String name)
   {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try {
			File destFile = new File(System.getProperty("user.dir")+"/src/com/org/screenshot/"+getClass() + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			Reporter.log("<a href='" + destFile+ "'> <img src='" + destFile + "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
   public void getResults(ITestResult result)
   {
	   if(result.getStatus()== ITestResult.SUCCESS)
	   {
		   test.log(LogStatus.PASS, result.getName()+ "Test is pass");
	   }
	   else if(result.getStatus()==ITestResult.SKIP)
	   {
		   test.log(LogStatus.SKIP, result.getName()+ "test is skkiped And Reson is:-"+result.getThrowable());
	   }
	   else if(result.getStatus()==ITestResult.FAILURE)
	   {
		   test.log(LogStatus.ERROR, result.getName()+ "test is failed and Reasone is:-"+result.getThrowable());
		   String screen = captureScreen("");
		   test.log(LogStatus.FAIL, test.addScreenCapture(screen));
	   }
	   else if(result.getStatus()==ITestResult.STARTED)
	   {
		   test.log(LogStatus.INFO, result.getName()+"test is started");
	   }
	   
   }
   public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "Failed";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/com/org/screenshot/";
			destFile = new File((String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			Reporter.log("<a href='" + destFile + "'> <img src='" + destFile + "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}
    @AfterMethod()
	public void afterMethod(ITestResult result) {
		getResults(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " Test Started ");
	}
	
	public String[][] getData(String exelName,String sheetName){
		String excelLocation="./Data/"+exelName;
		ExcelLibrary xllib = new ExcelLibrary();
		String[][] data = xllib.getExcelData(excelLocation, sheetName);
		return data;
		
	}
	
	
	@AfterClass(alwaysRun = true)
	public void quit()
	{
		driver.quit();
		extent.endTest(test);
		extent.flush();
	}
	
}
