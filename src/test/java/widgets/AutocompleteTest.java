package widgets;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

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
        String selectedOptionText = selectedOption.getText();
        selectedOption.click();
        assertThat("Selection not as expected", searchbar.getAttribute("value").equals(selectedOptionText));
        log.info("Autocomplete test check");
    }
}