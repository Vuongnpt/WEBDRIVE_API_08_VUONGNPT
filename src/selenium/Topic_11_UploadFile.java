package selenium;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_UploadFile {
	WebDriver driver;
	// Get relative path
	String rootFoler = System.getProperty("user.dir");
	
	String fileName01 = "match-tap1.png";
	String fileName02 = "match-tap2.png";
	String fileName03 = "match-tap3.png";
	
	String fileNamePath01 = rootFoler + "/uploadFile/" + fileName01;
	String fileNamePath02 = rootFoler + "/uploadFile/" + fileName02;
	String fileNamePath03 = rootFoler + "/uploadFile/" + fileName03;
	
	String chromePath = rootFoler + "/uploadFile/" + "chrome.exe";
	String firefoxPath = rootFoler + "/uploadFile/" + "firefox.exe";
	
	String[] files = {fileNamePath01, fileNamePath02, fileNamePath03};
	
	String subFolderName = "autotest";
	String emailName = "autotest" + randomNumber() + "@gmail.com";
	String firstName = "The Vuong";
	
	
	@BeforeClass
	public void beforeClass() {
		// Firefox
		System.setProperty("webdriver.gecko.driver", "./lib/geckodriver");
		driver = new FirefoxDriver();
		
		// Chrome
//		System.setProperty("webdriver.chrome.driver", "./lib/chromedriver");
//		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_Sendkey_Upload_Queue() {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		// for each
		for (String file: files) {
			WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
			uploadFile.sendKeys(file);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		driver.findElement(By.xpath("//span[text()='Start upload']")).click();
	
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName03 + "']")).isDisplayed());

	}
		
	@Test
	public void TC_02_Sendkey_Upload_Multiple() throws Exception {
			driver.get("http://blueimp.github.com/jQuery-File-Upload/");
			
			WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
			uploadFile.sendKeys(fileNamePath01 + "\n" + fileNamePath02 + "\n" + fileNamePath03);
			Thread.sleep(1000);
			
			List <WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
			
			for (WebElement startButton: startButtons) {
				startButton.click();
				Thread.sleep(5000);
				
			}
				
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName03 + "']")).isDisplayed());
		
	}
	
	// MAC OS can not use autoIT
//	@Test
//	public void TC_03_AutoIT() throws Exception {
//		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
//		
//		if(driver.toString().contains("chrome")) {
//			WebElement uploadFile =  driver.findElement(By.cssSelector(".fileinput-button"));
//            uploadFile.click();
//            Thread.sleep(1000);
//            Runtime.getRuntime().exec(new  String[] { chromePath, fileNamePath01 });
//		} else if(driver.toString().contains("firefox")) {
//			WebElement uploadFile =  driver.findElement(By.cssSelector(".fileinput-button"));
//            uploadFile.click();
//            Thread.sleep(1000);
//            Runtime.getRuntime().exec(new  String[] { firefoxPath, fileNamePath01 });
//		}
//		
//		
//	}
		
	@Test
	public void TC_04_Robot() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		Thread.sleep(5000);
		
		
		 // Specify the file location with extension
        StringSelection select = new  StringSelection(fileNamePath02);

        // Copy to clipboard
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

        if(driver.toString().contains("chrome")) {
			WebElement uploadFile =  driver.findElement(By.cssSelector(".fileinput-button"));
            uploadFile.click();
            Thread.sleep(1000);
            
		} else if(driver.toString().contains("firefox")) {
			WebElement uploadFile =  driver.findElement(By.cssSelector(".fileinput-button"));
            uploadFile.click();
            Thread.sleep(1000);
            
		}

        Robot robot = new Robot();
        Thread.sleep(1000);

        // Nhan phim Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);

        // Nhan xuong Ctrl - V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        Thread.sleep(1000);

        // Nha Ctrl - V
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(1000);

        // Nhan Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(3000);
		
		
		
	}
		
	
	@Test
	public void TC_05() throws Exception {
		driver.get("https://encodable.com/uploaddemo/");
		
		driver.findElement(By.xpath("//input[@id='uploadname1']")).sendKeys(fileNamePath01);
		
		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(subFolderName);
		
		driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(emailName);
		
		driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(firstName);
		
		clickToElementByJS("//input[@id='uploadbutton']");
		
		Thread.sleep(10000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='uploadDoneContainer']//dd[contains(text(),'" + emailName + "')]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='uploadDoneContainer']//dd[contains(text(),'" + firstName + "')]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='uploadDoneContainer']//a[contains(text(),'" + fileName01 + "')]")).isDisplayed());
		
		driver.findElement(By.xpath("//a[contains(.,'View Uploaded Files')]")).click();
		
		driver.findElement(By.xpath("(//a[contains(.,'autotest')])")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(.,'match-tap1.png')]")).isDisplayed());
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='6px groove red'", element);
    }

    public Object executeForBrowser(String javaSript) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(javaSript);
    }

    public Object clickToElementByJS(String xpathName) {
    		WebElement element = driver.findElement(By.xpath(xpathName));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].click();", element);
    }

    public Object sendkeyToElementByJS(String xpathName, String value) {
    		WebElement element = driver.findElement(By.xpath(xpathName));
           JavascriptExecutor js = (JavascriptExecutor) driver;
           return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public Object removeAttributeInDOM(String xpathName, String attribute) {
    		WebElement element = driver.findElement(By.xpath(xpathName));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
    }

    public Object scrollToBottomPage() {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public Object navigateToUrlByJS(String url) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.location = '" + url + "'");
    }
    
    public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

}
