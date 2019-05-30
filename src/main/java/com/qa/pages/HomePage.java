package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class HomePage extends Base {

	@FindBy(xpath = "//button[@class='welcome-bar-help']")
	WebElement welcomeBarTxt;
	
	@FindBy(xpath="//nav[@class='nav-bar']")
	WebElement navBar;
	
	@FindBy(xpath="//nav[@class='nav-bar']//child::a//child::span[contains(text(),'Dashboard')]")
	WebElement homePageIcon;

	@FindBy(xpath = "//nav[@class='nav-bar']//span[text()='People & Organisations']//parent::a")
	WebElement personIcon;

	@FindBy(xpath = "//nav[@class='nav-bar']//span[text()='Cases']//parent::a")
	WebElement caseIcon;
	
	@FindBy(xpath="//div[contains(@class,'nav-bar-section nav-bar-account')]")
	WebElement dropDown;
	
	@FindBy(xpath="//div[contains(@class,'menu-select is-open')]//div[contains(@class,'menu-select-options')]//a[text()='Account Settings']")
	WebElement accSettingLink;

	String dropdownStr = "//div[@class='ember-view']//child::div[@class='nav-bar-section nav-bar-account']";
	String acctSettingStr = "//a[text()='Account Settings']";

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String getWelcomeTxtOfHomePage() {
		return welcomeBarTxt.getText();
	}
	
	public String getHomeIconText() {
		Utils.checkVisibilityWithExplicitWait(driver, homePageIcon, Utils.EXPLICIT_TIMEOUT);
		return homePageIcon.getText();
	}
	
	public boolean navBarIsDisplayed() {
		Utils.checkVisibilityWithExplicitWait(driver, navBar, Utils.EXPLICIT_TIMEOUT);
		return navBar.isDisplayed();
	}

	public PersonPage clickOnPersonIcon() {
		Utils.clickWithExplicitWait(driver, personIcon, Utils.EXPLICIT_TIMEOUT);
		return new PersonPage();
	}

	public CasePage clickOnCaseIcon() {
		Utils.clickWithExplicitWait(driver, caseIcon, Utils.EXPLICIT_TIMEOUT);
		return new CasePage();
	}

	public AccSettingsPage clickOnAccountSetting() {
		Actions actions = new Actions(driver);
		/*Utils.checkVisibilityWithExplicitWait(driver, driver.findElement(By.xpath(dropdownStr)),
				Utils.EXPLICIT_TIMEOUT);
		WebElement dropDown = driver.findElement(By.xpath(dropdownStr));*/
		Utils.checkVisibilityWithExplicitWait(driver, dropDown, Utils.EXPLICIT_TIMEOUT);
		actions.moveToElement(dropDown).click().build().perform();
		/*Utils.checkVisibilityWithExplicitWait(driver, driver.findElement(By.xpath(acctSettingStr)),
				Utils.EXPLICIT_TIMEOUT);
		WebElement accSettingLink = driver.findElement(By.xpath(acctSettingStr));*/
		Utils.checkVisibilityWithExplicitWait(driver, accSettingLink, Utils.EXPLICIT_TIMEOUT);
		actions.moveToElement(accSettingLink).click().build().perform();
		return new AccSettingsPage();
	}
}
