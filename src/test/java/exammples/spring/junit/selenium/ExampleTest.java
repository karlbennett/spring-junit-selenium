package exammples.spring.junit.selenium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ExampleConfiguration.class)
public class ExampleTest {

    @Autowired
    private Google google;

    @Test
    public void I_can_do_a_search_on_google() throws MalformedURLException {

        // Given
        google.visit();

        // When
        final SearchResults results = google.search("kittens");

        // Then
        assertThat(results.total(), greaterThan(0));

        final Result result = results.get(new URL("http://en.wikipedia.org/wiki/Kitten"));

        assertThat(result, not(nullValue()));
        assertThat(result.getHeading(), equalTo("Kitten - Wikipedia, the free encyclopedia"));
        assertThat(result.getDescription(), containsString("A kitten or kitty is a juvenile domesticated cat."));
    }
}
