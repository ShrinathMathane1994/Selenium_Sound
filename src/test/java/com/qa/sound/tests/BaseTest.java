package com.qa.sound.tests;

import com.qa.sound.listners.ExtentReportListener;
import com.qa.sound.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import static com.qa.sound.listners.ExtentReportListener.getLogger;

public class BaseTest {

    public  Properties prop;
    public  WebDriver driver;
    public  WebDriverWait wait;
    public  BasePage page;
    public ExtentReportListener listener;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    @BeforeClass
    public void browserIntialization() {
        try {
            listener = new ExtentReportListener();
            prop = new Properties();
            FileInputStream file = new FileInputStream(System.getProperty("user.dir")
                    + "/src/test/java/com/qa/sound/config/config.properties");
            prop.load(file);

            switch (prop.getProperty("browserName")) {
                case "chrome":
                    ChromeOptions option = new ChromeOptions();
                    option.addArguments("--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors",
                            "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
                    driver = new ChromeDriver(option);
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Provide Valid Browser Name");
            }

            driver.get(prop.getProperty("url"));
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().getPageLoadTimeout();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            page = new BasePage(driver, wait);

            driver = listener.getDriver(driver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("Browser Terminated Successfully");
        getLogger().get().pass("Browser Terminated Successfully");
    }

}
