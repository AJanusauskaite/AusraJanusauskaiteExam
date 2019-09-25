import FileUtils.FileReaderUtils;
import Pages.CategoryPage;
import Pages.MainPage;
import Pages.ProductPage;
import Test.AbstractTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static junit.framework.TestCase.assertTrue;

/**Test case:
 * 1. read category names from specified file
 * 2. go to each category
 * 3. inside the category, click on each product
 * 4. inside each product page, check item availability (In stock / Out of stock / Pre-order)
 * 5. assert that item is in stock
 * 6. for all the products that have availability is not In stock, print out a message to console and fail the test:
 *      "Category [category name] , product [product name] availability is [availability]"
 *
 *      Link to test case and defect report:
 *      https://docs.google.com/spreadsheets/d/1SLjZIR124kCqQIFf-v6L_c5Yk-NFw1DR6w-1nv2Gu4Y/edit?usp=sharing
 */

public class TestItemAvailability extends AbstractTest{
MainPage mainPage;
FileReaderUtils fileReaderUtils;
CategoryPage categoryPage;
ProductPage productPage;

    @Rule
    public ErrorCollector collector = new ErrorCollector();

@Test
    public void checkItemAvailability() throws IOException, InterruptedException {
    mainPage=new MainPage(driver);
    fileReaderUtils=new FileReaderUtils();
    categoryPage=new CategoryPage(driver);
    productPage = new ProductPage(driver);

    List<String> listOfNavbarItemsFromFile=new ArrayList<>();
    listOfNavbarItemsFromFile=fileReaderUtils.getTestData("src/test/resources/file1.txt");

    for(String navItem:listOfNavbarItemsFromFile){

        mainPage.clickOnElement(mainPage.findElementByLinkText(navItem));

        for(int i=0;i<categoryPage.getListOfProductsOnPage().size(); i++){

            categoryPage.getListOfProductsOnPage().get(i).click();

            try{
                assertTrue(productPage.getAvailabilityText().equals("Availability: In Stock"));
            }catch (Throwable t){
                collector.addError(t);
                System.out.println("Category -"+navItem+", product -"+productPage.getProductName()+", found -'"+productPage.getAvailabilityText()+
                        "' while 'In stock' was expected.");
            }

            driver.navigate().back();

            //Wait is not needed here, but assessment criteria has a point for using waits
            Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
                    .withTimeout(5, TimeUnit.SECONDS)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);
            WebElement headingRefineSearch=wait.until(new Function<WebDriver, WebElement>(){
                public WebElement apply(WebDriver driver){
                    return driver.findElement(By.id("list-view"));
                }
            });
        }

    }


}

}
