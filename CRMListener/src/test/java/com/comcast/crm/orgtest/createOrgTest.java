package com.comcast.crm.orgtest;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.crm.generic.baseutility.BaseClass;

public class createOrgTest extends BaseClass {
	@Test
	public void createOrg() throws Throwable {
		FileInputStream fis = new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet3");
		Row row = sh.getRow(1);
		String orgName = row.getCell(2).toString() + jlib.getRandomNum();

		driver.findElement(By.linkText("Organizations")).click();

		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		driver.findElement(By.xpath("//input[@type='text' and @name='accountname']")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[@type='button' and @class='crmbutton small save'])[2]")).click();

		Thread.sleep(5000);
		WebElement verify = driver.findElement(By.xpath("//span[contains(text(), 'Updated today')]"));
		if (verify.isDisplayed()) {
			System.out.println(orgName + " Created Successfully!!!");
		}
	}

	@Test
	public void createOrgWithIndustry() throws Throwable {
		FileInputStream fis1 = new FileInputStream("C:\\Users\\User\\Desktop\\AdvanceSelenium/testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet3");
		Row row = sh.getRow(1);
		String orgName = row.getCell(2).toString() + jlib.getRandomNum() ;
		
		
		String industryName = sh.getRow(4).getCell(3).toString() ;
		System.out.println(industryName);
		
		String typeName = sh.getRow(4).getCell(4).toString() ;
		System.out.println(typeName);

		
		driver.findElement(By.linkText("Organizations")).click();

		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		driver.findElement(By.xpath("//input[@type='text' and @name='accountname']")).sendKeys(orgName);

		WebElement indDD = driver.findElement(By.name("industry"));
		Select selInd = new Select(indDD);
		selInd.selectByVisibleText(industryName);

		WebElement typeDD = driver.findElement(By.name("accounttype"));
		Select selType = new Select(typeDD);
		selType.selectByVisibleText(typeName);

		driver.findElement(By.xpath("(//input[@type='button' and @class='crmbutton small save'])[2]")).click();

		Thread.sleep(5000);
		WebElement verify = driver.findElement(By.xpath("//span[contains(text(), 'Updated today')]"));
		if (verify.isDisplayed()) {
			System.out.println(orgName + " Created Successfully!!!");
		}

		String IndDD1 = driver.findElement(By.id("dtlview_Industry")).getText();
		if (IndDD1.equals(industryName)) {
			System.out.println(industryName + " selected Successfully!!!");
		}

		String TypeDD2 = driver.findElement(By.id("dtlview_Type")).getText();
		if (TypeDD2.equals(typeName)) {
			System.out.println(typeName + " selected Successfully!!!");
		}
	}

}
