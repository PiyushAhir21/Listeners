package com.crm.generic.baseutility;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public static WebDriver sdriver;
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public DataBaseUtility dblib = new DataBaseUtility();
	public WebDriverUtility wdlib = new WebDriverUtility();

	@BeforeSuite
	public void configBS() throws SQLException {
		System.out.println("-----Connect to DB, Report config------");
		dblib.getDbConnection();
	}

	@BeforeClass
	public void configBC() throws Throwable {
		System.out.println("Launch browser");
		String BROWSER = flib.getDataFromPropertyFile("browser");

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
	}

	@BeforeMethod
	public void configBM() throws Throwable {
		String URL = flib.getDataFromPropertyFile("url");
		String USERNAME = flib.getDataFromPropertyFile("username");
		String PASSWORD = flib.getDataFromPropertyFile("password");

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
		System.out.println("-----Login-----------");
	}

	@AfterMethod
	public void configAM() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.logout();
		System.out.println("-----Logout---------");
	}

	@AfterClass
	public void configAC() {
		driver.quit();
		System.out.println("Close Browser");
	}

	@AfterSuite
	public void configAS() throws SQLException {
		dblib.closeDbConnection();
		System.out.println("-----Close DB, Report backUp------");
	}

}
