package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    /**
     * Captures a screenshot and returns it as a Base64 string for embedding in ExtentReport.
     *
     * @param driver The WebDriver instance.
     * @return The Base64 string of the screenshot.
     */
    public static String captureScreenshotAsBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
