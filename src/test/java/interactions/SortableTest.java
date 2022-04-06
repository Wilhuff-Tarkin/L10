package interactions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class SortableTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(SortableTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/sortable.php";

    @Test
    void shouldSetPutListInNewOrder() {
        List<WebElement> orderBefore = getList();

        List<WebElement> shuffledItems = getList();
        Collections.shuffle(shuffledItems);

        for (int i = 0; i < orderBefore.size(); i++) {

            int listPosition = i + 1;
            String dropPositionSelector = "#sortable li:nth-child(" + listPosition + ")";

            WebElement pickup = shuffledItems.get(i);
            WebElement drop = driver.findElement(By.cssSelector(dropPositionSelector));

            Actions actions = new Actions(driver);
            actions.dragAndDrop(pickup, drop).perform();
        }
        log.info("Shuffled order:");
        logListContent(shuffledItems);
        log.info("List after test order");
        List<WebElement> afterOrder = driver.findElements(By.cssSelector("#sortable li"));
        logListContent(afterOrder);
        assertThat("Order not as expected", afterOrder.equals(shuffledItems));
    }

    private void logListContent(List<WebElement> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (WebElement element : list) {
            stringBuilder.append(element.getText()).append(" ");
        }
        log.info(String.valueOf(stringBuilder));
    }
    private List<WebElement> getList() {
        driver.get(path);
        return driver.findElements(By.cssSelector("#sortable li"));
    }
}
