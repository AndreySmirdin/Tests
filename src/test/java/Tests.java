import objects.IssuePage;
import objects.LoginPage;
import objects.MainScreen;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
//        driver.quit();
    }


    @Before
    public void setUp() {
        new LoginPage(driver).login();
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.openIssuePage();
    }

    @After
    public void tearDown() {
//        driver.get("localhost:" + PORT + "/login");
    }

    @Test
    public void testCorrectInput() {
        IssuePage issue = new IssuePage(driver);
        issue.createIssue("", "1111");
        issue.submitIssue();
        String url = driver.getCurrentUrl();
        System.out.println(url);
        Assert.assertTrue(url.contains("localhost:8080/issues"));
    }

    @Test
    public void testEmptySummary() {
        IssuePage issue = new IssuePage(driver);
        issue.createIssue("", "");
        issue.submitIssue();
    }


    @Test
    public void testEmptyDescription() {
        IssuePage issue = new IssuePage(driver);
        issue.createIssue("", "");
        issue.submitIssue();
    }

    @Test
    public void testIssueWithRussianName() {
        IssuePage issue = new IssuePage(driver);
        issue.createIssue("новое ишью", "как же его закрыть");
        issue.submitIssue();
    }
}
