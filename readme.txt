**************************Selenium Coding challenge Part 3(Using JAVA, TestNG and Page Object Model Framework)- From Naveen Automation Labs************************************
*******************************************************************************************************************************************************************************
Configuration: *
****************
1.Configuration file is placed in path /src/main/resources/config.properties
2.This script is capable to execute in Image disable mode and Headless mode. 
3.Browser, Headless mode or image disable mode can be configured from config.properties
4.Please execute this program from testng.xml
5.This project is configured for 3 browsers (chrome/ff/IE). No explicit browser drivers are required, as WebDriverManager has been used 
6.Log generation has implemented and all logs are available in application.log file	
7.This project is working perfectly in browser chome and ff, however there is some catch in IE.   	
************************************Structure of Project********************************************************
1.src/main/java : contains all packages and classes
		com.qa.base
			a. Base.java - This class is configured to read properties from config.properties, initialize property for all browsers, implements log and drivers.
		com.qa.pages
			a. LoginPage.java - This class contains all the Login Page components and corresponding functions, which are used during test cases.
			b. HomePage.java - This class contains all the Home Page components and corresponding functions, which are used during test cases.
			c. PersonPage.java - This class contains all the Person Page components and corresponding functions, which are used during test cases.
            d. CasePage.java - This class contains all the Case Page components and corresponding functions, which are used during test cases.
			e. AccSettingsPage.java - This class contains all the Account Settings Page components and corresponding functions, which are used during test cases.
			f. AccountPage.java - This class contains all the Account Page components and corresponding functions, which are used during test cases.
			g. InvoicesPage.java - This class contains all the Invoices Page components and corresponding functions, which are used during test cases.
			h. ExportPage.java - This class contains all the Export Page components and corresponding functions, which are used during test cases.
			i. AppearancePage.java - This class contains all the Appearance Page components and corresponding functions, which are used during test cases.
			j. MailDropPage.java - This class contains all the Mail Drop Page components and corresponding functions, which are used during test cases.
			k. UsersPage.java - This class contains all the Users Page components and corresponding functions, which are used during test cases.
			l. OpportunitiesPage.java - This class contains all the Opportunities Page components and corresponding functions, which are used during test cases.
			m. TracksPage.java - This class contains all the Tracks Page components and corresponding functions, which are used during test cases.
			n. TaskCatPage.java - This class contains all the Task Category Page components and corresponding functions, which are used during test cases.
			o. CustFieldsPage.java - This class contains all the Custom Fields Page components and corresponding functions, which are used during test cases.
			p. TagsPage.java - This class contains all the Tags Page components and corresponding functions, which are used during test cases.
			q. IntegrationsPage.java - This class contains all the Integrations Page components and corresponding functions, which are used during test cases.
			r. TrashPage.java - This class contains all the Trash Page components and corresponding functions, which are used during test cases.	
		com.qa.utils
			a. Utils.java - This class contains all the common utilities functions used in this framework.
			b. BrowserUtils.java - This class sets the browser properties (Currently has been implemented for 3 browsers, chrome/ff/IE)	
2.src/main/resource: Contains config.properties, log4j.properties, Sketch.jpg(used in the project) and TestData.xlsx(For input data)
3.src/test/java: contains packages and classes for TestCases
		com.qa.tetcases
			a. AccountSettingTest.java - This class contains all test cases to check and verify links inside Account settings.
			b. LoginTest.java - This class contains all test cases for login, create person, crate case and verify them.	 