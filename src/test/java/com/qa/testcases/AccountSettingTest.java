package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.base.Base;
import com.qa.pages.AccSettingsPage;
import com.qa.pages.AccountPage;
import com.qa.pages.AppearancePage;
import com.qa.pages.CustFieldsPage;
import com.qa.pages.ExportPage;
import com.qa.pages.HomePage;
import com.qa.pages.IntegrationsPage;
import com.qa.pages.InvoicesPage;
import com.qa.pages.LoginPage;
import com.qa.pages.MailDropPage;
import com.qa.pages.OpportunitiesPage;
import com.qa.pages.TagsPage;
import com.qa.pages.TaskCatPage;
import com.qa.pages.TracksPage;
import com.qa.pages.TrashPage;
import com.qa.pages.UsersPage;
import com.qa.utils.Utils;

public class AccountSettingTest extends Base {
	LoginPage loginpage;
	HomePage homepage;
	AccSettingsPage accSettingPage;
	AccountPage accountPage;
	InvoicesPage invPage;
	ExportPage exportPage;
	AppearancePage appearancePage;
	MailDropPage mailDropPage;
	UsersPage usersPage;
	OpportunitiesPage opportunitiesPage;
	TracksPage tracksPage;
	TaskCatPage taskCatPage;
	CustFieldsPage custFieldsPage;
	TagsPage tagsPage;
	IntegrationsPage integrationsPage;
	TrashPage trashPage;
	SoftAssert softAssert = new SoftAssert();
	String header;

	public AccountSettingTest() {
		super();
	}

