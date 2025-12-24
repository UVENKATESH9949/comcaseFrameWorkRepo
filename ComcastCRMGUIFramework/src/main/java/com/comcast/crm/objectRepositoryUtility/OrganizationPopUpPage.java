package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPopUpPage {

	WebDriver driver;
	public OrganizationPopUpPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(name = "search_text")
	private WebElement searchInput;
	
	@FindBy(name = "search")
	private WebElement seachBtn;
	
	public WebElement getSearchInput() {
		return searchInput;
	}

	public WebElement getSeachBtn() {
		return seachBtn;
	}
	
	
}
