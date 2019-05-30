package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class TagsPage extends Base{
	@FindBy(xpath="//div[@class='settings-page-content']//h2[contains(text(),'Tags and DataTags')]")
	WebElement tagsHeaderTxt;
	
	@FindBy(xpath="//div[@class='col padded-right']//child::a[contains(text(),'Add new Tag')]")
	WebElement addNewTagBtn;
	
	@FindBy(xpath="//div[@id='editTagModal']//child::input[contains(@id,'tagName')]")
	WebElement tagNameTxt;
	
	@FindBy(xpath="//input[@class='btn-primary singlesubmit' and @value='Save']")
	WebElement tagSaveBtn;
	
	@FindBy(xpath="//div[@id='deleteTagModal']//child::input[@class='btn-danger singlesubmit' and @value='Delete tag']")
	WebElement deleteTagBtn;
	
	String dynamicTagXpath = "//table[@class='recordList']//child::a[text()='%replaceable%']";
	
	public TagsPage() {
		PageFactory.initElements(driver,this);
	}

	public String getTagsHeaderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, tagsHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return tagsHeaderTxt.getText();
	}

	public void deleteIfExistsTag(String tagName) {
		String runtimeXpath = Utils.createDynamicXpath(dynamicTagXpath, tagName);
		String deleteBtnXpath = runtimeXpath + "//parent::span//parent::td/following-sibling::td[2]/a";
		try {
			Utils.clickWithExplicitWait(driver, driver.findElement(By.xpath(deleteBtnXpath)), Utils.EXPLICIT_TIMEOUT);
			Utils.clickWithExplicitWait(driver, deleteTagBtn, Utils.EXPLICIT_TIMEOUT);
			Thread.sleep(Utils.SLEEP_TIME);
		} catch (Exception e) {
			logger.info("Creating new Tag");
		}		
	}

	public void addTag(String tagName) {
		Utils.clickWithExplicitWait(driver, addNewTagBtn, Utils.EXPLICIT_TIMEOUT);
		Utils.sendKeysWithExplicitWait(driver, tagNameTxt, Utils.EXPLICIT_TIMEOUT, tagName);
		Utils.clickWithExplicitWait(driver, tagSaveBtn, Utils.EXPLICIT_TIMEOUT);
	}

	public String verifyCreatedTag(String tagName) {
		String runtimeXpath = Utils.createDynamicXpath(dynamicTagXpath, tagName);
		try {
			Thread.sleep(Utils.SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver.findElement(By.xpath(runtimeXpath)).getText();
	}

}
