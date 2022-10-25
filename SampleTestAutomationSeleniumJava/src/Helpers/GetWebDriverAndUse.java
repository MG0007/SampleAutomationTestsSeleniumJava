import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

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

    public int getXDocTitle(WebDriver driver, String xpath){
        WebElement docTitle = driver.findElement(By.xpath(xpath));
        return docTitle.getLocation().getX();
    }
    public boolean allLinksAreAligned(List<WebElement> links, int xPageTitle){
        boolean linksAligned = true;
        for (int i = 0; i < links.size() ; i++){
            int xLink = links.get(i).getLocation().getX();

            if (xLink != xPageTitle){
                linksAligned = false;
                break;
            }
        }
        return linksAligned;
    }
}
