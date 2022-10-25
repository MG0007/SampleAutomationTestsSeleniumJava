import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoAutomation {

    private GetWebDriverAndUse helper = new GetWebDriverAndUse();
    private Constants constants = new Constants();
    public static WebDriver driver;

    @BeforeTest
    public void setCorrectBrowser(){
        driver = helper.createCorrectWebDriver("chrome");
        driver.manage().window().maximize();
    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void testHomePageTitle() {
        driver.get(constants.DESIRED_WEBSITE);
        String actualTitle = driver.getTitle();
        Assert.assertEquals(constants.EXPECTED_HOMEPAGE_TITLE, actualTitle);
    }
}
