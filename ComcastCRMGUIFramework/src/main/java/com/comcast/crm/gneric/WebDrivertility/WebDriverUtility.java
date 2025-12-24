package com.comcast.crm.gneric.WebDrivertility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public WebDriver luachBrowser(String browser) {

		if(browser.equals("firefox")) {
			return new FirefoxDriver();
		}
		
		else if(browser.equals("chrome")) {
			return new ChromeDriver();
		}
		
		else if(browser.equals("edge")) {
			return  new EdgeDriver();
		}
		else {
			return  new ChromeDriver();
		}
		
		
	}
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementPresent(WebElement ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void switchNewBrowserTab(WebDriver driver, String partailUrl) {

		Set<String> set = driver.getWindowHandles();
		for(String window : set) {
			driver.switchTo().window(window);
			String currURL = driver.getCurrentUrl();
			
			if(currURL.contains(partailUrl)) break;
		}
	
	}
	
	public void switchToTabTittle(WebDriver driver, String partailTitle) {
		Set<String> set = driver.getWindowHandles();
		for(String window : set) {
			driver.switchTo().window(window);
			String curuTitle = driver.getTitle();
			
			if(curuTitle.contains(partailTitle)) break;
		}
	}
	
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	public void swithchToFrame(WebDriver driver, String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	
	public void switchToFrame(WebDriver driver, WebElement ele) {
		driver.switchTo().frame(ele);
	}
	
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndReject(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void select(WebElement ele, String text) {
		Select select = new Select(ele);
		select.selectByContainsVisibleText(text);
	}
	
	
	public void select(WebElement ele, int index) {
		Select select = new Select(ele);
		select.selectByIndex(index);
	}
	
	public void mouseOverOnElement(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();;
	}
	
	public void doubleClick(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.doubleClick().perform();;
	}
}


