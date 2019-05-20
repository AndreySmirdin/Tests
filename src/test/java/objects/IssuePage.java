package objects;

import elements.ButtonElement;
import elements.TextElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * A class that is responsible for creating new issues for testing purposes.
 * Note that we assume here that the project was already created.
 */
public class IssuePage extends PageObject {

    private TextElement summaryField;
    private TextElement descriptionField;

    private ButtonElement submitButton;

    public IssuePage(WebDriver driver) {
        super(driver);
        summaryField = new TextElement(driver, By.className("edit-summary"));
        descriptionField = new TextElement(driver, By.className("username-suggest"));
        submitButton = new ButtonElement(driver, By.className("submit-btn"));
    }

    public void createIssue(String description, String summary) {


        summaryField.setText(summary);
        descriptionField.setText(description);
    }

    public void submitIssue() {
        submitButton.click();
    }
}
