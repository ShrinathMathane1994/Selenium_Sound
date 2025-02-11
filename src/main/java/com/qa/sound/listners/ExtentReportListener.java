package com.qa.sound.listners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class ExtentReportListener implements ITestListener {

    public static WebDriver driver;
    private static final String OUTPUT_FOLDER = "./reports/";
    private static final String FILE_NAME = System.currentTimeMillis()+"_TestExecutionReport.html";

    private static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private static ExtentReports extentReports;


    private static ExtentReports init() {
        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        reporter.config().setReportName("Sound Project Automation Result");
        extentReports.attachReporter(reporter);
        return extentReports;
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suit is Started");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("Test Suit is Ended");
        extentReports.flush();
        test.remove();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        System.out.println(methodName + " started!");
        ExtentTest extentTest = extentReports.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        System.out.println((methodName + " passed!"));
        test.get().pass("Test passed");
        //test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        String methodName = result.getMethod().getMethodName();
        test.get().fail("Test failed");
        test.get().fail(result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(getScreensShot(methodName),methodName).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        String methodName = result.getMethod().getMethodName();
        test.get().skip("Test skipped");
        test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreensShot(methodName),methodName).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public WebDriver getDriver(WebDriver driver){
        return this.driver =driver;
    }

    public static ThreadLocal<ExtentTest>  getLogger(){
        return test;
    }

    public String getScreensShot(String methodName) {
        String path=null;
        try {
            File srcfile = ((TakesScreenshot) getDriver(driver)).getScreenshotAs(OutputType.FILE);
            path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
                    + ".png";
            File DestFile = new File(path);
            FileUtils.copyFile(srcfile, DestFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

}
