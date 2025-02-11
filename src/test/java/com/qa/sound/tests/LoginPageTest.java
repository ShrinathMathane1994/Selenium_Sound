package com.qa.sound.tests;

import com.qa.sound.pages.LoginPage;
import org.testng.annotations.Test;

import static com.qa.sound.listners.ExtentReportListener.getLogger;

public class LoginPageTest extends BaseTest {


    @Test(priority =
            1, enabled = true)
    public void doLoginTest() {
        page.getPageInstance(LoginPage.class).doLogin(prop.getProperty("userName"), prop.getProperty("password"));
        page.getPageInstance(LoginPage.class).verifyLogin();
        System.out.println("Login Successful");
        getLogger().get().pass("Login Successful");
    }

}
