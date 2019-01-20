package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_03_Selenium_WebDriverAPI {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By under18Radio = By.xpath("//input[@id='under_18']");
	By educationTextArea = By.xpath("//textarea[@id='edu']");
	By passwordTextbox = By.xpath("//input[@id='password']");
	By biographyTextArea = By.xpath("//textarea[@id='bio']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By developmentCheckbox = By.xpath("//input[@id='development']");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_IsDisplayed() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		if (isElementDisplayed(emailTextbox)) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}
		
		if (isElementDisplayed(under18Radio)) {
			driver.findElement(under18Radio).click();
		}
		
		if (isElementDisplayed(educationTextArea)) {
			driver.findElement(educationTextArea).sendKeys("Automation Testing");
		}
		
	}
	
		
	@Test
	public void TC_02_IsEnabled() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(under18Radio));
		Assert.assertTrue(isElementEnabled(educationTextArea));
		Assert.assertFalse(isElementEnabled(passwordTextbox));
		Assert.assertFalse(isElementEnabled(biographyTextArea));
		Assert.assertFalse(isElementEnabled(jobRole02));
		
	}

	@Test
	public void TC_03_IsSelected() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		if (isElementDisplayed(under18Radio)) {
		
		}
		
		if (isElementDisplayed(developmentCheckbox)) {
		
		}
		
		if (isElementSelected(under18Radio)) {
		}
		driver.findElement(under18Radio).click();
		
		if (isElementSelected(developmentCheckbox)) {
		}
		driver.findElement(developmentCheckbox).click();
		
		Thread.sleep(10000);
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element: " + by + " is displayed");
			return true;
		}
		System.out.println("Element: " + by + " is not displayed");
		return false;
	}
	
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element: " + by + " is enalbled");
			return true;
		}
		System.out.println("Element: " + by + " is disbaled");
		return false;
	}
	
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element: " + by + " is selected");
			return true;
		}
		System.out.println("Element: " + by + " is not selected");
		return false;
	}
}
