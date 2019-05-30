package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.base.Base;
import com.qa.pages.CasePage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.PersonPage;
import com.qa.utils.Utils;

public class LoginTest extends Base{
	LoginPage loginpage;
	HomePage homepage;
	PersonPage personpage;
	CasePage casePage;
	SoftAssert softAssert = new SoftAssert();
	
	//Call super method to initialize the Property class
	public LoginTest() {
		super();
	}
	
	@BeforeTest
	public void setup() {
		initialization();
		loginpage = new LoginPage();
	}
	
	@Test(priority=1)
	public void loginTest() {
		homepage = loginpage.login(prop.getProperty("username"), Utils.decodeAnyString(prop.getProperty("encryptedpwd")));
		logger.info("login successful");
		//String homePageWelcomeMsg = homepage.getWelcomeTxtOfHomePage();
		//softAssert.assertEquals(homePageWelcomeMsg, "Need help getting started?", "Home Page Welcome Message is not matching");
		//String homePageIconTxt = homepage.getHomeIconText();
		//Assert.assertEquals(homePageIconTxt, "Dashboard", "Home Icon is not present");
		Assert.assertTrue(homepage.navBarIsDisplayed(), "Navigation Bar is not coming !");		
		logger.info("assertion pass after successful login");
		logger.info("*****************************************************************");
		personpage = homepage.clickOnPersonIcon();
		logger.info("clicking on person icon");
		String personTxt = personpage.getPersonTxt();
		softAssert.assertEquals(personTxt, "People & Organisations", "Person Text is not matching");	
		logger.info("assertion pass after successful click on person");
		softAssert.assertAll();	
	}	
	@DataProvider
	public Object[][] getPersonTestData(){
		Object data[][] = Utils.getTestData("Person");			
		return data;
	}	
	@Test(priority=2, dataProvider = "getPersonTestData", dependsOnMethods="loginTest")
	public void createNewPersonTest(String title, String fName, String lName,String jobTitle, String org, String tags, String phone, String email) {
		logger.info("Deleting person if exists");
		personpage.deleteIfExistsPerson(fName, lName);
		personpage.clickOnAddPerson();
		logger.info("clicking on add person icon");
		personpage.createNewPerson(title, fName, lName, jobTitle,org,tags,phone,email);	
		logger.info("creating new person");
		softAssert.assertEquals(personpage.verifyAddedPerson(), title+" "+fName+" "+lName, "Added Person Name is not matching");
		logger.info("created new person and verified successfully");
		logger.info("*****************************************************************");
		casePage = homepage.clickOnCaseIcon();
		logger.info("clicking on add Case icon");
		casePage.clickOnAddCase();		
		softAssert.assertAll();	
	}	
	@DataProvider
	public Object[][] getCaseTestData(){
		Object data[][] = Utils.getTestData("Case");			
		return data;
	}	
	@Test(priority=3, dataProvider = "getCaseTestData", dependsOnMethods="createNewPersonTest")
	public void createCaseTest(String caseRelateTo, String name, String description, String tags) {
		logger.info("creating new Case");
		casePage.createNewCase(caseRelateTo,name,description,tags);
		String caseCreated = casePage.verifyCaseForPerson(caseRelateTo);
		logger.info("created new Case and verified successfully");
		softAssert.assertEquals(caseCreated, caseRelateTo, "Created Case is not matching");		
		String caseStatus = casePage.verifyCaseStatus();
		softAssert.assertEquals(caseStatus, "Open", "Case Status is not Open");
		logger.info("verified case status successfully");
		softAssert.assertAll();				
		logger.info("*****************************************************************");
	}
	
	@AfterTest
	public void teardown() {
		Base.quit();
	}
}