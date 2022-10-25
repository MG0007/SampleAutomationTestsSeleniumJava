import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    @Test
    public void verifyDocTitle() {
        String docTitle = driver.findElement(By.xpath(constants.DOC_TITLE_XPATH)).getText();
        Assert.assertEquals(constants.DOC_TITLE, docTitle);
    }

    @Test
    public void verifyDocTitleFontSize(){
        WebElement docTitle = driver.findElement(By.xpath(constants.DOC_TITLE_XPATH));
        String docTitleFontSize = docTitle.getCssValue("font-size");
        Assert.assertEquals(constants.DOC_TITLE_FONTSIZE, docTitleFontSize);
    }

    @Test
    public void verifyDocTitleFontColor(){
        WebElement docTitle = driver.findElement(By.xpath(constants.DOC_TITLE_XPATH));
        String docTitleColor = docTitle.getCssValue("color");
        Assert.assertEquals(constants.DOC_TITLE_COLOR, docTitleColor);
    }
    @Test
    public void verifyDocSubTitle(){
        String docSubTitle = driver.findElement(By.xpath(constants.DOC_SUBTITLE_XPATH)).getText();
        Assert.assertEquals(docSubTitle, constants.DOC_SUBTITLE);
    }
}
