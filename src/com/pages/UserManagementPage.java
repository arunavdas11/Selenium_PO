package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.base.Wrapper;

//Page objects and methods for user management page
public class UserManagementPage extends Wrapper{
	
	@FindBy(xpath = "//input[@id='btnSave']")
	public  WebElement btnSave;
	
	@FindBy(xpath = "//*[@id='systemUser_status' or @id='status']")
	public  WebElement dropDownStatus;
	
	@FindBy(xpath = "//a[@id='menu_admin_viewAdminModule']")
	public  WebElement linkAdmin;

	@FindBy(xpath = "//a[@id='menu_admin_UserManagement']")
	public  WebElement linkUserManagement;

	@FindBy(xpath = "//a[@id='menu_admin_viewSystemUsers']")
	public  WebElement linkUsers;

	@FindBy(xpath = "//input[@value='Edit']")
	public  WebElement btnEdit;
	
	//Enables a user. Returns true on success and false if unsuccessful
	public  boolean EnableUser(String uName){
		waitForElementTobeClickable(linkAdmin);
		linkAdmin.click();
		waitForElementTobeClickable(linkUserManagement);
		linkUserManagement.click();
		waitForElementTobeClickable(linkUsers);
		linkUsers.click();
		try{
			WebElement userLink = driver.findElement(By.xpath("//*[@id='resultTable']//a[text()='"+uName+"']"));
			userLink.click();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		
		btnEdit.click();
		selectDropdown(dropDownStatus, "Enabled");
		btnSave.click();
		return true;
	}
	

}
