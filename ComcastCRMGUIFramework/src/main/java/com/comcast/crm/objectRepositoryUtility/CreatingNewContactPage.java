package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.gneric.WebDrivertility.WebDriverUtility;

public class CreatingNewContactPage {

	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement LastnameInput;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgLinkIcon;
	
	@FindBy(name = "support_start_date")
	private WebElement supportStartDateInput;
	
	@FindBy(name = "support_end_date")
	private WebElement supportEndDateInput;
	
	@FindBy(name = "button")
	private WebElement saveBtn;
	
	
	public WebElement getLastnameInput() {
		return LastnameInput;
	}

	public WebElement getOrgLinkIcon() {
		return orgLinkIcon;
	}

	public WebElement getSupportStartDateInput() {
		return supportStartDateInput;
	}

	public WebElement getSupportEndDateInput() {
		return supportEndDateInput;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createNewContact(String LastName) {
		LastnameInput.sendKeys(LastName);
		saveBtn.click();
		
	}
	
	public void createNewContactWithSupproDate(String Lastname, String fromdate, String todate) {
		LastnameInput.sendKeys(Lastname);
		supportStartDateInput.clear();
		supportStartDateInput.sendKeys(fromdate);
		supportEndDateInput.clear();
		supportEndDateInput.sendKeys(todate);
		saveBtn.click();
	}
	
	public void creatingNewContactWithOrgName(String LastName,String orgName) throws InterruptedException {
		LastnameInput.sendKeys(LastName);
		orgLinkIcon.click();
		WebDriverUtility wu = new WebDriverUtility();
		wu.switchNewBrowserTab(driver, "module=Accounts");
		OrganizationPopUpPage op = new OrganizationPopUpPage(driver);
		op.getSearchInput().sendKeys(orgName);
		op.getSeachBtn().click();
		System.out.println("Before clicking ");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		System.out.println("After clicking");
		wu.switchNewBrowserTab(driver, "module=Contacts");
		saveBtn.click();
		
	}
}
