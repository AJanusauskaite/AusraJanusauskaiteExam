package Test;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class AbstractTest {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUpBrowser(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver=new ChromeDriver();
    }


    @AfterClass
    public static void closeBrowser(){
    driver.quit();
    }

    @Before
    public void goToPage(){
        driver.get("http://192.168.1.178/opencartone/");
    }


    public WebDriver getDriver(){
        return this.driver;
    }


}
