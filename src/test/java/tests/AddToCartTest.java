package tests;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.CartPage;
import utils.DriverManager;
import utils.ExtentManager;
import utils.LoggingUtil;
import java.util.ArrayList;


@Listeners(tests.TestListener.class)
public class AddToCartTest {
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage resultsPage;
    private CartPage cartPage;
    private ExtentReports extent;
    private ExtentTest test;
    private Logger logger;


    @BeforeTest
    public void setup() {
        // Initialize ExtentReports and Logger
        extent = ExtentManager.getUIInstance();
        test = extent.createTest("UI Test Report");
        logger = LoggingUtil.getLogger(AddToCartTest.class);


        // Initialize WebDriver using DriverManager (chrome, firefox, edge, safari)
        driver = DriverManager.getDriver("chrome");
        driver.get("https://www.ebay.com");

        // Initialize page objects
        homePage = new HomePage(driver);
        resultsPage = new SearchResultsPage(driver);
        cartPage = new CartPage(driver);
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
            Assert.assertEquals("1", cartCount);
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
