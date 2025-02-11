package com.qa.sound.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptUtil {

    public WebDriver driver;
    public JavascriptExecutor js;

    public JavaScriptUtil(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }


}
