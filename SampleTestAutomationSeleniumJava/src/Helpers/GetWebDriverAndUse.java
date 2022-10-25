import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GetWebDriverAndUse {

    private String validateBrowserParameterValid(String browser){
        String browserNormalized;

        if (browser == null || browser.isEmpty() || browser.trim().isEmpty()){
            throw new IllegalArgumentException("Please specify correct browser: chrome, firefox, edge");
        }else {
            browserNormalized = browser.toLowerCase();
        }

        return  browserNormalized;
    }

    public WebDriver createCorrectWebDriver(String browser){
        WebDriver driver;
        String browserToUse = validateBrowserParameterValid(browser);

        if (browserToUse.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (browserToUse.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return  new FirefoxDriver();
        } else if (browserToUse.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        }else {
            throw new IllegalArgumentException("Can't create correct browser");
        }
    }
}
