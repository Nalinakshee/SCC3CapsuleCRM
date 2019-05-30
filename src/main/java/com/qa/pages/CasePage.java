package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class CasePage extends Base{
	
	@FindBy(xpath="//div[@class='page-header-right']/a[text()='Add Case']")
	WebElement addCaseBtn;
	
	@FindBy(id="partySearch")
	WebElement searchTxt;
	
	@FindBy(id="caseNameDecorate:name")
	WebElement nameTxt;
	
	@FindBy(id="caseDescriptionDecorate:description")
	WebElement descriptionTxt;
	
	@FindBy(id="tagsDecorate:tagComboBox")
	WebElement tagsTxt;
	
	@FindBy(id="save")
	WebElement saveBtn;
	
	@FindBy(xpath="//span[@class='kase-summary-status-open']")
	WebElement caseStatus;
	
	String caseForPersonXpath = "//div[@class='panel']//child::div[@class='entity-details-party']//child::a[text()='%replaceable%']";
	
	public CasePage() {
		PageFactory.initElements(driver, this);
	}

	public void createNewCase(String caseRelateTo, String name, String description, String tags) {
		Utils.sendKeysWithExplicitWait(driver, searchTxt, Utils.EXPLICIT_TIMEOUT, caseRelateTo);
		try {
			Thread.sleep(Utils.SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(searchTxt).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
				
		Utils.sendKeysWithExplicitWait(driver, nameTxt, Utils.EXPLICIT_TIMEOUT, name);
		Utils.sendKeysWithExplicitWait(driver, descriptionTxt, Utils.EXPLICIT_TIMEOUT, description);
		Utils.sendKeysWithExplicitWait(driver, tagsTxt, Utils.EXPLICIT_TIMEOUT, tags);
		
		Utils.clickWithExplicitWait(driver, saveBtn, Utils.EXPLICIT_TIMEOUT);
	}

	public void clickOnAddCase() {
		Utils.clickWithExplicitWait(driver, addCaseBtn, Utils.EXPLICIT_TIMEOUT);
	}
	
	public String verifyCaseForPerson(String name) {
		String runtimeXpath = Utils.createDynamicXpath(caseForPersonXpath, name);
		//Utils.checkVisibilityWithExplicitWait(driver, driver.findElement(By.xpath(runtimeXpath)), Utils.EXPLICIT_TIMEOUT);
		try {
			Thread.sleep(Utils.SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver.findElement(By.xpath(runtimeXpath)).getText();
	}
	
	public String verifyCaseStatus() {
		Utils.checkVisibilityWithExplicitWait(driver, caseStatus, Utils.EXPLICIT_TIMEOUT);
		return caseStatus.getText();
	}
}
