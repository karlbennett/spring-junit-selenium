package exammples.spring.junit.selenium;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

@Component
public class SearchResults {

    private static final Pattern STATS_PATTERN = Pattern.compile("^\\w+ ([\\d,]+) .+$");

    private final WebDriver driver;
    private final WebDriverWait wait;

    @Autowired
    public SearchResults(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public int total() {

        final WebElement stats = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.id("resultStats"));
            }
        });

        final Matcher matcher = STATS_PATTERN.matcher(stats.getText());
        matcher.matches();

        final String total = matcher.group(1);

        return Integer.valueOf(total.replaceAll(",", ""));
    }

    public Result get(final URL url) {

        return new Result(wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.xpath(format("//a[@href='%s']/ancestor::li[@class='g']", url)));
            }
        }));
    }

}
