import FileUtils.FileReaderUtils;
import Pages.CategoryPage;
import Pages.MainPage;
import Pages.ProductPage;
import Test.AbstractTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**Test case:
 * 1. read category names from specified file
 * 2. go to each category
 * 3. inside the category, click on each product
 * 4. inside each product page, check item availability (In stock / Out of stock / Pre-order)
 * 5. assert that item is in stock
 * 6. for all the products that have availability is not In stock, print out a message to console and fail the test:
 *      "Category [category name] , product [product name] availability is [availability]"
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

        //go to category: find element by link text (name read from file) and click on it
        mainPage.clickOnElement(mainPage.findElementByLinkText(navItem));

        //go to each product in page
       // for(WebElement productInPage: categoryPage.getListOfProductsOnPage()){
        for(int i=0;i<categoryPage.getListOfProductsOnPage().size(); i++){

            //click on the product in page
            categoryPage.getListOfProductsOnPage().get(i).click();

            //you're in.
            //now assert the availability
            //padaryti kad assert nepraejus tik isspausdintu zinute ir eitu toliau
            try{
                assertTrue(productPage.getAvailabilityText().equals("Availability: In Stock"));
            }catch (Throwable t){
                collector.addError(t);
                System.out.println("Category -"+navItem+", product -"+productPage.getProductName()+", found -'"+productPage.getAvailabilityText()+
                        "' while 'In stock' was expected.");
            }

            //asserted, message printed.
            //go back to category.
            driver.navigate().back();
        }

    }


}

}
