package com.comcast.crm.ContactTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.gneric.FileUtility.FileUtility;
import com.comcast.crm.gneric.FileUtility.XL_Utility;
import com.comcast.crm.gneric.WebDrivertility.JavaUtility;
import com.comcast.crm.gneric.WebDrivertility.UtilityClassObject;
import com.comcast.crm.gneric.WebDrivertility.WebDriverUtility;
import com.comcast.crm.listenerUtility.ListenerImpClass;
import com.comcast.crm.objectRepositoryUtility.ContactInformationPage;
import com.comcast.crm.objectRepositoryUtility.ContactPage;
import com.comcast.crm.objectRepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInformationPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationPage;
import com.crm.comcast.baseTest.BaseTest;

@Listeners(com.comcast.crm.listenerUtility.ListenerImpClass.class)
public class CreateContactTest extends BaseTest{
	
//	@Test(groups = "smokeTest")
	@Test
	public void CreateContactTest() throws IOException, Exception {
		
		HomePage hp = new HomePage(driver);
		ContactPage cp = new ContactPage(driver);
		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		ContactInformationPage cip = new ContactInformationPage(driver);
		XL_Utility xlu = new XL_Utility();
		JavaUtility ju = new JavaUtility();
		UtilityClassObject ucb = new UtilityClassObject();
		
		ucb.getTest().log(Status.INFO, "Navigating to contact page");
		hp.getContactpageLink().click();
		ucb.getTest().log(Status.INFO, "Navigating to create new contact page");
		cp.getCreateNewContactIcon().click();
		ucb.getTest().log(Status.INFO, "reading data from xl");
		String LastName =xlu.getDataFromExcel("contact", 1, 2);
		ucb.getTest().log(Status.INFO, "Ncreating new conatct page");
		cnp.createNewContact(LastName);
		ucb.getTest().log(Status.INFO, "validating header");
		String header = cip.getHeader().getText();
		
		boolean  b = header.contains(LastName);
		Assert.assertEquals(b, true);
		ucb.getTest().log(Status.PASS, "Successfully created ");
	}

//	@Test(groups = "RT")
	@Test
	public void CreateContactWithSupportDate() throws IOException, Exception {	
		
		HomePage hp = new HomePage(driver);
		ContactPage cp = new ContactPage(driver);
		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		ContactInformationPage cip = new ContactInformationPage(driver);
		XL_Utility xlu = new XL_Utility();
		JavaUtility ju = new JavaUtility();
		
		hp.getContactpageLink().click();
		
		cp.getCreateNewContactIcon().click();
		
		String LastName =xlu.getDataFromExcel("contact", 1, 2);
		String actdate = ju.getSystemDateYYYYDDMM();
		String dateRequire = ju.getRequireDateYYYYDDMM(30);
		
		
		cnp.createNewContactWithSupproDate(LastName, actdate, dateRequire);
		
		
		String header = cip.getHeader().getText();
		boolean headervalidation = header.contains(LastName);
		Assert.assertEquals(headervalidation, true);
		System.out.println("First assertion pass");
		
		String actstartdate = cip.getStartDate().getText();
		boolean startdatevalidation = actstartdate.equals(actdate);
		Assert.assertEquals(startdatevalidation, true);
		System.out.println("Second assertion pass");
		
		String actenddate = cip.getEndDate().getText();
		boolean enddatevalidation = actenddate.equals(dateRequire);
		Assert.assertEquals(enddatevalidation, true);
		System.out.println("Third assertion pass");
	}
	
//	@Test(retryAnalyzer = com.comcast.crm.listenerUtility.RetryListenerImp.class)
	@Test
	public void CreateContactWithOrgName() throws IOException, Exception {
		HomePage hp = new HomePage(driver);
		ContactPage cp = new ContactPage(driver);
		OrganizationPage op = new OrganizationPage(driver);
		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		ContactInformationPage cip = new ContactInformationPage(driver);
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		XL_Utility xu = new XL_Utility();
		JavaUtility ju = new JavaUtility();
		WebDriverUtility wu = new WebDriverUtility();
		
		hp.getOrgpageLink().click();
		op.getNewOrgLink().click();
		
		String orgname =xu.getDataFromExcel("org", 1, 2).toString()+ju.getRandomNumber();
		String shippingAddress = xu.getDataFromExcel("org", 1, 2).toString();
		
		cnop.createOrg(orgname, shippingAddress);
		Thread.sleep(3000);
		hp.getContactpageLink().click();;
		cp.getCreateNewContactIcon().click();
		
		String LastName =xu.getDataFromExcel("contact", 7, 3).toString();
		
		cnp.creatingNewContactWithOrgName(LastName, orgname);
		
		String heading = cip.getHeader().getText();
		String savedorgname = cip.getOrgName().getText();
		
		Assert.assertEquals(heading.contains(LastName), true);
		
		Assert.assertEquals(savedorgname.contains(orgname), true);
		
	}
}
