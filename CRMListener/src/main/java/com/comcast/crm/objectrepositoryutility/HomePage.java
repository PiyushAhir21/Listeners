package com.comcast.crm.objectrepositoryutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Organizations")
	private WebElement orgLink;

	@FindBy(linkText = "Contacts")
	private WebElement contactlnk;

	@FindBy(xpath = "//img[@src= 'themes/softed/images/user.PNG']")
	public WebElement adminImage;

	@FindBy(xpath = "//a[text()='Sign Out']")
	public WebElement signOutLnk;

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactlnk() {
		return contactlnk;
	}
	
	public void logout() throws InterruptedException {
		Actions act = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		WebElement verify = driver.findElement(By.xpath("//span[contains(text(),Contact Information)]"));
//		wait.until(ExpectedConditions.visibilityOf(verify));
		Thread.sleep(5000);
		act.moveToElement(adminImage).perform();
		signOutLnk.click();
	}

}
