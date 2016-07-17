package com.base;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.UserManagementPage;
import com.util.Utils;


// Base class for all tests
public class TestBase extends Wrapper{
	//public String browser = "FF";
	public static Utils util = new Utils();
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected UserManagementPage userManagementPage;

	// Opens browser and navigates to site url as defined in the testng.zml file
	@Parameters({"browser", "siteURL"})
	@BeforeTest(alwaysRun = true)
	public void setUp(String browser, String siteURL) throws MalformedURLException {
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} 
		else if (browser.equalsIgnoreCase("ie")) {
			String path = new File("libs\\IEDriverServer.exe").getAbsolutePath();
			File file = new File(path);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver();
		}

		loginPage = PageFactory.initElements(driver, LoginPage.class);
		homePage = PageFactory.initElements(driver, HomePage.class); 
		userManagementPage = PageFactory.initElements(driver, UserManagementPage.class); 
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		openUrl(siteURL);
		

	}

	//Closes webdriver
	@AfterTest
	public void tearDown() {
		
		driver.quit();
	}



}
