package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class TrashPage extends Base{
	
	@FindBy(xpath="//div[@class='settings-page-content']//h2[contains(text(),'Trash')]")
	WebElement trashHeaderTxt;
	
	public TrashPage() {
		PageFactory.initElements(driver,this);
	}

	public String getTrashHeaderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, trashHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return trashHeaderTxt.getText();
	}

}
