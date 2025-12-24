package com.comcast.crm.listenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.gneric.WebDrivertility.UtilityClassObject;
import com.crm.comcast.baseTest.BaseTest;

public class ListenerImpClass implements ITestListener, ISuiteListener{

	ExtentReports report = new ExtentReports();
	public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suit Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("browser", "chrome");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backUp");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName());
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject ucb = new UtilityClassObject();
		ucb.setTest(test);
		test.log(Status.INFO, "This test case is "+result.getMethod().getMethodName()+"---started---");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("This is test ended");
		test.log(Status.PASS, "Completed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
//		test = report.createTest("CrateContact");
		String testName = result.getMethod().getMethodName();
		
		TakesScreenshot ts = (TakesScreenshot)BaseTest.sdriver;
		String filepath = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filepath,"Failue");
		test.log(Status.FAIL, "Failed");
//		File src =ts.getScreenshotAs(OutputType.FILE);
//		String time = new Date().toString().replaceAll(":", "_");
//		File dest = new File("./screenshot/"+testName+"+"+time+".png");
//		
//		try {
//			FileHandler.copy(src, dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println();
		test.log(Status.SKIP, "Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println();
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println();
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println();
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println();
	}

}
