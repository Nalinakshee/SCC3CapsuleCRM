package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class UsersPage extends Base{
	
	@FindBy(xpath = "//div[@class='settings-page-content']//child::h2[text()='Users']")
	WebElement usersHeaderTxt;
	
	@FindBy(xpath="//div[@class='settings-page-content']//child::a[text()='Add new User']")
	WebElement btnAddUser;
	
	@FindBy(id="register:firstnameDecorate:firstName")
	WebElement firstNameTxt;
	
	@FindBy(id="register:lastNameDecorate:lastName")
	WebElement lastNameTxt;
	
	@FindBy(id="register:emailDecorate:email")
	WebElement emailTxt;
	
	@FindBy(id="register:usernameDecorate:username")
	WebElement userNameTxt;
	
	@FindBy(id="register:save")
	WebElement inviteUser;	
	
	@FindBy(xpath="//div[@class='preferences-user-panel']//a[contains(text(),'Delete user')]")
	WebElement deleteUserLink;
	
	@FindBy(xpath="//div[@id='deleteUserConfirm']//a[contains(text(),'Delete')]")
	WebElement deleteBtn;
	
	String dynamicUserStrXpath = "//div[@class='formsection']//child::td//a[contains(text(),'%replaceable%')]";
	
	public UsersPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getUsersHearderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, usersHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return usersHeaderTxt.getText();
	}
	
	public void addNewUser(String fName, String lName, String eMail, String userName) {
		Utils.clickWithExplicitWait(driver, btnAddUser, Utils.EXPLICIT_TIMEOUT);
		
		Utils.sendKeysWithExplicitWait(driver, firstNameTxt, Utils.EXPLICIT_TIMEOUT, fName);
		Utils.sendKeysWithExplicitWait(driver, lastNameTxt, Utils.EXPLICIT_TIMEOUT, lName);
		Utils.sendKeysWithExplicitWait(driver, emailTxt, Utils.EXPLICIT_TIMEOUT, eMail);
		Utils.sendKeysWithExplicitWait(driver, userNameTxt, Utils.EXPLICIT_TIMEOUT, userName);
		
		Utils.clickWithExplicitWait(driver, inviteUser, Utils.EXPLICIT_TIMEOUT);
	}
	
	public String verifyCreatedUser(String name) {
		String runtimeXpath = Utils.createDynamicXpath(dynamicUserStrXpath, name);
		//Utils.checkVisibilityWithExplicitWait(driver, driver.findElement(By.xpath(runtimeXpath)), Utils.EXPLICIT_TIMEOUT);
		try {
			Thread.sleep(Utils.SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver.findElement(By.xpath(runtimeXpath)).getText();
	}
	
	public void deleteExistedUser(String name) {
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='recordList']//tbody//tr"));		
		int rowsSize = rows.size();
		if(rowsSize>1) {
			logger.info("More than one user exists, deleting one");
			String runtimeXpath = Utils.createDynamicXpath(dynamicUserStrXpath, name);
			Utils.clickWithExplicitWait(driver, driver.findElement(By.xpath(runtimeXpath)), Utils.EXPLICIT_TIMEOUT);
			
			Utils.clickWithExplicitWait(driver, deleteUserLink, Utils.EXPLICIT_TIMEOUT);
			
			Utils.clickWithExplicitWait(driver, deleteBtn, Utils.EXPLICIT_TIMEOUT);
			
		} else {
			logger.info("Creating New User");
		}
	}

}
