package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
 
	WebDriver driver;
	public OrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement newOrgLink;

	@FindBy(name = "search_text")
	private WebElement searchInput;
	
	@FindBy(name = "search_field")
	private WebElement searchType;
	
	@FindBy(name = "submit")
	private WebElement searchBtn;
	
	
	public WebElement getSearchInput() {
		return searchInput;
	}


	public WebElement getSearchType() {
		return searchType;
	}


	public WebElement getSearchBtn() {
		return searchBtn;
	}


	public WebElement getNewOrgLink() {
		return newOrgLink;
	}
	
	
}
