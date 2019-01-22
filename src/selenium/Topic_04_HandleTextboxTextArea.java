package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_HandleTextboxTextArea {

	By nameLocator = By.xpath("//input[@name='name']");
	By dateOfBirthLocator = By.xpath("//input[@name='dob']");
	By addressLocator = By.xpath("//textArea[@name='addr']");
	By cityLocator = By.xpath("//input[@name='city']");
	By stateLocator = By.xpath("//input[@name='state']");
	By pinLocator = By.xpath("//input[@name='pinno']");
	By phoneLocator = By.xpath("//input[@name='telephoneno']");
	By emailLocator = By.xpath("//input[@name='emailid']");
	By submitLocator = By.xpath("//input[@name='sub']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		customerName = "Jimmy NguyenPham";
		dateOfBirth = "1999-02-02";
		address = "124 Xo Viet Nghe Tinh";
		city = "Ho Chi Minh";
		state = "Tan Binh";
		pin = "123456";
		phone = "0912345678";
		email = "selenium" + randomNumber() + "@gmail.com";
		password = "theVuong17";

		editAddress = "Edit Luu Chi Hieu";
		editCity = "Edit Vung Tau";
		editState = "Edit Quan 5";
		editPIN = "999999";
		editPhone = "0999999999";
		editEmail = "editselenium" + randomNumber() + "@gmail.com";
	}

	@Test
	public void TC_01_CreateNewCustomer() throws Exception {
		// Login with username and password
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr172599");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("bYbYbun");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		// Verify homepage
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3' and text()= \"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

		Thread.sleep(3000);

		// Open new customer page
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Input data to customer
		driver.findElement(nameLocator).sendKeys(customerName);
		driver.findElement(dateOfBirthLocator).sendKeys(dateOfBirth);
		driver.findElement(addressLocator).sendKeys(address);
		driver.findElement(cityLocator).sendKeys(city);
		driver.findElement(stateLocator).sendKeys(state);
		driver.findElement(pinLocator).sendKeys(pin);
		driver.findElement(phoneLocator).sendKeys(phone);
		driver.findElement(emailLocator).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(submitLocator).click();

		// Get dynamic customerid
		customerId = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID = " + customerId);

		// Verify customer info match input data
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

		Thread.sleep(3000);
	}

	@Test
	public void TC_02_EditNewCustomer() throws Exception {
		// Open edit customer page
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		// Input customerID
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerId);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		// Verify matching input data
		Assert.assertEquals(driver.findElement(nameLocator).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(dateOfBirthLocator).getAttribute("value"), dateOfBirth);
		Assert.assertEquals(driver.findElement(addressLocator).getText(), address);
		Assert.assertEquals(driver.findElement(cityLocator).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateLocator).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinLocator).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneLocator).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailLocator).getAttribute("value"), email);
		
		// Edit customer
		driver.findElement(addressLocator).clear();
		driver.findElement(addressLocator).sendKeys(editAddress);
		driver.findElement(cityLocator).clear();
		driver.findElement(cityLocator).sendKeys(editCity);
		driver.findElement(stateLocator).clear();
		driver.findElement(stateLocator).sendKeys(editState);
		driver.findElement(pinLocator).clear();
		driver.findElement(pinLocator).sendKeys(editPIN);
		driver.findElement(phoneLocator).clear();
		driver.findElement(phoneLocator).sendKeys(editPhone);
		driver.findElement(emailLocator).clear();
		driver.findElement(emailLocator).sendKeys(editEmail);
		driver.findElement(submitLocator).click();

		// Verify edit customer info match input data
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPIN);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
		
		Thread.sleep(3000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(99999);

	}

	WebDriver driver;
	String customerName, dateOfBirth, address, city, state, pin, phone, email, password, customerId;
	String editAddress, editCity, editState, editPIN, editPhone, editEmail;

}
