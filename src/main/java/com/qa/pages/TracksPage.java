package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class TracksPage extends Base{
	
	@FindBy(css ="h2[class='settings-page-header']")
	WebElement trackHeaderTxt;
	
	@FindBy(xpath="//a[contains(text(),'Add new Track')]")
	WebElement addNewTrackBtn;
	
	@FindBy(css ="input[id$='trackDescription']")
	WebElement trackNameTxt;
	
	@FindBy(css="input[id$='taskDescription']")
	WebElement taskNameTxt;
	
	@FindBy(css="a.btn-primary.btn-clear.singlesubmit")
	WebElement btnSave;
	
	@FindBy(xpath="//div[@id='deleteTrackTemplateModal']//child::input[@class='btn-danger' and @value='Delete track']")
	WebElement deleteTrackBtn;
	
	String dynamicTrackXpath = "//table[@class='recordList']//child::a[contains(text(),'%replaceable%')]";
	
	public TracksPage() {
		PageFactory.initElements(driver, this);
	}

	public String getTracksHeaderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, trackHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return trackHeaderTxt.getText();
	}

	public void addTrack(String trackName, String taskDescription) {
		Utils.clickWithExplicitWait(driver, addNewTrackBtn, Utils.EXPLICIT_TIMEOUT);
		
		Utils.sendKeysWithExplicitWait(driver, trackNameTxt, Utils.EXPLICIT_TIMEOUT, trackName);
		Utils.sendKeysWithExplicitWait(driver, taskNameTxt, Utils.EXPLICIT_TIMEOUT, taskDescription);
		
		Utils.clickWithExplicitWait(driver, btnSave, Utils.EXPLICIT_TIMEOUT);
	}

	public String verifyCreatedTrack(String trackName) {
		String runtimeXpath = Utils.createDynamicXpath(dynamicTrackXpath, trackName);
		try {
			Thread.sleep(Utils.SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver.findElement(By.xpath(runtimeXpath)).getText();
	}
	
	public void deleteIfExistsTrack(String trackName) {
		String runtimeXpath = Utils.createDynamicXpath(dynamicTrackXpath, trackName);
		String deleteBtnXpath = runtimeXpath + "//parent::td/following-sibling::td[3]/a";
		try {
			Utils.clickWithExplicitWait(driver, driver.findElement(By.xpath(deleteBtnXpath)), Utils.EXPLICIT_TIMEOUT);
			Utils.clickWithExplicitWait(driver, deleteTrackBtn, Utils.EXPLICIT_TIMEOUT);
			Thread.sleep(Utils.SLEEP_TIME);
		} catch (Exception e) {
			logger.info("Creating new Track");
		}
	}
}
