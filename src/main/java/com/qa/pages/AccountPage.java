package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class AccountPage extends Base{
	
	@FindBy(xpath="//div[@class='settings-page-content']//child::h1[text()='Account']")
	WebElement accountHeaderTxt;
	
	public AccountPage() {
		PageFactory.initElements(driver, this);
	}	
	
	public String getAccHearderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, accountHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return accountHeaderTxt.getText();
	}
}
