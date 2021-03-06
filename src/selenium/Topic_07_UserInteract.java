package selenium;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

public class Topic_07_UserInteract {
	WebDriver driver;
	Actions action, moveItem;
	
	String workingDirectory = System.getProperty("user.dir");
	String jsFilePath = workingDirectory + "\\helper\\drag_and_drop_helper.js";
	String jQueryFilePath = workingDirectory + "\\helper\\jquery_load_helper.js";
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		moveItem = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
//	@Test
//	public void TC_01() {
//		driver.get("http://www.myntra.com/");
//		
//		WebElement menuIcon = driver.findElement(By.xpath("//span[text()='Profile']"));
//		action.moveToElement(menuIcon).perform();
//		
//		driver.findElement(By.xpath("//a[text()='log in']")).click();
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
//		
//	}
	
//	@Test
//	public void TC_02() {
//		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
//		
//		List<WebElement> numberIcon = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
//		moveItem.clickAndHold(numberIcon.get(0)).moveToElement(numberIcon.get(3)).release().perform();
//		
//		List<WebElement> selectedIcon = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
//		System.out.println("Selected icon = " + selectedIcon.size());
//		Assert.assertEquals(selectedIcon.size(), 4);
//		
//	}
	
	
//	@Test
//	public void TC_03() throws Exception {
//		driver.get("http://www.seleniumlearn.com/double-click");
//		
//		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
//		action.doubleClick(doubleClickButton).perform();
//		
//		Alert doubleAlert = driver.switchTo().alert();
//		System.out.println(doubleAlert.getText());
//		Assert.assertEquals(doubleAlert.getText(), "The Button was double-clicked.");
//		Thread.sleep(3000);
//		
//		doubleAlert.accept();
//		
//	}
	
	
//	@Test
//	public void TC_04() throws Exception {
//		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
//		
//		WebElement rightButton = driver.findElement(By.xpath("//span[text()='right click me']"));
//		action.contextClick(rightButton).perform();
//		System.out.println("Pass right click button");
//		
//		WebElement quitIcon = driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']//span[text()='Quit']"));
//		action.moveToElement(quitIcon).perform();
//		System.out.println("Pass hover Quit icon");
//		Thread.sleep(3000);
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']")).isDisplayed());
//		
//		quitIcon.click();
//		System.out.println("Pass click Quit icon");
//		
//	}
	
//	@Test
//	public void TC_05() throws Exception {
//		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
//		
//		WebElement dragObject = driver.findElement(By.xpath("//div[@id='draggable']"));
//		WebElement dropObject = driver.findElement(By.xpath("//div[@id='droptarget']"));
//		action.clickAndHold(dragObject).moveToElement(dropObject).release().perform();
//		Thread.sleep(3000);
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
//		
//	}
	
	
	@Test
	public void TC_06() throws Exception {
		driver.get("https://html5demos.com/drag/#");
		
		String oneCss = "#one";
		String targetCss = "#bin";
		
		//String jQueryLoader = readFile(jQueryFilePath);
		String java_script = readFile(jsFilePath);
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		// je.executeScript(jQueryLoader);
		
		java_script = java_script + "$(\"" + oneCss + "\").simulateDragDrop({ droptarget: \"" + targetCss + "\"});";
		je.executeScript(java_script);
		Thread.sleep(3000);
		
}
	
	
	@Test
	public void TC_07() throws Exception {
		driver.get("https://html5demos.com/drag/#");
		
		
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}

	
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) >0) {
				builder.append(buffer, 0 , read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
}
	
	
	
	