package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

abstract public class PageElement {
    WebDriver driver;
    WebElement element;

    PageElement(WebDriver driver, By by) {
        this.driver = driver;

        element = driver.findElement(by);
    }
}

