package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextElement extends PageElement {
    public TextElement(WebDriver driver, By by) {
        super(driver, by);
    }

    public void setText(String text) {
        element.sendKeys(text);
    }
}
