package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage{

	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement orgnameedit;
	
	@FindBy(name = "ship_street")
	private WebElement shippingAddress;

	@FindBy(name = "button")
	private WebElement savebtn;
	
	@FindBy(name = "industry")
	private WebElement industryselect;
	
	@FindBy(id = "phone")
	private WebElement phoneedit;
	
	public WebElement getOrgname() {
		return orgnameedit;
	}

	public WebElement getShippingAddress() {
		return shippingAddress;
	}
	
	
	public WebElement getSavebtn() {
		return savebtn;
	}

	
	public WebElement getIndustryselect() {
		return industryselect;
	}

	
	public WebElement getPhoneedit() {
		return phoneedit;
	}

	public void createOrg(String orgname, String shippingAdd) {
		orgnameedit.sendKeys(orgname);
		shippingAddress.sendKeys(shippingAdd);
		savebtn.click();
	}
	
	public void createOrg(String orgname , String shippingAdd, String industry) {
		orgnameedit.sendKeys(orgname);
		shippingAddress.sendKeys(shippingAdd);
		
		Select select = new Select(industryselect);
		select.selectByContainsVisibleText(industry);
		
		savebtn.click();
		
	}
	
	public void createOrgWithPhone(String orgname , String phone, String shippingAdd) {
		orgnameedit.sendKeys(orgname);
		shippingAddress.sendKeys(shippingAdd);
		
		phoneedit.sendKeys(phone);
		
		savebtn.click();
		
	}
}
