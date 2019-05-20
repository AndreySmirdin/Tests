package objects;

import elements.ButtonElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainScreen extends PageObject {

    private ButtonElement issueButton;

    public MainScreen(WebDriver driver) {
        super(driver);
        issueButton = new ButtonElement(driver, By.className("yt-header__create-btn"));
    }

    public void openIssuePage() {
        issueButton.click();
    }
}
