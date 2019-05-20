import elements.TextElement;
import objects.IssuePage;
import objects.LoginPage;
import objects.MainScreen;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Tests {
    private static final int PORT = 8080;
    private static WebDriver driver;

    @BeforeClass
    public static void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "chromeDriver");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("localhost:" + PORT);
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }

    @Before
    public void setUp() {
        new LoginPage(driver).login();
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.openIssuePage();
    }

    @After
    public void tearDown() {
        driver.get("localhost:" + PORT + "/login");
    }

    @Test
    public void testCorrectInput() {
        testSuccessfulIssue("hello", "world");
    }

    @Test
    public void testEmptyDescription() {
        testSuccessfulIssue("", "hello");
    }

    @Test
    public void testEmptySummary() {
        IssuePage issue = new IssuePage(driver);
        issue.createIssue("", "");
        issue.submitIssue();

        Wait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("error")));
    }

    @Test
    public void testIssueWithRussianName() {
        testSuccessfulIssue("новое ишью", "как же его закрыть");
    }

    private void testSuccessfulIssue(String description, String summary) {
        IssuePage issue = new IssuePage(driver);
        issue.createIssue(description, summary);
        issue.submitIssue();

        checkCorrectIssue(description, summary);
    }

    private void checkCorrectIssue(String description, String summary) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fsi-content")));

        TextElement summaryElement = new TextElement(driver, By.className("issue-summary_fsi"));
        Assert.assertEquals(summary, summaryElement.getText());

        TextElement descriptionElement = new TextElement(driver, By.className("text"));
        Assert.assertEquals(description, descriptionElement.getText());
    }
}
