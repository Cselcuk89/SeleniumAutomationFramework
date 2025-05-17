package com.selcuk.projectListeners;

import com.selcuk.annotations.FrameworkAnnotation;
import com.selcuk.projectReports.ExtentReport;
import com.selcuk.utilities.ELKUtils;
import org.testng.*;

import java.util.Arrays;

import static com.selcuk.enums.LogType.*;
import static com.selcuk.projectReports.FrameworkLogger.log;

public class ProjectListenerClass implements ITestListener, ISuiteListener {
    /**
     * Initialise the reports with the file name

     */
    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    /**
     * Terminate the reports

     */
    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flushReports();

    }

    /**
     * Starts a test node for each testng test
     *
     */
    @Override
    public void onTestStart(ITestResult result) {

        ExtentReport.createTest(result.getMethod().getDescription());
        ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class)
                .author());
        ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class)
                .category());
    }

    /**
     * Marks the test as pass and logs it in the report

     */
    @Override
    public void onTestSuccess(ITestResult result) {
        //ExtentLogger.pass(result.getMethod().getMethodName() +" is passed");
        log(PASS,result.getMethod().getMethodName() +" is passed");
        ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "pass");
    }

    /**
     * Marks the test as fail,append base64 screenshot and logs it in the report

     */
    @Override
    public void onTestFailure(ITestResult result) {
        log(FAIL,result.getMethod().getMethodName() +" is failed");
        log(FAIL,result.getThrowable().toString());
        log(FAIL, Arrays.toString(result.getThrowable().getStackTrace()));
        ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "fail");
    }

    /**
     * Marks the test as skip and logs it in the report

     */
    @Override
    public void onTestSkipped(ITestResult result) {
        //ExtentLogger.skip(result.getMethod().getMethodName() +" is skipped");
        log(SKIP,result.getMethod().getMethodName() +" is skipped");
        ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "skip");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        /*
         * For now, we are not using this.
         */
    }

    @Override
    public void onStart(ITestContext context) {
        /*
         * We are having just one test in our suite. So we dont have any special implementation as of now
         */
    }

    @Override
    public void onFinish(ITestContext context) {
        /*
         * We are having just one test in our suite. So we dont have any special implementation as of now
         */

    }
}
