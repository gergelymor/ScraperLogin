package com.bacskai.scraper.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private By usernameField = By.id("usr");
	private By passwordField = By.id("pwd");
	private By loginBtn = By.cssSelector("input[value=Login]");
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}
	
	private void fillValueInTextField(By element, String value){
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(value);
	}
	
	public void setUsername(String username){
		fillValueInTextField(usernameField, username);
	}
	
	public void setPassword(String password){
		fillValueInTextField(passwordField, password);
	}
	
	public SuccessorPage loginAction(){
		driver.findElement(loginBtn).click();
		wait.until(ExpectedConditions.urlContains("?mode="));
		return new SuccessorPage(driver);
	}
}
