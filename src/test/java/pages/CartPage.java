package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private final By addToCartButton = By.xpath("//a[@id='atcBtn_btn_1']");
    private final By cartCount = By.xpath("//span[@class='badge']");

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getCartCount() {
        return driver.findElement(cartCount).getText();
    }
}
