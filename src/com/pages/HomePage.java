package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.base.Wrapper;

//Page objects and methods for Home page
public class HomePage extends Wrapper{
	@FindBy(xpath = "//input[@id='btnAdd']")
	public  WebElement btnAdd;
	
	@FindBy(xpath = "//input[@id='firstName']")
	public  WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@id='lastName']")
	public  WebElement txtLastName;

	@FindBy(xpath = "//input[@id='chkLogin']")
	public  WebElement checkboxLogin;
	
	@FindBy(xpath = "//input[@id='btnSave']")
	public  WebElement btnSave;
	
	@FindBy(xpath = "//input[@id='user_name']")
	public  WebElement txtUserNameField;
	
	@FindBy(xpath = "//input[@id='user_password']")
	public  WebElement txtPasswordField;
	
	@FindBy(xpath = "//input[@id='re_password']")
	public  WebElement txtReEnterPasswordField;

	@FindBy(xpath = "//input[@id='personal_txtEmpFirstName']")
	public  WebElement txtEmpDetailFname;
	
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
	
	@FindBy(xpath = "//input[@id='btnDelete']")
	public  WebElement btnDelete;

	@FindBy(xpath = "//input[@id='dialogDeleteBtn']")
	public  WebElement btnDeleteConfirm;
	
	//Adds a new employee and returns true on success and false if unsuccessful
	public  boolean AddNewEmployee(String fName, String lName, String uName, String pass, String status) {
		waitForElementTobeClickable(btnAdd);
		btnAdd.click();
		txtFirstName.sendKeys(fName);
		txtLastName.sendKeys(lName);
		checkboxLogin.click();
		waitForElementTobeClickable(txtUserNameField);
		txtUserNameField.sendKeys(uName);
		txtPasswordField.sendKeys(pass);
		txtReEnterPasswordField.sendKeys(pass);
		selectDropdown(dropDownStatus,status);
		
		btnSave.click();
		try{
			if(txtEmpDetailFname.getAttribute("value").equalsIgnoreCase(fName)){
				return true;
			}
			else{
				return false;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	//Deletes a user from the system, based on username. Returns true on success and false if unsuccessful 
	public boolean deleteUser(String userName){
		try{
			WebElement userChk = driver.findElement(By.xpath("//*[@id='resultTable']//a[text()='"+userName+"']/../..//input[@type='checkbox']"));
			userChk.click();
			btnDelete.click();
			waitForElementTobeClickable(btnDeleteConfirm);
			btnDeleteConfirm.click();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
		
	}
}
