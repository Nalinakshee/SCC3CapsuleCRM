package com.qa.utils;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qa.base.Base;

public class BrowserUtils {
	ChromeOptions chromeOptions;
	FirefoxOptions ffOptions;
	// InternetExplorerOptions ieOptions;

	/**
	 * This method will set properties of different browsers, based on configuration
	 * 
	 * @param browserName
	 */
	@SuppressWarnings("deprecation")
	public void browserProperties(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			chromeOptions = new ChromeOptions();
			if (Base.prop.getProperty("disableImageMode").equalsIgnoreCase("yes")) {
				HashMap<String, Object> images = new HashMap<String, Object>();
				images.put("images", 2);
				HashMap<String, Object> pref = new HashMap<String, Object>();
				pref.put("profile.default_content_setting_values", images);
				chromeOptions.setExperimentalOption("prefs", pref);
			}
			if (Base.prop.getProperty("headlessMode").equalsIgnoreCase("yes")) {
				chromeOptions.addArguments("--headless");
			}
			if (Base.prop.getProperty("incognitoChromeMode").equalsIgnoreCase("yes")) {
				chromeOptions.addArguments("--incognito");
			}
			Base.driver = new ChromeDriver(chromeOptions);
		} else if (browserName.equalsIgnoreCase("ff")) {
			ffOptions = new FirefoxOptions();
			if (Base.prop.getProperty("disableImageMode").equalsIgnoreCase("yes")) {
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("permissions.default.image", 2);
				ffOptions.setProfile(profile);
				ffOptions.setCapability(FirefoxDriver.PROFILE, profile);
			}
			if (Base.prop.getProperty("headlessMode").equalsIgnoreCase("yes")) {
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				firefoxBinary.addCommandLineOptions("--headless");
				ffOptions.setBinary(firefoxBinary);
			}
			Base.driver = new FirefoxDriver(ffOptions);
		} else if (browserName.equalsIgnoreCase("IE")) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			 capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			File ie_temp = new File(System.getProperty("user.dir") + File.separator + "temp");
			InternetExplorerDriverService.Builder ies = new InternetExplorerDriverService.Builder();
			ies.withExtractPath(ie_temp);
			InternetExplorerDriverService service = ies.build();
			Base.driver = new InternetExplorerDriver(service, capabilities);
		}
		Base.driver.manage().deleteAllCookies();
	}
}
