package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class InvoicesPage extends Base{
	
	@FindBy(xpath="//div[@class='page-box']//child::header[text()='Invoices']")
	WebElement invoicesHeaderTxt;
	
	public InvoicesPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getInvHearderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, invoicesHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return invoicesHeaderTxt.getText();
	}
}
