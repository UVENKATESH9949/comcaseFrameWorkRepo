package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {

	WebDriver driver;
	public ContactInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[contains(text(),'Contact Information')]")
	private WebElement header;
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement startDate;
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement endDate;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement orgName;
	
	public WebElement getHeader() {
		return header;
	}


	public WebElement getStartDate() {
		return startDate;
	}


	public WebElement getEndDate() {
		return endDate;
	}


	public WebElement getOrgName() {
		return orgName;
	}
	
	
}
