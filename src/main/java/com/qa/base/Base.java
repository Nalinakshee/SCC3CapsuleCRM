package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.qa.utils.BrowserUtils;
import com.qa.utils.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class is written to load properties and initialize driver
 * 
 * @author Nalinakshee
 * @class Base
 */
public class Base {
	public static WebDriver driver = null;
	public static Properties prop;
	public static Logger logger;
	static BrowserUtils bUtils;

	/**
	 * Default Constructor
	 */
	public Base() {
		logger = Logger.getLogger(Base.class);
		prop = new Properties();
		bUtils = new BrowserUtils();
		FileInputStream file;
		try {

			file = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "config.properties");
			prop.load(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to initialize driver depends on different browsers
	 */
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if (driver == null) {
			if (browserName.equals("chrome")) {
				//WebDriverManager maven dependency is added in pom.xml to support System.setProperty for all drivers
				WebDriverManager.chromedriver().setup();
				bUtils.browserProperties("chrome");
				logger.info("launching chrome browser");
			} else if (browserName.equals("ff")) {
				WebDriverManager.firefoxdriver().setup();
				bUtils.browserProperties("ff");
				logger.info("launching firefox browser");
			} else if (browserName.equals("IE")) {
				WebDriverManager.iedriver().setup();
				//driver = new InternetExplorerDriver();
				bUtils.browserProperties("IE");
				logger.info("launching IE browser");
			} else {
				System.out.println("Please give a browsername (chrome/ff/IE) in config file");
				logger.info("Please give a browsername (chrome/ff/IE) in config file");
			}
		}
		driver.manage().window().maximize();
		logger.info("maximizing window");
		driver.manage().timeouts().pageLoadTimeout(Utils.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
		logger.info("setting page load timeout");
		driver.get(prop.getProperty("url"));
		logger.info("launching url");
	}

	/**
	 * Method to quit the browser
	 */
	public static void quit() {
		logger.info("Quitting the browser");
		driver.quit();
		driver = null;
	}

	/**
	 * Method to close the browser
	 */
	public static void close() {
		logger.info("Closing the browser");
		driver.close();
		driver = null;
	}
}
