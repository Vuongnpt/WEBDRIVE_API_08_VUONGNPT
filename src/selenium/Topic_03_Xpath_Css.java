package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Css {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	
	/*@Test
	public void TC_01_WithEmailPasswordEmpty() throws Exception {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErrorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		AssertJUnit.assertEquals(emailErrorMessage, "This is a required field.");
		String passwordErrorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		AssertJUnit.assertEquals(passwordErrorMessage, "This is a required field.");
		
		Thread.sleep(3000);
	}
  
	@Test
	public void TC_02_WithEmailInvalid() throws Exception {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailValidateMessage = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		AssertJUnit.assertEquals(emailValidateMessage, "Please enter a valid email address. For example johndoe@domain.com.");
		
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_03_WithPasswordLessThanSixChar() throws Exception {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String passwordValidateMessage = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		AssertJUnit.assertEquals(passwordValidateMessage, "Please enter 6 or more characters without leading or trailing spaces.");
		
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_04_WithEmailPasswordIncorrect() throws Exception {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String invalidLoginMessage = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
		AssertJUnit.assertEquals(invalidLoginMessage, "Invalid login or password.");
		
		Thread.sleep(3000);
	}
	*/
	@Test
	public void TC_05_CreateAnAccount() throws Exception  {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		// Create account
		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Jimmy");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Nguyen Pham");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("automation" + randomNumber() + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();
		driver.findElement(By.xpath("//button[@class='button']")).click();
		
		// Verify register successful
		
		/*String registerSuccess = driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span")).getText();
		AssertJUnit.assertEquals(registerSuccess, "Thank you for registering with Main Website Store.");
		
		Thread.sleep(3000);
		*/
		// Logout
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a/div[@class='skip-link skip-account skip-active']//span[@class='icon']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
		
		Thread.sleep(5000);
		
		// Check Homepage
		
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}
	

}
