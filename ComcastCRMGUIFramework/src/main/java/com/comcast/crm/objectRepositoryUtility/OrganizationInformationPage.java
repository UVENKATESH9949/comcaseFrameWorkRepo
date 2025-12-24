package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	WebDriver driver;
	public OrganizationInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Organization Information')]")
	private WebElement header;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement indutry;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement phonereview;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	
	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getHeader() {
		return header;
	}

	public WebElement getIndutry() {
		return indutry;
	}

	public WebElement getPhonereview() {
		return phonereview;
	}
	
	
	
}
