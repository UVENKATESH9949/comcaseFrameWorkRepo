package com.comcast.crm.ContactTest;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.objectRepositoryUtility.ContactInformationPage;
import com.comcast.crm.objectRepositoryUtility.ContactPage;
import com.comcast.crm.objectRepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInformationPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationPage;

public class CreateContactWithOrgName {

	@Test
	public void CreateContactWithOrgName() throws Exception {
		FileUtility fiu = new FileUtility();
		WebDriverUtility wu = new WebDriverUtility();
		XL_Utility xu = new XL_Utility();
		JavaUtility ju = new JavaUtility();
		
		String browser = fiu.getDataFromPropertyFile("browser");
		String url = fiu.getDataFromPropertyFile("url");
		String username =fiu.getDataFromPropertyFile("username");
		String pass = fiu.getDataFromPropertyFile("password");
		
		WebDriver driver = wu.luachBrowser(browser);
		
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		ContactPage cp = new ContactPage(driver);
		ContactInformationPage cip = new ContactInformationPage(driver);
		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		
		
		driver.get(url);
		wu.waitForPageToLoad(driver);

		lp.loginTOApp(username, pass);
		
		new HomePage(driver).getOrgpageLink().click();
		
		new OrganizationPage(driver).getNewOrgLink().click();
		
		String orgname =xu.getDataFromExcel("org", 1, 2).toString()+ju.getRandomNumber();
		String shippingAddress = xu.getDataFromExcel("org", 1, 2).toString();

		new CreatingNewOrganizationPage(driver).createOrg(orgname, shippingAddress);
		
		String header =new OrganizationInformationPage(driver).getHeader().getText();
		if(header.contains(orgname)) {
			System.out.println(orgname +" is created == pass");
		}else {
			System.out.println(orgname+" is not created == Fail");
		}
		String LastName =xu.getDataFromExcel("contact", 7, 3).toString();
		
		hp.getContactpageLink().click();
		cp.getCreateNewContactIcon().click();
		cnp.getLastnameInput().sendKeys(LastName);
		cnp.getOrgLinkIcon().click();
		
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		wu.switchNewBrowserTab(driver, "module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(orgname);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		
		wu.switchNewBrowserTab(driver, "module=Contacts");
		
		driver.findElement(By.name("button")).click();		

		String header2 = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]")).getText();
		if(header2.contains(LastName)) {
			System.out.println(LastName +" is created == pass");
		}else {
			System.out.println(LastName+" is not created == Fail");
		}
		
		String actoegname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if(actoegname.trim().equals(orgname)) {
			System.out.println(orgname +" is created == pass");
		}else {
			System.out.println(orgname+" is not created == Fail");
		}
		
		
		Thread.sleep(3000);
		driver.quit();
	}
}
