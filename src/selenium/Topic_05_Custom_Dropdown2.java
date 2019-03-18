package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Custom_Dropdown2 {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascript;
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_Jquery() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		Thread.sleep(3000);
		
		selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "10");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='10']")).isDisplayed());		
		Thread.sleep(3000);
		
		selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());		
		Thread.sleep(3000);
		
		selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "3");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='3']")).isDisplayed());		
		Thread.sleep(3000);
	}
  	
	@Test
	public void TC_02_Angular() throws Exception {
		driver.get("https://material.angular.io/components/select/examples");
		
		selectCustomDropdownList("//mat-select[@placeholder='State']", "//mat-option", "Wyoming");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Wyoming']")).isDisplayed());
		Thread.sleep(3000);
		
		selectCustomDropdownList("//mat-select[@placeholder='State']", "//mat-option", "West Virginia");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='West Virginia']")).isDisplayed());
		Thread.sleep(3000);
		
		selectCustomDropdownList("//mat-select[@placeholder='State']", "//mat-option", "California");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='California']")).isDisplayed());
		Thread.sleep(3000);
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void selectCustomDropdownList(String parentXpath, String childXpath, String valueExpected) {
		// Cick to open dropdown list
		WebElement parent = driver.findElement(By.xpath(parentXpath));
		javascript.executeScript("arguments[0].click();", parent);
		
		// Wait for all items
		List <WebElement> child = driver.findElements(By.xpath(childXpath));
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(child));
		
		// Get text all items and verify if = expected value
		for (WebElement childItem: child) {
			if(childItem.getText().equals(valueExpected)) {
				// Scroll to item
				javascript.executeScript("arguments[0].scrollIntoView(true);", childItem);
				
				// Click to item
				childItem.click();
				break;
			}
		}
		
	}
	

}
