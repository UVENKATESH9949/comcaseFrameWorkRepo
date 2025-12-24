package com.crm.comcast.baseTest;

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
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.gneric.FileUtility.FileUtility;
import com.comcast.crm.gneric.WebDrivertility.JavaUtility;
import com.comcast.crm.gneric.WebDrivertility.UtilityClassObject;
import com.comcast.crm.gneric.WebDrivertility.WebDriverUtility;
import com.comcast.crm.gneric.databaseUtility.DatabaseUtility;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;

import lombok.experimental.UtilityClass;

public class BaseTest {

	FileUtility fiu = new FileUtility();
	public WebDriver driver = null;
	public static  WebDriver sdriver = null;
	
	
	@BeforeSuite(groups ={"smokeTest","RT"})
	public void beforesuit() {
		System.out.println("------Before Suit Executed---------");
	}
	
	
//	@Parameters("browser")
	@BeforeClass
	public void beforeclass() throws Exception {
		String browser = fiu.getDataFromPropertyFile("browser");
		String url = fiu.getDataFromPropertyFile("url");
		if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		
		else if(browser.equals("chrome")) {
			driver =  new ChromeDriver();
		}
		
		else if(browser.equals("edge")) {
			driver =  new EdgeDriver();
		}
		else {
			driver =  new ChromeDriver();
		}
		UtilityClassObject ucb = new UtilityClassObject();
		ucb.setDriver(driver);
		sdriver = driver;
		driver.get(url);
	}
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		String username =fiu.getDataFromPropertyFile("username");
		String pass = fiu.getDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginTOApp(username, pass);
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("Logout");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass
	public void Afterclass() {
		System.out.println("Close Browser");
		driver.close();
	}
	
	@AfterSuite
	public void afterSuit() throws Exception {
		System.out.println("-------------After suit executed-----------");
	}
}
