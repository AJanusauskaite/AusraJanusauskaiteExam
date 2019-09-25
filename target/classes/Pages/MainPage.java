package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AbstractPage {
    public MainPage(WebDriver driver) {
        super(driver);
    }




    public WebElement findElementByLinkText(String linkText){
        return driver.findElement(By.linkText(linkText));
    }

    public void clickOnElement(WebElement elementToClickOn){
        elementToClickOn.click();
    }
}
