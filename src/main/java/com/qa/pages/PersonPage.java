package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class PersonPage extends Base{
	
	@FindBy(xpath="//div[@class='page-header-left']//span[text()='People & Organisations']")
	WebElement personTxt;
	
	@FindBy(xpath="//a[text()='Add Person']")
	WebElement addPersonBtn;
	
	@FindBy(id="party:fnDecorate:fn")
	WebElement firstNameTxt;
	
	@FindBy(id="party:lnDecorate:ln")
	WebElement lastNameTxt;
	
	@FindBy(id="party:roleDecorate:jobTitle")
	WebElement jobTitleTxt;
	
	@FindBy(id="party:orgDecorate:org")
	WebElement orgTxt;
	
	@FindBy(id="party:tagsDecorate:tagComboBox")
	WebElement tagsTxt;
	
	@FindBy(id="party:j_id325:0:phnDecorate:number")
	WebElement phoneTxt;
	
	@FindBy(id="party:j_id342:0:emlDecorate:nmbr")
	WebElement emailTxt;
	
	@FindBy(id="party:save")
	WebElement saveBtn;
	
	@FindBy(xpath="//span[@class='party-details-title']")
	WebElement addedPersonTxt;
	
	@FindBy(xpath="//div[@class='list-actions-bulk']//child::span[contains(@class,'btn-small list-actions-delete')]")
	WebElement smallDeleteIcon;
	
	@FindBy(xpath="//div[@class='modal-dialog-box']//child::input[contains(@class,'bulk-delete-modal-checkbox')]")
	WebElement checkBoxInsideDelete;
	
	@FindBy(xpath="//footer[@class='modal-dialog-footer']//child::button[contains(@class,'btn-danger')]")
	WebElement personDeleteBtn;
	
	String dynamicPersonXpath="//table[@class='simple-table list-results-table with-hover-effect']//child::td/a[contains(text(),'%replaceable%')]";	
	
	
	public PersonPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getPersonTxt() {
		Utils.checkVisibilityWithExplicitWait(driver,personTxt, Utils.EXPLICIT_TIMEOUT);
		return personTxt.getText();
	}

	public void createNewPerson(String title, String fName, String lName, String jobTitle, String org, String tags,
			String phone, String email) {
		Select titleObj = new Select(driver.findElement(By.name("party:j_id108:j_id116")));
		titleObj.selectByVisibleText(title);
		Utils.sendKeysWithExplicitWait(driver, firstNameTxt, Utils.EXPLICIT_TIMEOUT, fName);
		Utils.sendKeysWithExplicitWait(driver, lastNameTxt, Utils.EXPLICIT_TIMEOUT, lName);
		Utils.sendKeysWithExplicitWait(driver, jobTitleTxt, Utils.EXPLICIT_TIMEOUT, jobTitle);
		Utils.sendKeysWithExplicitWait(driver, orgTxt, Utils.EXPLICIT_TIMEOUT, org);
		Utils.sendKeysWithExplicitWait(driver, tagsTxt, Utils.EXPLICIT_TIMEOUT, tags);		
		Utils.sendKeysWithExplicitWait(driver, phoneTxt, Utils.EXPLICIT_TIMEOUT, phone);
		Utils.sendKeysWithExplicitWait(driver, emailTxt, Utils.EXPLICIT_TIMEOUT, email);
		
		Utils.clickWithExplicitWait(driver, saveBtn, Utils.EXPLICIT_TIMEOUT);
	}

	public void clickOnAddPerson() {
		Utils.clickWithExplicitWait(driver, addPersonBtn, Utils.EXPLICIT_TIMEOUT);
	}
	
	public String verifyAddedPerson(){
		Utils.checkVisibilityWithExplicitWait(driver, addedPersonTxt, Utils.EXPLICIT_TIMEOUT);
		return addedPersonTxt.getText();
	}
	
	public void deleteIfExistsPerson(String fName, String lName) {
		String runtimeXpath = Utils.createDynamicXpath(dynamicPersonXpath, fName+" "+lName);
		String checkBoxXpath = runtimeXpath + "//parent::td/preceding-sibling::td[@class='list-results-cell is-checkbox']";
		try {
			Utils.clickWithExplicitWait(driver, driver.findElement(By.xpath(checkBoxXpath)), Utils.EXPLICIT_TIMEOUT);
			Utils.clickWithExplicitWait(driver, smallDeleteIcon, Utils.EXPLICIT_TIMEOUT);
			Utils.clickWithExplicitWait(driver, checkBoxInsideDelete, Utils.EXPLICIT_TIMEOUT);
			Utils.clickWithExplicitWait(driver, personDeleteBtn, Utils.EXPLICIT_TIMEOUT);
			Thread.sleep(Utils.SLEEP_TIME);
		} catch (Exception e) {
			logger.info("Creating new Person");
		}
	}
}
