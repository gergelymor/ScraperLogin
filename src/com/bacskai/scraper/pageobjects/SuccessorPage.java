package com.bacskai.scraper.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessorPage {
	
	private WebDriver driver;
	private By message = By.xpath("//div[contains(@id, 'case_login')]/h3");
	private By goBackBtn = By.xpath("//a[@href='login']");
	
	public SuccessorPage(WebDriver driver){
		this.driver = driver;
	}
	
	public String getPageMessage(){
		return driver.findElement(message).getText();
	}
	
	public LoginPage goBackToLogin(){
		driver.findElement(goBackBtn).click();
		return new LoginPage(driver);
	}

}
