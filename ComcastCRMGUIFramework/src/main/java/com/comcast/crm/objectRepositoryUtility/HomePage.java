package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Calendar")
	private WebElement CalendarpageLink;
	
	@FindBy(linkText = "Organizations")
	private WebElement orgpageLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement ContactpageLink;
	
	@FindBy(linkText = "Opportunities")
	private WebElement OpportunitiespageLink;
	
	@FindBy(linkText = "Products")
	private WebElement productpageLink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement usericon;

	@FindBy(partialLinkText  = "Sign Out")
	private WebElement logoutLink;
	
	public WebElement getCalendarpageLink() {
		return CalendarpageLink;
	}

	public WebElement getOrgpageLink() {
		return orgpageLink;
	}

	public WebElement getContactpageLink() {
		return ContactpageLink;
	}

	public WebElement getOpportunitiespageLink() {
		return OpportunitiespageLink;
	}

	public WebElement getProductpageLink() {
		return productpageLink;
	}

	public WebElement getUsericon() {
		return usericon;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}
	
	public void logout() {
		Actions action = new Actions(driver);
		action.moveToElement(usericon).perform();
		logoutLink.click();
	}
}
