package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryPage extends AbstractPage {
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getListOfProductsOnPage(){
        List<WebElement> listOfProductsOnPage=driver.findElements(By.cssSelector("#content div.product-thumb h4 a"));
        return listOfProductsOnPage;
    }
}
