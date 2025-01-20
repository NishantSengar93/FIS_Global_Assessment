package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SearchResultsPage {
    private By firstItem = By.xpath("(//ul[@class='srp-results srp-list clearfix']/descendant::div[@class='s-item__image-section'])[1]");

    private WebDriver driver;


    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstItem() throws InterruptedException {
        driver.findElement(firstItem).click();
    }
}
