package com.comcast.crm.listenersutility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.crm.generic.baseutility.BaseClass;

public class ListImp implements ISuiteListener, ITestListener {
	
	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + "=========START==========");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + "=========END==========");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		EventFiringWebDriver eDriver = new EventFiringWebDriver(BaseClass.sdriver);
		File srcFile = eDriver.getScreenshotAs(OutputType.FILE);

//		String time = new Date().toString().replace(" ","").replace(":", "");
		LocalDateTime now = LocalDateTime.now();
		String time = now.format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"));

		File destfile = new File("./screenshot/" + time + ".png");
		try {
			FileUtils.copyFile(srcFile, destfile);
			System.out.println("Screenshot taken !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
	}
}