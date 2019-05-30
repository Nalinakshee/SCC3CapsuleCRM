package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class AccSettingsPage extends Base {

	@FindBy(xpath = "//div[@class='settings-content']//child::span[text()='Account Settings']")
	WebElement accSettingLbl;

	@FindBy(xpath = "//div[@class='left-fixed']//child::li//a[text()='Account']")
	WebElement accountLink;

	@FindBy(xpath = "//div[@class='left-fixed']//child::li//a[text()='Invoices']")
	WebElement invoicesLink;

	@FindBy(xpath = "//ul[@class='settings-nav']//li//a[contains(text(),'Export')]")
	WebElement exportLink;

	@FindBy(xpath = "//div[@class='left-fixed']//child::li//a[text()='Appearance']")
	WebElement appearanceLink;

	@FindBy(xpath = "//div[@class='left-fixed']//child::li//a[text()='Mail Drop Box']")
	WebElement mdbLink;

	@FindBy(xpath = "//div[@class='left-fixed']//child::li//a[text()='Users']")
	WebElement usersLink;

	@FindBy(xpath = "//div[@class='left-fixed']//child::li//a[text()='Opportunities']")
	WebElement opprtunitiesLink;

	@FindBy(xpath = "//ul[@class='settings-nav']//li//a[contains(text(),'Tracks')]")
	WebElement tracksLink;

	@FindBy(xpath = "//div[@class='left-fixed']//child::li//a[contains(text(),'Task Categories')]")
	WebElement taskCatLink;
	
	@FindBy(xpath = "//div[@class='left-fixed']//child::li//a[contains(text(),'Custom Fields')]")
	WebElement custFieldsLink;
	
	@FindBy(xpath = "//div[@class='left-fixed']//child::li//a[contains(text(),'Tags')]")
	WebElement tagsLink;
	
	@FindBy(xpath = "//div[@class='left-fixed']//child::li//a[contains(text(),'Integrations')]")
	WebElement integrationsLink;
	
	@FindBy(xpath = "//div[@class='left-fixed']//child::li//a[contains(text(),'Trash')]")
	WebElement trashLink;

	public AccSettingsPage() {
		PageFactory.initElements(driver, this);
	}

	public String getAccSettingPageHeader() {
		Utils.checkVisibilityWithExplicitWait(driver, accSettingLbl, Utils.EXPLICIT_TIMEOUT);
		return accSettingLbl.getText();
	}

	public AccountPage clickOnAccount() {
		Utils.clickWithExplicitWait(driver, accountLink, Utils.EXPLICIT_TIMEOUT);
		return new AccountPage();
	}

	public InvoicesPage clickOnInvoices() {
		Utils.clickWithExplicitWait(driver, invoicesLink, Utils.EXPLICIT_TIMEOUT);
		return new InvoicesPage();
	}

	public ExportPage clickOnExport() {
		Utils.clickWithExplicitWait(driver, exportLink, Utils.EXPLICIT_TIMEOUT);
		return new ExportPage();
	}

	public AppearancePage clickOnAppearance() {
		Utils.clickWithExplicitWait(driver, appearanceLink, Utils.EXPLICIT_TIMEOUT);
		return new AppearancePage();
	}

	public MailDropPage clickOnMailDrop() {
		Utils.clickWithExplicitWait(driver, mdbLink, Utils.EXPLICIT_TIMEOUT);
		return new MailDropPage();
	}

	public UsersPage clickOnUsers() {
		Utils.clickWithExplicitWait(driver, usersLink, Utils.EXPLICIT_TIMEOUT);
		return new UsersPage();
	}

	public OpportunitiesPage clickOnOpportunities() {
		Utils.clickWithExplicitWait(driver, opprtunitiesLink, Utils.EXPLICIT_TIMEOUT);
		return new OpportunitiesPage();
	}

	public List<String> getAllLinksOfAccSettings() {
		List<String> listRawList = new ArrayList<String>();
		List<WebElement> listLinks = driver.findElements(By.xpath("//div[@class='left-fixed']//child::li//a"));
		for (WebElement webElement : listLinks) {
			listRawList.add(webElement.getText());
		}

		return listRawList;
	}

	public TracksPage clickOnTracks() {
		Utils.clickWithExplicitWait(driver, tracksLink, Utils.EXPLICIT_TIMEOUT);
		return new TracksPage();
	}
	
	public TaskCatPage clickOnTaskCat() {
		Utils.clickWithExplicitWait(driver, taskCatLink, Utils.EXPLICIT_TIMEOUT);
		return new TaskCatPage();
	}
	public CustFieldsPage clickOnCustFields() {
		Utils.clickWithExplicitWait(driver, custFieldsLink, Utils.EXPLICIT_TIMEOUT);
		return new CustFieldsPage();
	}
	public TagsPage clickOnTags() {
		Utils.clickWithExplicitWait(driver, tagsLink, Utils.EXPLICIT_TIMEOUT);
		return new TagsPage();
	}
	public IntegrationsPage clickOnIntegrations() {
		Utils.clickWithExplicitWait(driver, integrationsLink, Utils.EXPLICIT_TIMEOUT);
		return new IntegrationsPage();
	}
	public TrashPage clickOnTrash() {
		Utils.clickWithExplicitWait(driver, trashLink, Utils.EXPLICIT_TIMEOUT);
		return new TrashPage();
	}
}
