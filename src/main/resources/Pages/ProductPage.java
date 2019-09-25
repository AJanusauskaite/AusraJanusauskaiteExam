package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends AbstractPage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#content>div>div:nth-child(2)>ul>li:last-child")
    private WebElement availability;

    @FindBy(css = "#content h1")
    private WebElement productName;

    public String getAvailabilityText(){
        return availability.getText();
    }

    public String getProductName(){
        return productName.getText();
    }
}
