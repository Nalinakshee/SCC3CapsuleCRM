package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class TaskCatPage extends Base {

	@FindBy(xpath = "//div[@class='settings-page-content']//h2[@class='settings-page-header']")
	WebElement taskCatHeaderTxt;

	@FindBy(xpath = "//div[@class='section']//a[contains(text(),'Add new Category')]")
	WebElement addNewCatBtn;

	@FindBy(xpath = "//div[@id='editCategoryModal']//child::input[@id='editCategoryForm:taskCategoryNameDecorate:taskCategoryName']")
	WebElement taskCatNameTxt;

	@FindBy(xpath = "//footer[@class='panel-footer-modal']//child::input[@class='btn-primary' and @value='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//footer[@class='panel-footer-modal']//child::input[@class='btn-danger' and @value='Delete category']")
	WebElement deleteCatBtn;

	String dynamicTaskCatXpath = "//table[@class='recordList colorDataTable']//child::a[contains(text(),'%replaceable%')]";

	public TaskCatPage() {
		PageFactory.initElements(driver, this);
	}

	public String getTaskCatsHeaderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, taskCatHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return taskCatHeaderTxt.getText();
	}

	public void addTaskCategory(String taskCatName) {
		Utils.clickWithExplicitWait(driver, addNewCatBtn, Utils.EXPLICIT_TIMEOUT);
		Utils.sendKeysWithExplicitWait(driver, taskCatNameTxt, Utils.EXPLICIT_TIMEOUT, taskCatName);

		Utils.clickWithExplicitWait(driver, saveBtn, Utils.EXPLICIT_TIMEOUT);
	}

	public String verifyCreatedTaskCat(String taskCatName) {
		String runtimeXpath = Utils.createDynamicXpath(dynamicTaskCatXpath, taskCatName);
		try {
			Thread.sleep(Utils.SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver.findElement(By.xpath(runtimeXpath)).getText();
	}

	public void deleteIfExistsTaskCat(String taskCatName) {
		String runtimeXpath = Utils.createDynamicXpath(dynamicTaskCatXpath, taskCatName);
		String deleteBtnXpath = runtimeXpath + "//parent::td/following-sibling::td/a";
		try {
			Utils.clickWithExplicitWait(driver, driver.findElement(By.xpath(deleteBtnXpath)), Utils.EXPLICIT_TIMEOUT);
			Utils.clickWithExplicitWait(driver, deleteCatBtn, Utils.EXPLICIT_TIMEOUT);
			Thread.sleep(Utils.SLEEP_TIME);
		} catch (Exception e) {
			logger.info("Creating new Task Category");
		}
	}
}
