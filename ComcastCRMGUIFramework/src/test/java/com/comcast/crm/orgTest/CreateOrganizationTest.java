package com.comcast.crm.orgTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
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
import com.crm.comcast.baseTest.BaseTest;

@Listeners(com.comcast.crm.listenerUtility.ListenerImpClass.class)
public class CreateOrganizationTest extends BaseTest{

	@Test(groups = "smokeTest")
	public void CreateOrganizationTest() throws Exception {
		
		XL_Utility xu = new XL_Utility();
		JavaUtility ju = new JavaUtility();
		
		new HomePage(driver).getOrgpageLink().click();
		
		new OrganizationPage(driver).getNewOrgLink().click();
		
		String orgname =xu.getDataFromExcel("org", 1, 2).toString()+ju.getRandomNumber();
		String shippingAddress = xu.getDataFromExcel("org", 1, 2).toString();

		new CreatingNewOrganizationPage(driver).createOrg(orgname, shippingAddress);
		
		Thread.sleep(2000);
		String header =new OrganizationInformationPage(driver).getHeader().getText();
		if(header.contains(orgname)) {
			System.out.println(orgname +" is created == pass");
		}else {
			System.out.println(orgname+" is not created == Fail");
		}
	}
	
	
	@Test(groups = "RT")
	public void  CreateOrgWithIndustry() throws Exception {
		
		XL_Utility xu = new XL_Utility();
		JavaUtility ju = new JavaUtility();

		new HomePage(driver).getOrgpageLink().click();
		new OrganizationPage(driver).getNewOrgLink().click();;
		
		String orgname =xu.getDataFromExcel("org", 1, 2).toString()+ju.getRandomNumber();
		String shippingAddress = xu.getDataFromExcel("org", 1, 2).toString();
		String industry = xu.getDataFromExcel("org", 4, 3).toString();
		
		new CreatingNewOrganizationPage(driver).createOrg(orgname, shippingAddress, industry);
		Thread.sleep(2000);
		
		String actualIndustry = new OrganizationInformationPage(driver).getIndutry().getText();
		if(actualIndustry.equals(industry)) {
			System.out.println(actualIndustry +" Industry is created == pass");
		}
		else {
			System.out.println(actualIndustry +" Industry not created == Fail");
		}
	}
	
	@Test(groups = "RT")
	public void CreateOrgWithPhoneNumber() throws Exception {
		HomePage hp = new HomePage(driver);
		OrganizationPage op = new OrganizationPage(driver);
		XL_Utility xu = new XL_Utility();
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		JavaUtility ju = new JavaUtility();
		
		hp.getOrgpageLink().click();
		
		op.getNewOrgLink().click();
				
		
		String orgname =xu.getDataFromExcel("org", 7, 2).toString()+ju.getRandomNumber();
		String shippingAddress = xu.getDataFromExcel("org", 7, 2).toString();
		String phone = xu.getDataFromExcel("org", 7, 3).toString();
		
		cop.createOrgWithPhone(orgname, phone, shippingAddress);
		
		Thread.sleep(2000);
		String actphoneNumber = oip.getPhonereview().getText();
		if(actphoneNumber.equals(phone)) {
			System.out.println(phone +" is verified == pass");
		}else {
			System.out.println(phone +" is not verified == Fail");
		}
	}
}
