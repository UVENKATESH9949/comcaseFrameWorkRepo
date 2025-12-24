package com.comcast.crm.orgTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.comcast.crm.gneric.FileUtility.FileUtility;
import com.comcast.crm.gneric.FileUtility.XL_Utility;
import com.comcast.crm.gneric.WebDrivertility.JavaUtility;
import com.comcast.crm.gneric.WebDrivertility.WebDriverUtility;
import com.comcast.crm.objectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInformationPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationPage;

public class DeleteOrgTest {

	public static void main(String[] args) throws Exception {
		
		
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

		HomePage hp = new HomePage(driver);
		OrganizationPage op = new OrganizationPage(driver);
				
		LoginPage lp = new LoginPage(driver);

		lp.loginTOApp(username, pass);
		
		hp.getOrgpageLink().click();
		
		op.getNewOrgLink().click();;
		
		String orgname =xu.getDataFromExcel("org", 1, 2).toString()+ju.getRandomNumber();
		String shippingAddress = xu.getDataFromExcel("org", 1, 2).toString();

		new CreatingNewOrganizationPage(driver).createOrg(orgname, shippingAddress);
		
		String header =new OrganizationInformationPage(driver).getHeader().getText();
		if(header.contains(orgname)) {
			System.out.println(orgname +" is created == pass");
		}else {
			System.out.println(orgname+" is not created == Fail");
		}
		
		hp.getOrgpageLink().click();
		
		wu.select(op.getSearchType(), "Organization Name");
		op.getSearchInput().sendKeys(orgname);
		op.getSearchBtn().click();
		
		Thread.sleep(2000);
		driver.findElement(
			    By.xpath("//a[text()='"+orgname+"']/ancestor::tr//a[text()='del']")
			).click();
		
		wu.switchToAlertAndAccept(driver);
		
		System.out.println(orgname +" is Deleted == pass");
		
		Thread.sleep(3000);
		
		new HomePage(driver).logout();
	}
}
