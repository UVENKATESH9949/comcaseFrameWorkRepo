package com.comcast.crm.orgTest;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.gneric.FileUtility.FileUtility;
import com.comcast.crm.gneric.FileUtility.XL_Utility;
import com.comcast.crm.gneric.WebDrivertility.JavaUtility;
import com.comcast.crm.gneric.WebDrivertility.WebDriverUtility;
import com.comcast.crm.objectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInformationPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationPage;

public class CreateOrgWithIndustry {

	@Test
	public void  CreateOrgWithIndustry() throws Exception {
		FileUtility fiu = new FileUtility();
		WebDriverUtility wu = new WebDriverUtility();
		XL_Utility xu = new XL_Utility();
		JavaUtility ju = new JavaUtility();
		
		String browser = fiu.getDataFromPropertyFile("browser");
		String url = fiu.getDataFromPropertyFile("url");
		String username =fiu.getDataFromPropertyFile("username");
		String pass = fiu.getDataFromPropertyFile("password");
		
		WebDriver driver = wu.luachBrowser(browser);
		
		driver.get(url);
		wu.waitForPageToLoad(driver);

		LoginPage lp = new LoginPage(driver);

		lp.loginTOApp(username, pass);
		
		new HomePage(driver).getOrgpageLink().click();
		
		new OrganizationPage(driver).getNewOrgLink().click();;
		
		String orgname =xu.getDataFromExcel("org", 1, 2).toString()+ju.getRandomNumber();
		String shippingAddress = xu.getDataFromExcel("org", 1, 2).toString();
		String industry = xu.getDataFromExcel("org", 4, 3).toString();
		
		new CreatingNewOrganizationPage(driver).createOrg(orgname, shippingAddress, industry);
		
		String actualIndustry = new OrganizationInformationPage(driver).getIndutry().getText();
		if(actualIndustry.equals(industry)) {
			System.out.println(actualIndustry +" Industry is created == pass");
		}
		else {
			System.out.println(actualIndustry +" Industry not created == Fail");
		}
		
		Thread.sleep(3000);
		
		new HomePage(driver).logout();
	}
}
