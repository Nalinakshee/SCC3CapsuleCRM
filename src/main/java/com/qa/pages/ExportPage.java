package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class ExportPage extends Base{
	
	@FindBy(xpath="//div[@class='settings-page-content']//child::h1[text()='Export']")
	WebElement exportHeaderTxt;
	
	public ExportPage() {
		PageFactory.initElements(driver, this);
	}

	public String getExportHearderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, exportHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return exportHeaderTxt.getText();
	}
}
