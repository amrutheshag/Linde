package test;

import java.awt.AWTException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import generic.FWUtil;
import generic.IAutoConst;

public class UK_Consumer extends FWUtil implements IAutoConst {
	
	//String un=FWUtil.getXLData(INPUT_PATH, "TestLaunch",1,0);
	//String pw=FWUtil.getXLData(INPUT_PATH, "TestLaunch",1,1);
	//static String date=FWUtil.getXLData(INPUT_PATH, "TestLaunch",1,2);
	//static String month1=FWUtil.getXLData(INPUT_PATH, "TestLaunch",1,3);
	//static String year1=FWUtil.getXLData(INPUT_PATH, "TestLaunch",1,4);
	
	
	
	@BeforeSuite
	public void set_Proprty()
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	
	@Test
	public void TestUK_Credit_Check() throws InterruptedException, AWTException{
 
	String un=FWUtil.getXLData(INPUT_PATH, "TestLaunch",1,0);
	String pw=FWUtil.getXLData(INPUT_PATH, "TestLaunch",1,1);
	String EmailId=FWUtil.getXLData(INPUT_PATH, "TestLaunch",1,8);
	String BankName=FWUtil.getXLData(INPUT_PATH, "TestLaunch",1,5);
	String BankNumber=FWUtil.getXLData(INPUT_PATH, "TestLaunch",1,6);
	String SortCode=FWUtil.getXLData(INPUT_PATH, "TestLaunch",1,7);
	
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	
	driver.get("https://ecd3w01a.edc.linde.grp/shop/en/uk/home");
	
	driver.findElement(By.xpath("//div[@class='close closeClkArea']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("311-WN");
	List<WebElement> elements  = driver.findElements(By.xpath("//b[.='X']"));
	JavascriptExecutor js = (JavascriptExecutor) driver;
    for (WebElement element : elements ) {
        if (element.isDisplayed()) {
        	element.click();
        }
    }
    
	driver.findElement(By.xpath("//*[@id='icon_search']")).click();
	
	WebDriverWait wait = new WebDriverWait(driver, 10);

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='container-fluid-pdp__infobox__button container-fluid-pdp__infobox__button--cart pdp_add_to_cart_button']")));       
	Thread.sleep(5000);
	 WebElement addToCart = driver.findElement(By.xpath("//button[@class='container-fluid-pdp__infobox__button container-fluid-pdp__infobox__button--cart pdp_add_to_cart_button']"));
	 addToCart.click();
	 
	driver.findElement(By.xpath("//a[@class='AddtoCartOverlay_btn_cmn AddtoCartOverlay_btn_gotocrt']")).click();
	Thread.sleep(5000);
	
	driver.findElement(By.xpath("//span[@class='checkoutBtn']")).click();
	Thread.sleep(5000);
	driver.navigate().to("https://ecd3w01a.edc.linde.grp/shop/en/uk/registration");
	Thread.sleep(5000);
	driver.findElement(By.xpath("//div[@class='btn btn-block uk_selectBtn button-color-grey']")).click();
	Thread.sleep(5000);
	
	WebElement Title = driver.findElement(By.xpath("//select[@name='personTitle']"));
	Select s=new Select(Title);
	s.selectByIndex(2);
	

	
	driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Testcredit");;
	driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("ND");
	driver.findElement(By.xpath("//input[@id='email1']")).sendKeys(EmailId);
	
	driver.findElement(By.xpath("//input[@id='phone1']")).sendKeys("01483111111");
	driver.findElement(By.xpath("//input[@id='Username']")).sendKeys(un);
	
	driver.findElement(By.xpath("//input[@id='Password1']")).sendKeys(pw);
	driver.findElement(By.xpath("//input[@id='Password2']")).sendKeys(pw);
	
	driver.findElement(By.xpath("//button[@id='UKConsumer_tabPersonal']")).click();
	
	Thread.sleep(8000);
	WebElement Error = driver.findElement(By.xpath("//span[contains(text(),'User name already exists, please try alternative.')]"));
	if(Error.isDisplayed())
	{
		driver.findElement(By.xpath("//input[@id='Username']")).clear();
		un=un+2;
		driver.findElement(By.xpath("//input[@id='Username']")).sendKeys(un);
		driver.findElement(By.xpath("//button[@id='UKConsumer_tabPersonal']")).click();
		Thread.sleep(8000);
		if(Error.isDisplayed())
		{
			
			driver.findElement(By.xpath("//input[@id='Username']")).clear();
			un=un+3;
			driver.findElement(By.xpath("//input[@id='Username']")).sendKeys(un);
			driver.findElement(By.xpath("//button[@id='UKConsumer_tabPersonal']")).click();
			Thread.sleep(8000);
	}
	}
	
	try {
		//driver.findElement(By.id("acceptPreContractCheckbox")).click();
	} catch (Exception e) {
		driver.findElement(By.xpath("//label[@for='acceptPreContractCheckbox']")).click();
	}
	driver.findElement(By.xpath("//label[@for='acceptPreContractCheckbox']")).click();
	WebElement dob = driver.findElement(By.xpath("//select[@id='dayDOB']"));
	Select s1=new Select(dob);
	s1.selectByIndex(22);
	WebElement Month=driver.findElement(By.id("monthDOB"));
	Select s5=new Select(Month);
	s5.selectByIndex(9);
	WebElement year=driver.findElement(By.id("yearDOB"));
	Select s6=new Select(year);
	s6.selectByIndex(22);
	
	driver.findElement(By.xpath("//label[@for='creditCheck']")).click();
	driver.findElement(By.xpath("//input[@id='SP_PostCode']")).sendKeys("AA11AA");
	
	driver.findElement(By.xpath("//div[contains(@class,'row boc_acc_prsnlonlnacc_outr form setPos')]//*[@class='icon']")).click();
	WebElement address = driver.findElement(By.xpath("//select[@id='crafty_postcode_lookup_result_option1']"));
	Thread.sleep(2000);

	Select s2=new Select(address);
	s2.selectByIndex(2);
	Thread.sleep(4000);

	//driver.findElement(By.id("sameDeliveryContact")).click();
	
	driver.findElement(By.xpath("//button[@id='UKConsumer_tabAddr']")).click();
	//driver.findElement(By.xpath(""))
	Thread.sleep(4000);
	driver.findElement(By.id("onAccount")).click();
	driver.findElement(By.id("oaaccountHolderName")).sendKeys(BankName);
	driver.findElement(By.id("oaaccountNumber")).sendKeys(BankNumber);
	driver.findElement(By.id("oasortCode")).sendKeys(SortCode);
	driver.findElement(By.id("UKConsumer_tabPayment")).click();
	//driver.findElement(By.xpath(""))
	
}
}

