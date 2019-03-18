package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_JavascriptExcutor {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01() {
		driver.get("http://live.guru99.com");
		
		String domainName = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainName, "live.guru99.com");
		
		String homePageURL = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(homePageURL, "http://live.guru99.com/");
		
		clickToElementByJS("//a[text()='Mobile']");
		
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		
		String shoppingCartInnerText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(shoppingCartInnerText.contains("Samsung Galaxy was added to your shopping cart."));
		
		clickToElementByJS("//a[text()='Privacy Policy']");
		
		String privacyTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(privacyTitle, "Privacy Policy");
		
		scrollToBottomPage();
		
		Assert.assertTrue(driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']")).isDisplayed());
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		String demoDomainName = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoDomainName, "demo.guru99.com");
		
	}
  
	@Test
	public void TC_02() throws Exception {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		
		String firstName = "The Vuong";
		String lastName = "Nguyen Pham";
		
		WebElement resultIframe = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(resultIframe);
		
		// Remove disabled attribute of Lastname textbox
		removeAttributeInDOM("//input[@name='lname']", "disabled");
		Thread.sleep(3000);
		
		sendkeyToElementByJS("//input[@name='fname']", firstName);
		sendkeyToElementByJS("//input[@name='lname']", lastName);
		
		clickToElementByJS("//input[@type='submit']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'" + firstName + "') and contains(text(),'" + lastName + "')]")).isDisplayed());
		
		
	}

	@Test
	public void TC_03() throws Exception {
		driver.get("http://live.guru99.com");
		
		String firstName2 = "The Vuong";
		String lastName2 = "Nguyen Pham";
		String password = "theVuong17";
		String email = "automationtesting" + randomNumber() + "@gmail.com";
		
		
		clickToElementByJS("//div[@class='footer-container']//a[text()='My Account']");
		
		clickToElementByJS("//a[@class='button']");
		
		sendkeyToElementByJS("//input[@id='firstname']", firstName2);
		sendkeyToElementByJS("//input[@id='lastname']", lastName2);
		sendkeyToElementByJS("//input[@id='email_address']", email);
		sendkeyToElementByJS("//input[@id='password']", password);
		sendkeyToElementByJS("//input[@id='confirmation']", password);
		
		clickToElementByJS("//input[@id='is_subscribed']");
		
		clickToElementByJS("//button[@title='Register']");
		
		String registerSuccessfulText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(registerSuccessfulText.contains("Thank you for registering with Main Website Store."));
		
		clickToElementByJS("//a[@class='skip-link skip-account']");
		
		clickToElementByJS("//a[text()='Log Out']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
		
		Thread.sleep(3000);
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
