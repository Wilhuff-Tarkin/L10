package widgets;

import basic.FormTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.time.Duration;

public class ProgressbarTest extends TestBase {
    private static final Logger log = LoggerFactory.getLogger(ProgressbarTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/progressbar.php";

    @Test
    public void progressBarShouldComplete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(path);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".progress-label"), "Complete!"));
        log.info("Progress bar status is: " + driver.findElement(By.cssSelector((".progress-label"))).getText());
        log.info(">>>>>  Progress bar check");
    }
}
