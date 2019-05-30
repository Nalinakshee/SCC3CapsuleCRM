package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class IntegrationsPage extends Base{
	
	@FindBy(xpath="//div[@class='settings-page-content']//h2[contains(text(),'Integrations')]")
	WebElement integrationHeaderTxt;
	
	@FindBy(xpath="//table[@class='recordList']//child::a[contains(text(),'Configure')]")
	List<WebElement> listOfConfigures;
	
	public IntegrationsPage() {
		PageFactory.initElements(driver,this);
	}

	public String getIntegrationsHeaderTxt() {
		Utils.checkVisibilityWithExplicitWait(driver, integrationHeaderTxt, Utils.EXPLICIT_TIMEOUT);
		return integrationHeaderTxt.getText();
	}
	
	public int countOfConfigureBtns() {
		return listOfConfigures.size();
	}
}
