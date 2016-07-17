package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//Wrapper class for common actions
public class Wrapper {
protected static WebDriver driver;
	
	public void click(WebElement element){
		element.click();
	}
	public void openUrl(String url){
		driver.get(url);
	}
	
	public void type(WebElement element, String value){
		element.clear();
		element.sendKeys(value);
	}
	
	public static void selectDropdown(WebElement element, String value){
		new Select(element).selectByVisibleText(value);
	}
	
	public void selectCheckbox(WebElement element){
		if (!element.isSelected()){
		     element.click();
		}
	}
	
	public void unselectCheckbox(WebElement element){
		if(element.isSelected()){
			element.click();
		}
	}
	
	public String getText(WebElement element){
		String a = element.getText();
		return a;
	}
	
	
	public static void waitForElementTobeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
