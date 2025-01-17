package practic.testng;

import java.time.Duration;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class DataProviderGetProductInfoUsingExcel {
	@Test(dataProvider = "getData")
	public void getPRoductInfoTest(String brand, String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://amazon.in");

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone 13");
		driver.findElement(By.id("nav-search-submit-button")).click();

		String x = "//span[text()='" + productName + "']/../../../../div[3]/div[1]/div/div[1]/div[1]/div/a/span/span[2]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);

		driver.quit();
	}

	@DataProvider
	public Object[][] getData() throws Throwable {
		
		ExcelUtility elib = new ExcelUtility();
		int rowc = elib.getRowCount("product");

		Object[][] objArr = new Object[rowc][2];
		
		for (int i = 0; i < rowc; i++){
			objArr[i][0] = elib.getDataFromExcel("product", i + 1, 0);
			objArr[i][1] = elib.getDataFromExcel("product", i + 1, 1);
		}
		
		return objArr;
	}
}
