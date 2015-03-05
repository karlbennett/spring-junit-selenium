package exammples.spring.junit.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Result {

    private final WebElement element;

    public Result(WebElement element) {
        this.element = element;
    }

    public String getHeading() {

        final WebElement header = element.findElement(By.className("r"));

        final WebElement link = header.findElement(By.tagName("a"));

        return link.getText();
    }

    public String getDescription() {

        final WebElement description = element.findElement(By.className("st"));

        return description.getText();
    }
}
