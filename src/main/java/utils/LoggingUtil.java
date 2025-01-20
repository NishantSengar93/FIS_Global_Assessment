package utils;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class LoggingUtil {

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    /**
     * Logs a message to ExtentReport with a screenshot beside the log.
     *
     * @param driver   The WebDriver instance.
     * @param test     The ExtentTest instance.
     * @param logger   The Log4j logger instance.
     * @param message  The message to log.
     */
    public static void logWithScreenshot(WebDriver driver, ExtentTest test, Logger logger, String message) {
        // Capture screenshot as Base64
        String screenshotBase64 = ScreenshotUtil.captureScreenshotAsBase64(driver);

        // Log message with screenshot in ExtentReport
        test.log(com.aventstack.extentreports.Status.INFO,
                message + " <br><img src='data:image/png;base64," + screenshotBase64 + "' height='400' width='200'/>");

        // Log message in Log4j
        logger.info(message);
    }
}
