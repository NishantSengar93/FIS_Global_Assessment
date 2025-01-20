package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private final Logger logger = LogManager.getLogger(TestListener.class);

    public void onTestStart(ITestResult result) {
        logger.info("Started...!!! "+result.getMethod().getMethodName());
        logger.info("Description : "+result.getMethod().getDescription());

    }

    public void onTestSuccess(ITestResult result) {
        logger.info("Passed...!!! "+result.getMethod().getMethodName());
        logger.info("Description : "+result.getMethod().getDescription());
    }

    public void onTestFailure(ITestResult result) {
        logger.error("Failed...!!! "+result.getMethod().getMethodName());
    }

    public void onTestSkipped(ITestResult result) {
        logger.error("Skipped...!!! "+result.getMethod().getMethodName());
    }

    public void onStart(ITestContext context) {
        logger.info("Test Suite Started..!!!");
    }

    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed..!!!");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }


}
