package com.qa.sound.pages;

import com.qa.sound.utility.ElementUtil;
import com.qa.sound.utility.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public ElementUtil elementUtil;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        elementUtil = new ElementUtil(driver);
    }

    public By emailId = By.id("TxtName");
    public By password = By.id("TxtPassword");
    public By loginBtn = By.name("command");

    public void getLoginPageTitle() {
        System.out.println(elementUtil.getPageTitle());
    }

    public void verifyLogin(){
        Assert.assertTrue(elementUtil.getPageTitle().contains("Summary"),"Login Unsuccessful");
    }

    public void doLogin(String userName, String pass) {
        try {
            elementUtil.doSendKeys(emailId, userName);
            elementUtil.doSendKeys(password, pass);
            elementUtil.doClick(loginBtn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
