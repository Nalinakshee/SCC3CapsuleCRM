package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class MailDropPage extends Base{
	
	@FindBy(xpath="//div[@class='settings-page-content']//child::h2[text()='Mail Drop Box']")
	WebElement mdbHeaderTxt;
	
	public MailDropPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getMdbHearderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, mdbHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return mdbHeaderTxt.getText();
	}
}
