package ui;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.CartPage;
import utils.DriverManager;
import utils.ExtentManager;
import utils.LoggingUtil;
import utils.TestListener;

import java.util.ArrayList;


@Listeners(TestListener.class)
public class AddToCartTest {
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage resultsPage;
    private CartPage cartPage;
    private ExtentReports extent;
    private ExtentTest test;
    private Logger logger;

    @Parameters("browser")
    @BeforeTest
    public void setup(String browser) {
        // Initialize ExtentReports and Logger
        extent = ExtentManager.getUIInstance();
        test = extent.createTest("UI Test Report");
        logger = LoggingUtil.getLogger(AddToCartTest.class);


        // Initialize WebDriver using DriverManager (chrome, firefox, edge, safari)
        driver = DriverManager.getDriver(browser);
        driver.get("https://www.ebay.com");

        // Initialize page objects
        homePage = new HomePage(driver);
        resultsPage = new SearchResultsPage(driver);
        cartPage = new CartPage(driver);

        test.log(Status.INFO, "Browser started: " + browser);
    }

    @Test(description = "Verify book added to cart")
    public void testAddToCart() {
        try {
            // Step 1: Search for a book
            LoggingUtil.logWithScreenshot(driver, test, logger, "Searching for a book");
            homePage.searchItem("book");

            // Step 2: Click on the first item
            LoggingUtil.logWithScreenshot(driver, test, logger, "Clicking on the first item");
            resultsPage.clickFirstItem();
            handleNewTab();

            // Step 3: Add to cart
            LoggingUtil.logWithScreenshot(driver, test, logger, "Adding item to cart");
            cartPage.addToCart();

            // Step 4: Verify cart count
            String cartCount = cartPage.getCartCount();
            LoggingUtil.logWithScreenshot(driver, test, logger, "Cart updated successfully with count: " + cartCount);
            Assert.assertEquals(cartCount,"1" );
            test.pass("Cart updated successfully with item count: " + cartCount);

        } catch (Exception e) {
            LoggingUtil.logWithScreenshot(driver, test, logger, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @AfterTest
    public void teardown() {
       DriverManager.quitDriver();
        extent.flush();
    }


    private void handleNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); // Switch to the new tab (index 1)
        test.log(Status.INFO, "Switched to the new tab.");
    }
}
