package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ExtentManager;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CoinDeskAPITest {

    private ExtentTest test;
    private ExtentReports extent;

    @BeforeTest
    public void setup() {


        extent= ExtentManager.getUIInstance();
        test = extent.createTest("API Test Report");
    }

    @Test
    public void validateBPIResponse() {

        try
        {

            RestAssured.baseURI = "https://api.coindesk.com";
            test.info(RestAssured.baseURI);

            test.info("Sending GET request to CoinDesk API...");
            Response response = RestAssured.get("https://api.coindesk.com/v1/bpi/currentprice.json");
            test.info(response.asPrettyString());

            assertEquals(200, response.getStatusCode());
            test.pass("API response status code verified: 200");

            // Assertions
            String gbpDescription = response.jsonPath().getString("bpi.GBP.description");


            assertEquals("British Pound Sterling", gbpDescription);
            test.pass("GBP description verified successfully.");


            assertNotNull(response.jsonPath().get("bpi.USD"));
            assertNotNull(response.jsonPath().get("bpi.GBP"));
            assertNotNull(response.jsonPath().get("bpi.EUR"));
            test.pass("Response contains required BPIs");

        } catch (Exception e) {
            test.fail("API test failed: " + e.getMessage());
        }

    }

    @AfterTest
    public void teardown() {
        extent.flush();
    }
}
