package widgets;

import basic.FormTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.util.List;

public class TooltipTest extends TestBase {
    private static final Logger log = LoggerFactory.getLogger(FormTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/tooltip.php";

    @Test
    void shouldReadTooltips() {
        driver.get(path);
        List<WebElement> titleTooltips = driver.findElements(By.cssSelector(".content.tooltips a"));
        titleTooltips.add(driver.findElement(By.cssSelector("#age")));

        for (int i = 0; i < titleTooltips.size(); i++) {
            int counter = i+1;
            log.info("Tooltip " + counter + " of " + titleTooltips.size());
            log.info(titleTooltips.get(i).getAttribute("title"));
        }
        log.info(">>>>>  Tooltips check");
    }
}
