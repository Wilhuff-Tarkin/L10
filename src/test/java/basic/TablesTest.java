package basic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.util.List;

public class TablesTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(TablesTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/table.php";

    @ParameterizedTest
    @CsvSource({"Switzerland, 4000", "Italy,4200", "France , 4103 "})
    public void shouldPrintSpecifMountains(String country, int height) {
        driver.get(path);
        List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody tr"));
        log.info("Mountains that are in " + country + " and are higher than " + height + "m:");

        for (WebElement row : tableRows) {

            String rank = row.findElements(By.cssSelector("th")).get(0).getText();
            String peakName = row.findElements(By.cssSelector("td")).get(0).getText();
            String range = row.findElements(By.cssSelector("td")).get(1).getText();
            String state = row.findElements(By.cssSelector("td")).get(2).getText();
            String heightOfPeak = row.findElements(By.cssSelector("td")).get(3).getText();

            if (state.contains(country) && Integer.parseInt(heightOfPeak) > height) {
                log.info(rank + ". " + peakName + " " + "(" + range + ")");
            }
        }
        log.info(">>>>>  Getting data from table check");
    }
}
