package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private By searchBox = By.xpath("//input[@id='gh-ac']");
    private By searchButton = By.xpath("//button[@id='gh-search-btn']");


    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchItem(String item) {
        driver.findElement(searchBox).sendKeys(item);
        driver.findElement(searchButton).click();
    }
}
