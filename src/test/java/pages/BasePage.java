package pages;

import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

public abstract class BasePage {

    public static final String BASE_URL = System.getenv().getOrDefault("TESTRAIL_URL",
            PropertyReader.getProperty("testrail.url"));
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}