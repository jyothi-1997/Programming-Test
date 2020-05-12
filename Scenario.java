package basic.ProgrammingTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;

public class Scenario {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("open browser");
		//implicitwait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void f() throws InterruptedException {
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/login.htm");
		driver.findElement(By.xpath("//input[contains(@id,'userName')]")).sendKeys("Lalitha");
		driver.findElement(By.name("password")).sendKeys("Password123");
		driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();

		WebElement abt =  driver.findElement(By.xpath("//span[contains(.,'AboutUs')]"));
		WebElement ofc = driver.findElement(By.xpath("//span[contains(.,'Our\n" + 
				"												Offices')]"));
		WebElement blore = driver.findElement(By.xpath("//span[contains(.,'Bangalore')]"));

		Actions act = new Actions(driver);
		act.moveToElement(abt).click().pause(2000).perform();
		act.moveToElement(ofc).click().pause(2000).perform();
		act.moveToElement(blore).click().pause(2000).perform();
		Thread.sleep(2000);

		Set<String> allWH = driver.getWindowHandles();
		Thread.sleep(2000);

		for (String singleH:allWH) {
			driver.switchTo().window(singleH);
			Thread.sleep(2000);
			System.out.println("Child window handle is: "+singleH);
		}
		WebElement frame = driver.findElement(By.name("main_page"));
		driver.switchTo().frame(frame);

		String address=driver.findElement(By.tagName("address")).getText();
		System.out.println(address);

		driver.close();



	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
		System.out.println("closed all tabs");

	}

}
