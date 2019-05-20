package objects;

import org.openqa.selenium.WebDriver;

abstract class PageObject {
    private WebDriver driver;

    PageObject(WebDriver driver) {
        this.driver = driver;
    }
}
