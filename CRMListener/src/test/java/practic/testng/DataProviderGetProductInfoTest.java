package practic.testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderGetProductInfoTest {
	@Test(dataProvider = "getData")
	public void getPRoductInfoTest(String brand, String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://amazon.in");

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone 13");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		String x= "//span[text()='" + productName + "']/../../../../div[3]/div[1]/div/div[1]/div[1]/div/a/span/span[2]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][2];
		objArr[0][0] = "iphone";
		objArr[0][1] = "Apple iPhone 13 (128GB) - Starlight";

		objArr[1][0] = "iphone";
		objArr[1][1] = "Apple iPhone 13 (128GB) - (Product) RED";

		objArr[2][0] = "iphone";
		objArr[2][1] = "Apple iPhone 13 (256GB) - Starlight";

		return objArr;

	}
}
