package exammples.spring.junit.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static java.lang.String.format;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:example.properties")
public class ExampleConfiguration {

    @Bean(destroyMethod = "quit")
    public WebDriver webDriver(@Value("${web.driver:firefox}") String webDriver) {

        if (webDriver.equals("chrome")) {
            return new ChromeDriver();
        }

        if (webDriver.equals("firefox")) {
            return new FirefoxDriver();
        }

        if (webDriver.equals("ie")) {
            return new InternetExplorerDriver();
        }

        if (webDriver.equals("safari")) {
            return new SafariDriver();
        }

        if (webDriver.equals("opera")) {
            return new OperaDriver();
        }

        throw new IllegalArgumentException(format("Browser %s not supported.", webDriver));
    }
}
