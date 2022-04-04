package interactions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;


import static org.hamcrest.MatcherAssert.assertThat;

public class SelectableTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(DraggableTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/selectable.php";

    @Test
    void shouldSelectFewOptions() {

        driver.get(path);
        WebElement item1 = driver.findElement(By.cssSelector(".ui-selectable :first-child"));
        WebElement item2 = driver.findElement(By.cssSelector(".ui-selectable :nth-child(3)"));
        WebElement item3 = driver.findElement(By.cssSelector(".ui-selectable :nth-child(4)"));

        item1.click();



    }
}
