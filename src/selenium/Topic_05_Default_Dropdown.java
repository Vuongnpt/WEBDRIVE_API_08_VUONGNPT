package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_05_Default_Dropdown {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		WebElement jobRoleDropdown = driver.findElement(By.xpath("//select[@id='job1']"));
		
		Select jobRole = new Select(jobRoleDropdown);
		System.out.println("Dopdown status = " + jobRole.isMultiple());
		Assert.assertFalse(jobRole.isMultiple());
				
	
		/*<select id="job1" name="user_job1">
		<option value="automation">Automation Tester</option>
		<option value="manual">Manual Tester</option>
		<option value="website">Website Tester</option>
		<option value="mobile">Mobile Tester</option>
		<option value="disabled" disabled="disabled">Dropdown disable</option>
		</select>*/
		
		// Step 03 - selectVisibleText
		jobRole.selectByVisibleText("Automation Tester");
		Thread.sleep(3000);
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Automation Tester");
		
		// Step 05 - selectValue
		jobRole.selectByValue("manual");
		Thread.sleep(3000);
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Manual Tester");
		
		// Step 07 - selectIndex
		jobRole.selectByIndex(3);
		Thread.sleep(3000);
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Mobile Tester");
		
		// Step 09 - Verify 5 elements
		Assert.assertEquals(jobRole.getOptions().size(), 5);
		System.out.println(jobRole.getOptions().size());
		
		
	}
	
  	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
