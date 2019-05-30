package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class OpportunitiesPage extends Base {

	@FindBy(xpath = "//div[@class='page-box']//header[text()='Opportunities']")
	WebElement opporHeaderTxt;

	@FindBy(xpath = "//div[@class='page-box-actions']//button[@class='btn-primary']")
	WebElement addMileStoneBtn;

	@FindBy(css = "input[class*='milestone-modal-name']")
	WebElement mileStnName;

	@FindBy(css = "textarea[class*='milestone-modal-description']")
	WebElement mileStnDesc;

	@FindBy(css = "input[class*='milestone-modal-probability']")
	WebElement milestnProb;

	@FindBy(css = "input[class*='milestone-modal-days-until-stale']")
	WebElement daysUntilStale;

	@FindBy(xpath = "//button[@type='submit' and text()='Save']")
	WebElement btnSave;

	/*
	 * @FindBy(
	 * xpath="//table[@class='record-list']//tbody//tr//button[@class='hyperlink-button milestone-item-delete']"
	 * ) WebElement mileStoneDeleteLink;
	 */

	@FindBy(xpath = "//div[@class='modal-dialog-box']//child::button[text()='Delete']")
	WebElement milestoneDeleteBtn;

	/*
	 * @FindBy(css="div.flash-message-body") WebElement flashMsg;
	 */

	@FindBy(xpath = "//footer[@class='modal-dialog-footer']//button[text()='Cancel']")
	WebElement cancelBtn;

	String dynamicMileStoneStr = "//button[@class='hyperlink-button milestone-item-edit' and text()='%replaceable%']";

	public OpportunitiesPage() {
		PageFactory.initElements(driver, this);
	}

	public String getOpporHeaderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, opporHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return opporHeaderTxt.getText();
	}

	public void addMileStone(String name, String description, String probability, String days) {
		Utils.clickWithExplicitWait(driver, addMileStoneBtn, Utils.EXPLICIT_TIMEOUT);

		Utils.sendKeysWithExplicitWait(driver, mileStnName, Utils.EXPLICIT_TIMEOUT, name);
		Utils.sendKeysWithExplicitWait(driver, mileStnDesc, Utils.EXPLICIT_TIMEOUT, description);
		Utils.sendKeysWithExplicitWait(driver, milestnProb, Utils.EXPLICIT_TIMEOUT, probability);
		Utils.sendKeysWithExplicitWait(driver, daysUntilStale, Utils.EXPLICIT_TIMEOUT, days);

		Utils.clickWithExplicitWait(driver, btnSave, Utils.EXPLICIT_TIMEOUT);

		// Click on Cancel Button if Milestone already exists
		if (Utils.checkVisOfElementLocatedWithExWait(driver,
				By.xpath("//div[@class='modal-dialog-box']//div[@class='flash-message-body']"),
				Utils.EXPLICIT_TIMEOUT)) {
			Utils.clickWithExplicitWait(driver, cancelBtn, Utils.EXPLICIT_TIMEOUT);
		}
	}

	public String verifyCreatedMileStone(String name) {
		String runtimeXpath = Utils.createDynamicXpath(dynamicMileStoneStr, name);
		try {
			Thread.sleep(Utils.SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver.findElement(By.xpath(runtimeXpath)).getText();
	}

	public void deleteIfMileStoneExist(String name) {
		String runtimeXpath = Utils.createDynamicXpath(dynamicMileStoneStr, name);
		List<WebElement> listOfMileStones = driver.findElements(By.xpath(
				"//table[@class='record-list']//tbody//tr//button[@class='hyperlink-button milestone-item-edit']"));
		List<WebElement> listOfDeleteLinks = driver.findElements(By.xpath(
				"//table[@class='record-list']//tbody//tr//button[@class='hyperlink-button milestone-item-delete']"));
		WebElement mileStone = driver.findElement(By.xpath("//table[@class='record-list']//tbody//tr" + runtimeXpath));
		for (int i = 0, j = 0; i < listOfMileStones.size(); i++, j++) {
			if (listOfMileStones.get(i).getText().equals(mileStone.getText())) {
				WebElement deleteBtnLink = listOfDeleteLinks.get(j);
				Utils.clickWithExplicitWait(driver, deleteBtnLink, Utils.EXPLICIT_TIMEOUT);
				Utils.clickWithExplicitWait(driver, milestoneDeleteBtn, Utils.EXPLICIT_TIMEOUT);
				break;
			}
		}

	}
}
