package test;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.org.testBase.TestBase;

import generic.FWUtil;

public class LogingToApplication extends TestBase {
	@BeforeSuite
	public void set_Proprty()
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	
	@Test
	public void LoginMy() throws InterruptedException, AWTException{
 

	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	
	//driver.get("https://ecd3w01a.edc.linde.grp/shop/en/uk/home");
	SoftAssert s = new SoftAssert();
	driver.findElement(By.xpath("//a[@id='departmentLink_3074457345616693268']")).click();
	
}
}
