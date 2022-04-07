package interactions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;

public class DroppableTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(DroppableTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/droppable.php";

    @Test
    void shouldBeDropped() {
        driver.get(path);
        WebElement square = driver.findElement(By.cssSelector("#draggable"));
        WebElement dropZone = driver.findElement(By.cssSelector(".content #droppable"));
        Actions action = new Actions(driver);
        action.dragAndDrop(square, dropZone).build().perform();
        assertThat("Not dropped correctly", dropZone.getText().equals("Dropped!"));
        log.info("Droppable check");
    }
}
