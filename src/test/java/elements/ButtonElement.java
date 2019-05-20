package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ButtonElement extends PageElement {
    public ButtonElement(WebDriver driver, By by) {
        super(driver, by);
    }

    public void click() {
        element.click();
    }
}
