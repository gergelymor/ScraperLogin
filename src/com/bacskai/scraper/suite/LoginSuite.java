package com.bacskai.scraper.suite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.bacskai.scraper.pageobjects.LoginPage;
import com.bacskai.scraper.pageobjects.SuccessorPage;

public class LoginSuite {
	
	private WebDriver driver;
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.get("http://testing-ground.scraping.pro/login");
	}
	
	@Test
	public void testLoginWithCorrectCredentials(){
		String username = "admin";
		String password = "12345";
		String successMsg = "WELCOME :)";
		LoginPage lpage = new LoginPage(driver); 
		lpage.setUsername(username);
		lpage.setPassword(password);
		SuccessorPage spage = lpage.loginAction();
		Assert.assertTrue(spage.getPageMessage().equals(successMsg));
		spage.goBackToLogin();
	}
	
	@Test
	public void testLoginWithIncorrectCredentials(){
		String username = "fakeuser";
		String password = "fakepass";
		String deniedMsg = "ACCESS DENIED!";
		LoginPage lpage = new LoginPage(driver); 
		lpage.setUsername(username);
		lpage.setPassword(password);
		SuccessorPage spage = lpage.loginAction();
		Assert.assertTrue(spage.getPageMessage().equals(deniedMsg));
		spage.goBackToLogin();
	}
	
	@Test
	public void testNoCookies(){
		String username = "admin";
		String password = "12345";
		String missingCookieMsg = "THE SESSION COOKIE IS MISSING OR HAS A WRONG VALUE!";
		LoginPage lpage = new LoginPage(driver); 
		lpage.setUsername(username);
		lpage.setPassword(password);
		SuccessorPage spage = lpage.loginAction();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		Assert.assertTrue(spage.getPageMessage().equals(missingCookieMsg));
		spage.goBackToLogin();
	}
	
	@After
	public void tearDown(){
		driver.close();
	}

}
