import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

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
    @Test
    public void verifyTitleSubTitleVerticalAlignment(){
        WebElement docTitle = driver.findElement(By.xpath(constants.DOC_TITLE_XPATH));
        int xDocTitle = helper.getXDocTitle(driver, constants.DOC_TITLE_XPATH);

        WebElement docSubtitle = driver.findElement(By.xpath(constants.DOC_SUBTITLE_XPATH));
        int xSubTitleElement = docSubtitle.getLocation().getX();

        Assert.assertEquals(xSubTitleElement, xDocTitle);
    }
    @Test
    public void verifyPageLinksVerticallyAligned(){
        List<WebElement> links = driver.findElements(By.xpath(constants.LINKS_XPATH));
        int xDocTitle = helper.getXDocTitle(driver, constants.DOC_TITLE_XPATH);
        boolean linksAligned = helper.allLinksAreAligned(links, xDocTitle);

        Assert.assertTrue( linksAligned);
    }
    @Test
    public void testLogin() throws InterruptedException {
        String originalWindowHandle = driver.getWindowHandle();
        driver.findElement(By.linkText("Digest Authentication")).click();

        String userNameAndPassword = "admin";
        String target = "https://" + userNameAndPassword + ":" + userNameAndPassword + "@" + "the-internet.herokuapp.com/digest_auth";

        driver.get(target);
        String result = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p")).getText();
        driver.get(constants.DESIRED_WEBSITE);
        Assert.assertEquals(result, "Congratulations! You must have the proper credentials.");

    }
}
