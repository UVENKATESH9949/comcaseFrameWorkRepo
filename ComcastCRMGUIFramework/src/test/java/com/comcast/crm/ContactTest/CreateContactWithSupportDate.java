package com.comcast.crm.ContactTest;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.gneric.FileUtility.FileUtility;
import com.comcast.crm.gneric.FileUtility.XL_Utility;
import com.comcast.crm.gneric.WebDrivertility.JavaUtility;
import com.comcast.crm.gneric.WebDrivertility.WebDriverUtility;

public class CreateContactWithSupportDate {

	@Test
	public void CreateContactWithSupportDate() throws Exception {
		FileUtility fiu = new FileUtility();
		WebDriverUtility wu = new WebDriverUtility();
		XL_Utility xu = new XL_Utility();
		JavaUtility ju = new JavaUtility();
		
		String browser = fiu.getDataFromPropertyFile("browser");
		String url = fiu.getDataFromPropertyFile("url");
		String username =fiu.getDataFromPropertyFile("username");
		String pass = fiu.getDataFromPropertyFile("password");
		
		WebDriver driver = null;
		if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		
		else if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		
		else if(browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		else {
			driver = new ChromeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		wu.waitForPageToLoad(driver);
		

		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(pass);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
		String LastName =xu.getDataFromExcel("contact", 4, 2).toString();
		
		String actdate = ju.getSystemDateYYYYDDMM();
		String dateRequire = ju.getRequireDateYYYYDDMM(30);
		
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(actdate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(dateRequire);
		driver.findElement(By.name("button")).click();
		

		String header = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]")).getText();
		if(header.contains(LastName)) {
			System.out.println(LastName +" is created == pass");
		}else {
			System.out.println(LastName+" is not created == Fail");
		}
		String actStartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartdate.equals(actdate)) {
			System.out.println(actStartdate+" is verified == pass");
		}else {
			System.out.println(actStartdate+" is not verified == Fail");
		}
		
		
		String actenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actenddate.equals(dateRequire)) {
			System.out.println(actenddate+" is verified == pass");
		}else {
			System.out.println(actenddate+" is not verified == Fail");
		}
		Thread.sleep(3000);
		driver.quit();
	}

}
