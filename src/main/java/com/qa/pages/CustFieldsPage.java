package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class CustFieldsPage extends Base{
	
	@FindBy(xpath="//div[@class='settings-page-content']//h2[contains(text(),'Custom Fields')]")
	WebElement custFieldsHeaderTxt;
	
	public CustFieldsPage() {
		PageFactory.initElements(driver,this);
	}

	public String getCustFieldsHeader() {
		Utils.checkVisibilityWithExplicitWait(driver, custFieldsHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return custFieldsHeaderTxt.getText();
	}
}
