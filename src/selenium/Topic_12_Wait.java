package selenium;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
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


public class Topic_12_Wait {
	WebDriver driver;
	WebDriverWait waitExplicit;
	By startButton = By.xpath("//div[@id='start']//button");
	By loadingIcon = By.xpath("//div[@id='loading']//img");
	By helloText = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");
	
	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		
		
		
		
	}
	
	@Test
	public void TC_01_ImplicitWait_2s() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		driver.findElement(startButton).click();
		
		Assert.assertTrue(driver.findElement(helloText).isDisplayed());
		
	}
	
	
	@Test
	public void TC_02_ImplicitWait_5s() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(startButton).click();
		
		Assert.assertTrue(driver.findElement(helloText).isDisplayed());
		
	}
	
	
	@Test
	public void TC_03_LoadingIconInvisible() {
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			waitExplicit = new WebDriverWait(driver, 5);
			
			driver.findElement(startButton).click();
			
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
			
			Assert.assertTrue(driver.findElement(helloText).isDisplayed());
				
	
	}
	
	
	@Test
	public void TC_04_HelloTextVisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);
		
		driver.findElement(startButton).click();
		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		
		Assert.assertTrue(driver.findElement(helloText).isDisplayed());
	}
	
	
	@Test
	public void TC_05_HelloText_LoadingIcon_NoLongerInDOM() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);
		
		// invisible + not in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		System.out.println("End time = " + getDateTimeSecond());
		
		// invisible + not in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
		
		System.out.println("Start time = " + getDateTimeSecond());
		driver.findElement(startButton).click();
		System.out.println("End time = " + getDateTimeSecond());
		
		// invisible + in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		System.out.println("End time = " + getDateTimeSecond());
		
		// invisible + in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(startButton));
		System.out.println("End time = " + getDateTimeSecond());
		
		
	}

		

		
	
	
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public Date getDateTimeSecond() {
        Date date = new Date();
        date = new Timestamp(date.getTime());
        return date;
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
