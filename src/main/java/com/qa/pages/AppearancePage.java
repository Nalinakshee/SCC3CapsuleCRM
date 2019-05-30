package com.qa.pages;

import java.io.File;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class AppearancePage extends Base {

	@FindBy(xpath = "//div[@class='settings-page-content']//child::h1[text()='Appearance']")
	WebElement appearanceHeaderTxt;

	@FindBy(xpath = "//form[@id='appearance']//input[@id='appearance:uploadDecorate:logoImage']")
	WebElement chooseFile;

	@FindBy(xpath = "//form[@id='appearance']//a[text()='Save']")
	WebElement btnSave;
	
	
	
	public AppearancePage() {
		PageFactory.initElements(driver, this);
	}

	public String getAppearanceHearderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, appearanceHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return appearanceHeaderTxt.getText();
	}

	public void saveAppearance() {
		Utils.sendKeysWithExplicitWait(driver, chooseFile, Utils.EXPLICIT_TIMEOUT,
				System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
						+ "resources" + File.separator + "Sketch.jpg");
		Utils.clickWithExplicitWait(driver, btnSave, Utils.EXPLICIT_TIMEOUT);
	}
	
	
}
