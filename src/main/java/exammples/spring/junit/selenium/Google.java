package exammples.spring.junit.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Google {

    private final WebDriver driver;
    private final SearchResults results;

    @Autowired
    public Google(WebDriver driver, SearchResults results) {
        this.driver = driver;
        this.results = results;
    }

    public void visit() {
        driver.get("https://www.google.com");
    }

    public SearchResults search(String text) {

        driver.findElement(By.name("q")).sendKeys(text);

        driver.findElement(By.name("btnG")).click();

        return results;
    }
}
