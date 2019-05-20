package objects;

import elements.ButtonElement;
import elements.TextElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageObject {

    private static final String USERNAME = "123";
    private static final String PASSWORD = "123";

    private TextElement login;
    private TextElement password;
    private ButtonElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        login = new TextElement(driver, By.id("id_l.L.login"));
        password = new TextElement(driver, By.id("id_l.L.password"));
        loginButton = new ButtonElement(driver, By.id("id_l.L.loginButton"));

    }

    public void login() {
        login.setText(USERNAME);
        password.setText(PASSWORD);
        loginButton.click();
    }
}
