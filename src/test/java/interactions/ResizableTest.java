package interactions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;

public class ResizableTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(ResizableTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/resizable.php";

    @Test
    public void shouldResize10toTheRight() {
        driver.get(path);
        WebElement resizableSquare = driver.findElement(By.cssSelector("#resizable"));
        WebElement xAxisHandle = driver.findElement(By.cssSelector(".ui-resizable-handle.ui-resizable-e"));
        int initialWidth = resizableSquare.getSize().width;
        int expectedWidth = initialWidth + 10;
        Actions action = new Actions(driver);

        while (resizableSquare.getSize().width != expectedWidth) {
            action.clickAndHold(xAxisHandle).moveByOffset(19, 0).release().build().perform();
        }
        assertThat("Not resized as expected", expectedWidth == resizableSquare.getSize().width);
        log.info("Resize on x axis check");
    }

    @Test
    void shouldResize10toTheBottom() {
        driver.get(path);
        WebElement yAxisHandle = driver.findElement(By.cssSelector(".ui-resizable-handle.ui-resizable-s"));
        WebElement resizableSquare = driver.findElement(By.cssSelector("#resizable"));
        int initialHeight = resizableSquare.getSize().height;
        int expectedHeight = initialHeight + 10;
        Actions action = new Actions(driver);

        while (resizableSquare.getSize().height != expectedHeight) {
            action.clickAndHold(yAxisHandle).moveByOffset(0, 19).release().build().perform();
        }
        assertThat("Not resized as expected", expectedHeight == resizableSquare.getSize().height);
        log.info("Resize on y axis check");
    }

    @Test
    void shouldResize10onBothAxis() {
        driver.get(path);
        WebElement resizableSquare = driver.findElement(By.cssSelector("#resizable"));
        int initialHeight = resizableSquare.getSize().height;
        int expectedHeight = initialHeight + 10;
        int initialWidth = resizableSquare.getSize().width;
        int expectedWidth = initialWidth + 10;
        Actions action = new Actions(driver);
        WebElement xyAxisHandle = driver.findElement(By.cssSelector(".ui-icon-gripsmall-diagonal-se"));

        while (resizableSquare.getSize().height != expectedHeight || resizableSquare.getSize().width != expectedWidth) {
            action.clickAndHold(xyAxisHandle).moveByOffset(19, 19).release().build().perform();
        }
        assertThat("Not resized as expected", expectedHeight == resizableSquare.getSize().height && expectedWidth == resizableSquare.getSize().width);
        log.info("Resize on both axis check");
    }
}
