package com.qa.sound.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class ElementUtil {

    public WebDriver driver;
    public WebDriverWait wait;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void doClick(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void doSendKeys(By locator, String text) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            driver.findElement(locator).sendKeys(text);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getHeaderText(By locator) {
        String textValue =null;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
             textValue = driver.findElement(locator).getText();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return textValue;
    }

    public String getAttributeValue(By locator, String value) {
        String attrValue = null;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            attrValue =  driver.findElement(locator).getDomAttribute(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return attrValue;
    }

}
