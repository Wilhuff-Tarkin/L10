package widgets;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Iterator;
import java.util.List;

public class AutocompleteTest extends TestBase {

    private static final String path = "https://seleniumui.moderntester.pl/autocomplete.php";
    private static final Logger log = LoggerFactory.getLogger(AutocompleteTest.class);


    @Test
    void autoCompleteSelectTest() {
        driver.get(path);
        WebElement searchbar = driver.findElement(By.cssSelector("#search"));

        searchbar.sendKeys("a");

        List<WebElement> autocompleteOptions = driver.findElements(By.cssSelector("#ui-id-1 li"));
        autocompleteOptions.removeIf(next -> next.getAttribute("class").equals("ui-autocomplete-category"));

        log.info("There are " + autocompleteOptions.size() + " options:");

        for (WebElement autocompleteOption : autocompleteOptions) {

            log.info(autocompleteOption.getText());
        }


        WebElement selectedOption = getRandomElement(autocompleteOptions);
        selectedOption.click();
        log.info(searchbar.getText());
        log.info(selectedOption.getText());
        assertThat("Selection not as expected", searchbar.getText().equals(selectedOption.getText()));


    }
}