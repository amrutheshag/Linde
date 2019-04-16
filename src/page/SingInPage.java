package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class SingInPage {
	@FindBy(id="userName")
	private WebElement unTB;
	
	@FindBy(id="passWord")
	private WebElement pwTB;
	
	@FindBy(xpath="//input[@alt='Sign In']")
	private WebElement signInBTN;
	
	public SingInPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public void setUserName(String un) {
		SoftAssert s = new SoftAssert();
		unTB.sendKeys(un);
		System.out.println("done");
		s.assertAll();
	}
	
	public void setPassword(String pw) {
		SoftAssert s = new SoftAssert();
		pwTB.sendKeys(pw);
	}
	
	public void clickSingIn() {
		SoftAssert s = new SoftAssert();
		signInBTN.click();
	}
}
