package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.Wrapper;

//Page objects and methods for login page
public class LoginPage extends Wrapper{
	@FindBy(xpath = "//input[@id='txtUsername']")
	public  WebElement txtUserName;

	@FindBy(xpath = "//input[@id='txtPassword']")
	public WebElement txtPassword;

	@FindBy(xpath = "//input[@id='btnLogin']")
	public WebElement btnLogin;
	
	@FindBy(xpath = "//a[@id='welcome']")
	public  WebElement linkWelcome;
	
	@FindBy(xpath = "//div[@id='welcome-menu']//a[text()='Logout']")
	public  WebElement linkLogout;
	
	//Logs in to site. Returns true on success and false if unsuccessful
	public boolean login(String strUserName, String strPassword) {
		waitForElementTobeClickable(txtUserName);
		txtUserName.sendKeys(strUserName);
		txtPassword.sendKeys(strPassword);
		btnLogin.click();
		try{
			if(linkWelcome.isDisplayed()){
				return true;
			}
			return false;
		}
		catch(Exception e){
			return false;
		}
	}
	
	//Logs out of the site. Returns true on success and false if unsuccessful
	public boolean logout(){
		linkWelcome.click();
		waitForElementTobeClickable(linkLogout);
		linkLogout.click();
		if(txtUserName.isDisplayed()){
			return true;
		}
		return false;	
	}

}
