import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class DemoAutomation {

    private GetWebDriverAndUse helper = new GetWebDriverAndUse();

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
}
