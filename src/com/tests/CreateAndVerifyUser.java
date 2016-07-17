package com.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.base.TestBase;


public class CreateAndVerifyUser extends TestBase{
	
	@DataProvider(name = "testData")
	 
	  public Object[][] credentials() {
	 
	        return new Object[][] { { "AdminOH", "adminoh", "test first name", "test last name", "testuser1", "testpass1", "Disabled" }};
	 
	  }
	
	@Test(dataProvider = "testData")
	public void VerifyAddNewEmployee(String userName, String passWord, String newUserFname, String newUserLname, String newUserName, String newUserPass, String newUserStatus) throws InterruptedException{
		//Login to application
		boolean loginStatus = loginPage.login(userName, passWord);
		Assert.assertEquals(loginStatus, true, "Failed to login with admin user"+userName);
		
		//Create a new employee
		boolean addEmpStatus = homePage.AddNewEmployee(newUserFname, newUserLname, newUserName, newUserPass, newUserStatus);
		Assert.assertEquals(addEmpStatus, true,"Failed to create new employee"+newUserFname+" "+newUserLname);
		
		//Logout as admin user
		boolean logoutStatus =loginPage.logout();
		Assert.assertEquals(logoutStatus, true, "Failed to logout");
		
		//Try to login with newly created user
		boolean newUserLoginStatus = loginPage.login(newUserName, newUserPass);
		Assert.assertEquals(newUserLoginStatus, false, "Able to login with new user credentials +newUserName");
		
		//Login as admin user
		boolean reloginStatus = loginPage.login(userName, passWord);
		Assert.assertEquals(reloginStatus, true, "Failed to login");
		
		//Enable newly created user login
		boolean enableUserStatus = userManagementPage.EnableUser(newUserName);
		Assert.assertEquals(enableUserStatus, true, "Failed to enable user "+newUserName);
		
		//Logout as admin
		boolean logout2Status = loginPage.logout();
		Assert.assertEquals(logout2Status, true, "Failed to logout");
		
		//Login as newly created user
		boolean login2Status = loginPage.login(userName, passWord);
		Assert.assertEquals(login2Status, true, "Unable to login with admin credentials "+userName);
		
		//Delete the created user
		cleanup(userName, passWord,newUserName,newUserFname);
	}
	
	//Deletes the created user
	public void cleanup(String userName, String passWord,String newUserName, String newUserFname){
		
		boolean logoutStatus = loginPage.logout();
		Assert.assertEquals(logoutStatus, true, "Failed to logout");
		
		boolean loginStatus = loginPage.login(userName, passWord);
		Assert.assertEquals(loginStatus, true, "Failed to login");
		
		boolean deleteUserStatus = homePage.deleteUser(newUserFname);
		Assert.assertEquals(deleteUserStatus, true, "Unable to delete created user as a part of cleanup process");
		
	}
}
