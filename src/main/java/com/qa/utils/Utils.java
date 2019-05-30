package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is written to define common variables and common utilities
 * function
 * 
 * @author Nalinakshee
 * @class MmtUtils
 */
public class Utils {
	public static long PAGELOAD_TIMEOUT = 20;
	public static long IMPLICIT_TIMEOUT = 20;
	public static int SCROLL_COUNTER = 20;
	public static int EXPLICIT_TIMEOUT = 30;
	public static long SLEEP_TIME = 500;
	static Logger logger = Logger.getLogger(Utils.class);

	public static String EXCELL_TEST_DATA_PATH = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "TestData.xlsx";

	static Workbook book;
	static Sheet sheet;

	/**
	 * This method will take one date from calender's current date and will return
	 * the corresponding co-ordinate
	 * 
	 * @param objDate
	 * @return String
	 */
	public static String getCoordinate(LocalDate objDate) {
		String cordinate = "";
		int row = 0;
		int month = objDate.getMonthValue();
		int year = objDate.getYear();

		LocalDate firstdayoftheMonth = LocalDate.of(year, month, 1);

		int firstcol = firstdayoftheMonth.getDayOfWeek().getValue();

		if (firstcol == 7) {
			firstcol = 1;
		} else {
			firstcol += 1;
		}

		int offset = 0;
		if (firstcol == 1) {
			offset = 7;
			row = 1;
		} else {
			offset = (7 - firstcol) + 1;
			row = 1;
		}

		int dayoftheMonth = objDate.getDayOfMonth();

		int restcells = dayoftheMonth - offset;

		if (restcells > 0) {
			int addrow = 0;
			if (restcells % 7 == 0) {
				addrow = (restcells / 7);
			} else {
				addrow = (restcells / 7) + 1;
			}

			row = row + addrow;
		}

		int col = objDate.getDayOfWeek().getValue();

		if (col == 7) {
			col = 1;
		} else {
			col += 1;
		}
		cordinate = "" + row + "-" + col;
		return cordinate;
	}

	/**
	 * This method is used to click any Element by JavaScript
	 * 
	 * @param element
	 * @param driver
	 */
	public static void clickElementByJS(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * This method is used to scroll down the page till 20 times
	 * 
	 * @param driver
	 * @param scrollCounter
	 */
	public static void scrollPageDown(WebDriver driver, int scrollCounter) {
		// int counter = 0;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			long height = Long.parseLong(js.executeScript("return document.body.scrollHeight").toString());
			// while (counter != scrollCounter) {
			while (true) {
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(Utils.SLEEP_TIME);
				long newHeight = Long.parseLong(js.executeScript("return document.body.scrollHeight").toString());
				if (height == newHeight) {
					break;
				}
				height = newHeight;
				// counter++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to draw border on specific element
	 * 
	 * @param element
	 * @param driver
	 */
	public static void drawBorder(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border = '3px solid yellow'", element);
	}

	/**
	 * Method is used to scroll until a particular element is visible
	 * 
	 * @param element
	 * @param driver
	 */
	public static void scrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 * @param timeout
	 * @param value
	 */
	public static void sendKeysWithExplicitWait(WebDriver driver, WebElement element, int timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 * @param timeout
	 * @param value
	 */
	public static void sendKeysWithExplicitWait(WebDriver driver, WebElement element, int timeout, Keys value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 * @param timeout
	 */
	public static void clickWithExplicitWait(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 * @param timeout
	 */
	public static void checkVisibilityWithExplicitWait(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 * @param timeout
	 */
	public static boolean checkVisOfElementLocatedWithExWait(WebDriver driver, By locator, int timeout) {
		boolean flag = false;
		try {
			flag = true;
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch(Exception e) {
			flag = false;
			logger.info("Exception caught during locating element ! "+ e.getMessage());
		}
		return flag;
	}

	/**
	 * This method will take one xpath expression and any number of arguments and
	 * will return a dynamic xpath e.g to call this pass xpathExp as //input[@id =
	 * '{0}'] and objects array as 'username', then this method will return xpathExp
	 * as //input[@id='username'], Similarly pass for {1}, {2} for 2nd and 3rd
	 * parameters
	 * 
	 * @param xpathExp
	 * @param objects
	 * @return String
	 */
	public static String createDynamicXpath(String xpathExp, Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			xpathExp = xpathExp.replace("{" + i + "}", (CharSequence) objects[i]);
		}
		return xpathExp;
	}

	/**
	 * This method is useful to create dynamic xpath
	 * 
	 * @param xpath
	 * @param data
	 * @return String
	 */
	public static String createDynamicXpath(String xpath, String data) {
		return xpath.replaceAll("%replaceable%", data);
	}

	/**
	 * This method will take the sheetName and will return a 2D data array from an
	 * excel
	 * 
	 * @param sheetName
	 * @return Object[][]
	 */
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;

		try {
			file = new FileInputStream(EXCELL_TEST_DATA_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}

		return data;
	}

	/**
	 * Method to decode any encoded string - used for password encryption
	 * 
	 * @param encodedStr
	 * @return String
	 */
	public static String decodeAnyString(String encodedStr) {
		byte[] decodeStr = Base64.decodeBase64(encodedStr);
		return new String(decodeStr);
	}

	/**
	 * Method to encode any decoded string - used for password encryption
	 * 
	 * @param encodedStr
	 * @return String
	 */
	public static String encodeAnyString(byte[] decodedStr) {
		byte[] encodeStr = Base64.encodeBase64(decodedStr);
		return new String(encodeStr);
	}
}
