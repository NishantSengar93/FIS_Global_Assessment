package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    /**
     * Get an ExtentReports instance for UI tests.
     *
     * @return ExtentReports instance.
     */
    public static ExtentReports getUIInstance() {
        if (extent == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/reports/Test_Execution_Report.html");
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setDocumentTitle("UI Test Report");
            htmlReporter.config().setReportName("UI Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }
}

