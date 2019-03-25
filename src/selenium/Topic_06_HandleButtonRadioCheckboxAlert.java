package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_06_HandleButtonRadioCheckboxAlert {
	WebDriver driver;
	JavascriptExecutor javascript;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01() throws Exception {
		driver.get("http://live.guru99.com/");
		
		WebElement myAccount = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		javascript.executeScript("arguments[0].click();", myAccount );
		
		String myAccountURL = driver.getCurrentUrl();
		Assert.assertEquals(myAccountURL, "http://live.guru99.com/index.php/customer/account/login/");
		
		WebElement createAccountButton = driver.findElement(By.xpath("//span[text()='Create an Account']"));
		javascript.executeScript("arguments[0].click();", createAccountButton );
		
		String createAccountURL = driver.getCurrentUrl();
		Assert.assertEquals(createAccountURL, "http://live.guru99.com/index.php/customer/account/create/");
		
	}
	
	@Test
	public void TC_02() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		// Case 01 - label có thể click nhưng ko kiểm tra isSelected được 
		WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		javascript.executeScript("arguments[0].click();", dualZoneCheckbox );
		Thread.sleep(3000);
		
		// Case 02 - input có thể kiểm tra isSelected được nhưng ko click được 
		Assert.assertTrue(dualZoneCheckbox.isSelected());
		
		if (dualZoneCheckbox.isSelected()) {
			javascript.executeScript("arguments[0].click();", dualZoneCheckbox );
		}
		Thread.sleep(3000);
		
		Assert.assertFalse(dualZoneCheckbox.isSelected());
		
		}
		
		
	@Test
	public void TC_03() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		WebElement PetrolRadio2 = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		javascript.executeScript("arguments[0].click();", PetrolRadio2 );
		Thread.sleep(3000);
		
		if (!PetrolRadio2.isSelected()) {
			javascript.executeScript("arguments[0].click();", PetrolRadio2 );
		}
		Thread.sleep(3000);
	}	
		
	@Test
	public void TC_04() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		Alert jsAlert = driver.switchTo().alert();
		Assert.assertEquals(jsAlert.getText(), "I am a JS Alert");
		jsAlert.accept();
		
		WebElement resultText = driver.findElement(By.xpath("//p[@id='result']"));
		System.out.println(resultText.getText());
		Assert.assertEquals(resultText.getText(), "You clicked an alert successfully");
	
	}	
	
	
	@Test
	public void TC_05() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		Alert jsAlert = driver.switchTo().alert();
		Assert.assertEquals(jsAlert.getText(), "I am a JS Confirm");
		jsAlert.dismiss();
		
		WebElement resultText = driver.findElement(By.xpath("//p[@id='result']"));
		System.out.println(resultText.getText());
		Assert.assertEquals(resultText.getText(), "You clicked: Cancel");
		
	}
		
	@Test
	public void TC_06() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		String promptText = "Nguyen Pham The Vuong";
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		System.out.println("Pass click prompt");
		
		Alert jsAlert = driver.switchTo().alert();
		System.out.println("Pass switch alert");
		Assert.assertEquals(jsAlert.getText(), "I am a JS prompt");
		jsAlert.sendKeys(promptText);
		System.out.println("Pass type text");
		jsAlert.accept();
		System.out.println("Pass OK alert");
		
		WebElement resultText = driver.findElement(By.xpath("//p[@id='result']"));
		System.out.println(resultText.getText());
		Assert.assertEquals(resultText.getText(), "You entered: " + promptText);
		
	}
	
	@Test
	public void TC_07() throws Exception {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials')]")).isDisplayed());
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public void clickElementByJavascript(WebElement element) {
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}

}
