package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        driver.findElement(By.xpath(String.format(dropdownLocator, label))).click();
        driver.findElement(By.xpath(String.format(optionLocator, option))).click();
    }
}