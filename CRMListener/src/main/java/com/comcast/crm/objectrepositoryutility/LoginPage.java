package com.comcast.crm.objectrepositoryutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	Rule - 1 : Create a separate java class
//	Rule - 2 : object creation
	
	@FindBy(name= "user_name")
//	as they are default we can't utilise directly
	WebElement usernameEdt;
	
	@FindBy(name= "user_password")
	WebElement passwordEdt;
	
	@FindBy(id= "submitButton")
	WebElement loginBtn;

	public WebElement getUsernameEdt() {
		
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
//	Rule - 3 : OBject Initializaiton
//	Rule - 4 : Object Encapsulation
	public void loginToApp(String url,String username, String password) {
		driver.manage().window().maximize();
		driver.get(url);
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
}
