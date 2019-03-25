package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_iFrame {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01() {
		// Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
		driver.get("https://www.hdfcbank.com/");
		
		// Step 02 - Close popup nếu có hiển thị (switch qua iframe nếu có)  - F5 (refresh page) nhiều lần thì sẽ xuất hiện popup
		List <WebElement> notificationIframes = driver.findElements(By.xpath("//div[@id='vizury-notification-template']/iframe"));
		System.out.println("Notification size = " + notificationIframes.size());
		if(notificationIframes.size() > 0) {
			Assert.assertTrue(notificationIframes.get(0).isDisplayed());
			System.out.println("Pass Display");
			driver.switchTo().frame(notificationIframes.get(0));
			System.out.println("Pass Switch Frame 1");
			driver.findElement(By.xpath("//div[@id='div-close']")).click();
			System.out.println("Pass Close icon");
			driver.switchTo().defaultContent();
			
		}
		
		// Step 03 - Verify đoạn text được hiển thị:  What are you looking for? (switch qua iframe nếu có)
		WebElement  lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingForIframe);
		System.out.println("Pass Switch Frame 2");
		WebElement  lookingForText = driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']"));
		Assert.assertTrue(lookingForText.isDisplayed());
		driver.switchTo().defaultContent();
		
		// Step 04 - Verify banner có đúng 6 images (switch qua iframe nếu có)
		WebElement  slidingBannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(slidingBannerIframe);
		System.out.println("Pass Switch Frame 3");
		
		List <WebElement> slidingBannerImage = driver.findElements(By.xpath("//div[@id='bannercontainer']//div[@class='item-list']//img"));
		System.out.println("Sliding Banner Image = " + slidingBannerImage.size());
		Assert.assertEquals(slidingBannerImage.size(), 6);
		driver.switchTo().defaultContent();
	
		// Step 05 - Verify flipper banner được hiển thị và có 8 items
		List <WebElement> flipperBannerImage = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		System.out.println("Flipper Banner Image = " + flipperBannerImage.size());
		Assert.assertEquals(flipperBannerImage.size(), 8);
		
		// Check 8 images display
		for (int i = 0; i< flipperBannerImage.size(); i++) {
			System.out.println("Check image thu - " + i);
			Assert.assertTrue(flipperBannerImage.get(i).isDisplayed());
		}
		
		// for each
		for (WebElement bannerImage : flipperBannerImage) {
			Assert.assertTrue(bannerImage.isDisplayed());
		}

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	
}
	
	
	
	