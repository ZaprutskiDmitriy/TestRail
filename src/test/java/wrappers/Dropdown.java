package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Dropdown {

    WebDriver driver;
    String label;
    String dropdownLocator = "//label[contains(text(),'%s')]/following::div[contains(@class,'chzn-container')]";
    String optionLocator = "//li[contains(text(),'%s')]";

    public Dropdown(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        log.debug("Selecting option '{}' in dropdown under the label '{}'", option, label);
        driver.findElement(By.xpath(String.format(dropdownLocator, label))).click();
        driver.findElement(By.xpath(String.format(optionLocator, option))).click();
    }
}