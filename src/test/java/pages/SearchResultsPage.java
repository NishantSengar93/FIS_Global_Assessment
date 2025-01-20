package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SearchResultsPage {
    private final By firstItem = By.xpath("(//ul[@class='srp-results srp-list clearfix']/descendant::div[@class='s-item__image-section'])[1]");

    private WebDriver driver;


    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstItem()  {
        driver.findElement(firstItem).click();
    }
}
