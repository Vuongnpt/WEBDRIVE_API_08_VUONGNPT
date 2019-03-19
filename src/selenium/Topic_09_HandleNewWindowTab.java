package selenium;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_HandleNewWindowTab {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		// Get ra cửa sổ/tab đang active (single)
		String parentWindowID = driver.getWindowHandle();
		System.out.println(parentWindowID);
	
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		
		switchToChildWindow(parentWindowID);
		
		Assert.assertEquals(driver.getTitle(), "Google");
		
		closeAllWithoutParentWindows(parentWindowID);
		
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

	}
	
	@Test
	public void TC_02() throws Exception {
		driver.get("https://www.hdfcbank.com/");
		
		String parentID = driver.getWindowHandle();
		
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
		
		// Click to Agri
		driver.findElement(By.xpath("//div[@class='sectionnav']//a[text()='Agri']")).click();
		
		// Switch to Agri window
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		
		// Click to Account details
		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		
		// Switch to Account details window
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		
		// Switch to frame footer
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
		
		// Click to Privacy Policy
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		
		// Switch to Policy page
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		
		// Click to CSR link
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		Assert.assertEquals(driver.getTitle(), "HDFC BANK - CSR - Homepage");
		
		// Close all windows except parent
		closeAllWithoutParentWindows(parentID);
		
	}
	
	@Test
	public void TC_03() throws Exception {
		driver.get("http://live.guru99.com/index.php/");
		
		String parentID = driver.getWindowHandle();
		
		// Click to Mobile link
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		// Add Xperia to compare
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		
		// Add Galaxy to compare
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		
		// Click to Compare
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		// Switch to Compare page
		switchToChildWindow(parentID);
		
		// Verify title
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		// Close tab and back to parent
		closeAllWithoutParentWindows(parentID);
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	// Handle only 2 windows
		  public void switchToChildWindow(String parentID) throws Exception {
	         // Get all windows/tabs currently
			  Set<String> allWindows = driver.getWindowHandles();
			  Thread.sleep(3000);
			
			  // Duyệt qua từng id của tất cả cửa sổ/tab
			  for (String childWindow : allWindows) {
				  System.out.println(childWindow);
				  		// Kiểm tra id nào khác parent thì switch sang id 
	                      if (!childWindow.equals(parentID)) {
	                                  driver.switchTo().window(childWindow);
	                                  break;
	                      }
	          }
	}
		  // Handle more than 2 windows
		   public void switchToWindowByTitle(String titleExpected) {
			// Get all windows/tabs currently
	           Set<String> allWindows = driver.getWindowHandles();
	           
	        // Dùng vòng lặp duyệt qua từng ID 
	           for (String runWindows : allWindows) {
	        	   
	        	   			// Switch sang từng window  
	                       driver.switchTo().window(runWindows);
	                       
	                       // Get title của window đó 
	                       String currentWindowTitle = driver.getTitle();
	                       if (currentWindowTitle.equals(titleExpected)) {
	                                   break;
	                       }
	           }
	}
		   // Close all windows except parent one
		   public boolean closeAllWithoutParentWindows(String parentWindow) throws Exception {
			// Get all windows/tabs currently
	           Set<String> allWindows = driver.getWindowHandles();
	           
	           // Dùng vòng lặp duyệt qua từng ID 
	           for (String childWindow : allWindows) {
	        	   
	        	   			// Kiểm tra id nào khác parent
	                       if (!childWindow.equals(parentWindow)) {
	                    	   			// Switch sang id 
	                                   driver.switchTo().window(childWindow);
	                                   
	                                   // Close nó 
	                                   driver.close();
	                                   
	                                   Thread.sleep(3000);
	                                   
	                       }
	           }
	           
	           // Quay về parent ID
	           driver.switchTo().window(parentWindow);

				// Kiểm tra chỉ còn duy nhất parent 
	           if (driver.getWindowHandles().size() == 1)
	                      return true;
	           else
	                      return false;
	}
		   

	}


