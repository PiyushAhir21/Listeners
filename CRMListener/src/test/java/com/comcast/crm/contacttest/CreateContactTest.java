package com.comcast.crm.contacttest;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.crm.generic.baseutility.BaseClass;

@Listeners(com.comcast.crm.listenersutility.ListImp.class)
public class CreateContactTest extends BaseClass {

	@Test
	public void createContactTest() throws Throwable {
		// Read testScript data from from excel file
		String LastName = elib.getDataFromExcel("Contact", 1, 2) + jlib.getRandomNum();
		
//		step 2 : navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

//		step 3 : click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

//		step 4 : enter details and create new contact
		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.createContact(LastName);

//		step 5 : verification
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		boolean status = actLastName.contains(LastName + " ");
		Assert.assertEquals(status, true);
	}

	@Test(enabled = false)
	public void createContactWithSupportDateTest() throws Throwable {
		// Read testScript data from from excel file
		ExcelUtility elib = new ExcelUtility();
		String LastName = elib.getDataFromExcel("Contact", 1, 2) + jlib.getRandomNum();

//		step 2 : navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// Date
		String StartDate = jlib.getSystemDateYYYYMMDD();
		String EndDate = jlib.getRequiredDateYYYYMMDD(StartDate, 30);

		System.out.println("Start date : " + StartDate + " End date : " + EndDate);

		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(LastName);

		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(StartDate);

		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(EndDate);

		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])")).click();

		String StartDateVerify = driver.findElement(By.id("dtlview_Support Start Date")).getText();
//		if (StartDateVerify.equals(StartDate)) {
//			System.out.println(StartDate + " given Successfully!!!");
//		}
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(StartDateVerify, StartDate);

		String EndDateVerify = driver.findElement(By.id("dtlview_Support End Date")).getText();
//		if (EndDateVerify.equals(EndDate)) {
//			System.out.println(EndDate + " given Successfully!!!");
//		}
		softAssert.assertEquals(EndDateVerify, EndDate);

	}

	@Test(enabled = false)
	public void createContactWithOrgTest() throws Throwable {
		String orgName = elib.getDataFromExcel("Contact", 7, 2) + jlib.getRandomNum();
		String LastName = elib.getDataFromExcel("Contact", 1, 2) + jlib.getRandomNum();
		
		driver.findElement(By.linkText("Organizations")).click();

		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		driver.findElement(By.xpath("//input[@type='text' and @name='accountname']")).sendKeys(orgName);

		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])")).click();

		Thread.sleep(5000);
		
		WebElement verify = driver.findElement(By.xpath("//span[contains(text(), "+ orgName +" )]"));
//		if (verify.isDisplayed()) {
//			System.out.println(orgName + " Created Successfully!!!");
//		}
		Assert.assertTrue(verify.isDisplayed());

//		navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(LastName);

		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

//		switch to child window		
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);

			String actUrl = driver.getCurrentUrl();
			if (actUrl.contains("module=Accounts")) {
				break;
			}
		}

		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();

//		switch to parent window
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> it1 = set1.iterator();
		while (it1.hasNext()) {
			String windowID = it1.next();
			driver.switchTo().window(windowID);

			String actUrl = driver.getCurrentUrl();
			if (actUrl.contains("Contacts&action")) {
				break;
			}
		}

		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])")).click();
		
		
		String verifyLn = driver.findElement(By.id("dtlview_Last Name")).getText();
//		if (verifyLn.equals(LastName)) {
//			System.out.println("Lastname : " + LastName + " given Successfully!!!");
//		}
		Assert.assertEquals(verifyLn, LastName);
	}

}
