package practic.testng;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class ScreenshotTest {

	@Test
	public void amazonTest() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://amazon.in");
		
//		step - 1 : create an object of EventFiringWebDriver
		EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);

//		step - 2 : Use getScreenshotAs() to get file type of screenshot
		File srcFile = eDriver.getScreenshotAs(OutputType.FILE);
		
//		Step - 3 : Store screenshot in local driver
		FileUtils.copyFile(srcFile, new File("./screenshot/test.png"));
	}
}
