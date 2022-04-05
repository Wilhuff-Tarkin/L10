package basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.util.List;

public class TablesTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(TablesTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/table.php";

    @Test
    public void shouldPrintSpecifMountains() {
        driver.get(path);
        List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody tr"));
        log.info("Mountains that are in Switzerland and are higher than 4000m:");

        for (WebElement row : tableRows) {

            String rank = row.findElements(By.cssSelector("th")).get(0).getText();
            String peakName = row.findElements(By.cssSelector("td")).get(0).getText();
            String range = row.findElements(By.cssSelector("td")).get(1).getText();
            String state = row.findElements(By.cssSelector("td")).get(2).getText();
            String height = row.findElements(By.cssSelector("td")).get(3).getText();

            if (state.contains("Switzerland") && Integer.parseInt(height) > 4000) {
                log.info(rank + ". " + peakName + " " + "(" + range + ")");
            }
        }
    }
}
