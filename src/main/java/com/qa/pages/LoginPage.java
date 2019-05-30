package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.utils.Utils;

public class LoginPage extends Base {

	@FindBy(css = "#login\\:usernameDecorate\\:username")
	WebElement usernameTxt;

	@FindBy(id = "login:passwordDecorate:password")
	WebElement passwordTxt;

	@FindBy(xpath = "//input[@id='login:login' and @type='submit']")
	WebElement loginBtn;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}	

	public HomePage login(String uname, String pass) {
		Utils.sendKeysWithExplicitWait(driver, usernameTxt, Utils.EXPLICIT_TIMEOUT, uname);
		Utils.sendKeysWithExplicitWait(driver, passwordTxt, Utils.EXPLICIT_TIMEOUT, pass);
		
		Utils.clickElementByJS(loginBtn, driver);
		return new HomePage();
	}
}
