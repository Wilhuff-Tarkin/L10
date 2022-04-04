package widgets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class AccordionTest extends TestBase {

    private static final String path = "https://seleniumui.moderntester.pl/accordion.php";
    private static final Logger log = LoggerFactory.getLogger(AccordionTest.class);


    @Test
    void accordionTest() {

        driver.get(path);
        List<WebElement> sections = driver.findElements(By.cssSelector("#accordion h3"));
        Assertions.assertNotEquals(0, sections.size());

        for (WebElement sectionHeader : sections) {
            if (!Objects.equals(sectionHeader.getAttribute("aria-hidden"), "false")) {
                sectionHeader.click();
            }
            log.info(sectionHeader.getText());
            String sectionText = getSectionText(sectionHeader.getAttribute("aria-controls"));
            log.info(sectionText);
        }
        log.info(">>>>>  Accordion check");
    }

    private String getSectionText(String sectionId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sectionText = driver.findElement(By.cssSelector("#" + sectionId));
        wait.until(ExpectedConditions.visibilityOfAllElements(sectionText.findElements(By.cssSelector("*"))));
        return sectionText.getText();
    }
}
