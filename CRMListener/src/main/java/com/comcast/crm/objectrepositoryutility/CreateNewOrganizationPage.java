package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {

	WebDriver driver;

	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname")
	private WebElement orgNameEdt;

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getSaveBtn() {
		return orgNameEdt;
	}

	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}

	public void createOrg(String orgName, String industryName, String typeName) {
		orgNameEdt.sendKeys(orgName);

		WebElement indDD = driver.findElement(By.name("industry"));
		Select selInd = new Select(indDD);
		selInd.selectByVisibleText(industryName);
		WebElement typeDD = driver.findElement(By.name("accounttype"));
		Select selType = new Select(typeDD);
		selType.selectByVisibleText(typeName);

		saveBtn.click();
	}

}