	@BeforeTest
	public void setup() {
		initialization();
		loginpage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginTest() {
		homepage = loginpage.login(prop.getProperty("username"),
				Utils.decodeAnyString(prop.getProperty("encryptedpwd")));
		logger.info("login successful");
		Assert.assertTrue(homepage.navBarIsDisplayed(), "Navigation Bar is not coming !");
		logger.info("assertion pass after successful login");
		logger.info("*****************************************************************");
		accSettingPage = homepage.clickOnAccountSetting();
		logger.info("In account setting Page");
		header = accSettingPage.getAccSettingPageHeader();
		softAssert.assertEquals(header, "Account Settings");
		logger.info("In account setting Page after successful assertion");
		logger.info("*****************************************************************");
		softAssert.assertAll();
	}

	@Test(priority = 2, dependsOnMethods = "loginTest")
	public void linksAccountToMailDropsTest() {
		// Account
		logger.info("Clicking on Account Link");
		accountPage = accSettingPage.clickOnAccount();
		header = accountPage.getAccHearderTxt();
		softAssert.assertEquals(header, "Account", "Header is not matching for Account");
		logger.info("In account Page after successful assertion");
		logger.info("*****************************************************************");
		// Invoices
		logger.info("Clicking on Invoices Link");
		invPage = accSettingPage.clickOnInvoices();
		header = invPage.getInvHearderTxt();
		softAssert.assertEquals(header, "Invoices", "Header is not matching for Invoices");
		logger.info("In Invoices Page after successful assertion");
		logger.info("*****************************************************************");
		// Export
		logger.info("Clicking on Export Link");
		exportPage = accSettingPage.clickOnExport();
		header = exportPage.getExportHearderTxt();
		softAssert.assertEquals(header, "Export", "Header is not matching for Export");
		logger.info("In Export Page after successful assertion");
		logger.info("*****************************************************************");
		// Appearance
		logger.info("Clicking on Appearance Link");
		appearancePage = accSettingPage.clickOnAppearance();
		header = appearancePage.getAppearanceHearderTxt();
		softAssert.assertEquals(header, "Appearance", "Header is not matching for Appearance");
		logger.info("In Appearance Page after successful assertion");
		appearancePage.saveAppearance();
		logger.info("Appearance has been saved successfully");
		softAssert.assertAll();
		logger.info("*****************************************************************");
		// Mail Drop Box
		logger.info("Clicking on Mail Drop Link");
		mailDropPage = accSettingPage.clickOnMailDrop();
		header = mailDropPage.getMdbHearderTxt();
		softAssert.assertEquals(header, "Mail Drop Box", "Header is not matching for Mail Drop Box");
		logger.info("In Mail Drop Box Page after successful assertion");
		softAssert.assertAll();
		logger.info("*****************************************************************");
	}

	@DataProvider
	public Object[][] getUserTestData() {
		Object data[][] = Utils.getTestData("User");
		return data;
	}

	@Test(priority = 3, dataProvider = "getUserTestData", dependsOnMethods = "linksAccountToMailDropsTest")
	public void createUserTest(String fName, String lName, String eMail, String uName) {
		// Users
		logger.info("Clicking on Users Link");
		usersPage = accSettingPage.clickOnUsers();
		header = usersPage.getUsersHearderTxt();
		softAssert.assertEquals(header, "Users", "Header is not matching for Users");
		logger.info("In Users Page after successful assertion");
		// Delete if there is an existing user, this is because application is
		// restricting to create more than two users
		usersPage.deleteExistedUser(fName + " " + lName);
		usersPage.addNewUser(fName, lName, eMail, uName);
		String name = usersPage.verifyCreatedUser(fName + " " + lName);
		softAssert.assertEquals(name, fName + " " + lName, "Created User is not matching");
		logger.info("In Users Page User created successfully");
		softAssert.assertAll();
		logger.info("*****************************************************************");
	}

	@DataProvider
	public Object[][] getMileStoneTestData() {
		Object data[][] = Utils.getTestData("MileStone");
		return data;
	}

	@Test(priority = 4, dataProvider = "getMileStoneTestData", dependsOnMethods = "createUserTest")
	public void createOpportunities(String name, String description, String probability, String days) {
		// Opportunities/Milestone
		logger.info("Clicking on Opportunities Link");
		opportunitiesPage = accSettingPage.clickOnOpportunities();
		header = opportunitiesPage.getOpporHeaderTxt();
		softAssert.assertEquals(header, "Opportunities", "Header is not matching for Opportunities");
		// Please change MileStone Name in TestData MileStone tab, as it is unique
		// otherwise it will not create an existing milestone
		opportunitiesPage.addMileStone(name, description, probability, days);
		String mileStoneName = opportunitiesPage.verifyCreatedMileStone(name);
		softAssert.assertEquals(mileStoneName, name, "Created MileStone is not matching");
		logger.info("Created milestone is " + mileStoneName);
		softAssert.assertAll();
		logger.info("*****************************************************************");
	}

	@DataProvider
	public Object[][] getTracksTestData() {
		Object data[][] = Utils.getTestData("Track");
		return data;
	}

	@Test(priority = 5, dataProvider = "getTracksTestData", dependsOnMethods = "createOpportunities")
	public void createTrack(String trackName, String taskDescription) {
		// Tracks
		logger.info("Clicking on Tracks Link");
		tracksPage = accSettingPage.clickOnTracks();
		header = tracksPage.getTracksHeaderTxt();
		softAssert.assertEquals(header, "Tracks", "Header is not matching for Tracks");
		// Delete existing track, so that duplicate tracks will not be created.
		logger.info("Checking if track already exist, then it will delete");
		tracksPage.deleteIfExistsTrack(trackName);
		tracksPage.addTrack(trackName, taskDescription);
		String createdTrackName = tracksPage.verifyCreatedTrack(trackName);
		softAssert.assertEquals(createdTrackName, trackName, "Created Track is not matching");
		logger.info("Created Track is " + createdTrackName);
		softAssert.assertAll();
		logger.info("*****************************************************************");
	}

	@DataProvider
	public Object[][] getTaskCatTestData() {
		Object data[][] = Utils.getTestData("TaskCategory");
		return data;
	}

	@Test(priority = 6, dataProvider = "getTaskCatTestData", dependsOnMethods = "createTrack")
	public void createTaskCategory(String taskCatName) {
		// Task Categories
		logger.info("Clicking on Task Categories Link");
		taskCatPage = accSettingPage.clickOnTaskCat();
		header = taskCatPage.getTaskCatsHeaderTxt();
		softAssert.assertEquals(header, "Task Categories", "Header is not matching for Task Categories");
		// Delete if task category already exist
		taskCatPage.deleteIfExistsTaskCat(taskCatName);
		taskCatPage.addTaskCategory(taskCatName);
		String createdTaskCat = taskCatPage.verifyCreatedTaskCat(taskCatName);
		softAssert.assertEquals(createdTaskCat, taskCatName, "Created Task Category is not matching");
		logger.info("Created Task Category is " + createdTaskCat);
		softAssert.assertAll();
		logger.info("*****************************************************************");
	}

	@Test(priority = 7, dependsOnMethods = "createTaskCategory")
	public void custFieldsTest() {
		// Custom fields
		logger.info("Clicking on Custom Fields Link");
		custFieldsPage = accSettingPage.clickOnCustFields();
		header = custFieldsPage.getCustFieldsHeader();
		softAssert.assertEquals(header, "Custom Fields", "Header is not matching for Custom Fields");
		logger.info("In Custom Fields Page after successful assertion");
		softAssert.assertAll();
		logger.info("*****************************************************************");
	}

	@DataProvider
	public Object[][] geTagsTestData() {
		Object data[][] = Utils.getTestData("Tag");
		return data;
	}

	@Test(priority = 8, dependsOnMethods = "custFieldsTest", dataProvider = "geTagsTestData")
	public void createTagsTest(String tagName) {
		// Tags
		logger.info("Clicking on Tags Link");
		tagsPage = accSettingPage.clickOnTags();
		header = tagsPage.getTagsHeaderTxt();
		softAssert.assertEquals(header, "Tags and DataTags", "Header is not matching for Tags");
		// Delete if Tags and DataTags already exist
		tagsPage.deleteIfExistsTag(tagName);
		tagsPage.addTag(tagName);
		String createdTag = tagsPage.verifyCreatedTag(tagName);
		softAssert.assertEquals(createdTag, tagName, "Created Tag is not matching");
		logger.info("Created Tag is " + createdTag);
		softAssert.assertAll();
		logger.info("*****************************************************************");
	}

	@Test(priority = 9, dependsOnMethods = "createTagsTest")
	public void IntegrationAndTrashTest() {
		// Integrations
		logger.info("Clicking on Integrations Link");
		integrationsPage = accSettingPage.clickOnIntegrations();
		header = integrationsPage.getIntegrationsHeaderTxt();
		softAssert.assertEquals(header, "Integrations", "Header is not matching for Integrations");
		logger.info("In Integration Page after successful assertion");
		int countOfConfigure = integrationsPage.countOfConfigureBtns();
		logger.info("Count of Configure Button is " + countOfConfigure);
		logger.info("*****************************************************************");
		// Trash
		logger.info("Clicking on Trash Link");
		trashPage = accSettingPage.clickOnTrash();
		header = trashPage.getTrashHeaderTxt();
		softAssert.assertEquals(header, "Trash", "Header is not matching for Trash");
		softAssert.assertAll();
		logger.info("In Trash Page after successful assertion");
		logger.info("*****************************************************************");
	}

	@AfterTest
	public void teardown() {
		Base.quit();
	}
}
