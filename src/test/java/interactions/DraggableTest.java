package interactions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.awt.*;

public class DraggableTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(DraggableTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/draggable.php";

    @Test
    void shouldDragElements() throws InterruptedException {
        driver.get(path);
        WebElement square = driver.findElement(By.cssSelector("#draggable"));
        Actions action = new Actions(driver);

        action.dragAndDropBy(square, getPositionOffset(Directions.UPPER_RIGHT).x, getPositionOffset(Directions.UPPER_RIGHT).y).perform();
        Thread.sleep(100);

        action.dragAndDropBy(square, getPositionOffset(Directions.BOTTOM_RIGHT).x, getPositionOffset(Directions.BOTTOM_RIGHT).y).perform();
        Thread.sleep(100);

        action.dragAndDropBy(square, getPositionOffset(Directions.CENTER).x, getPositionOffset(Directions.CENTER).y).perform();
        Thread.sleep(100);

        action.dragAndDropBy(square, getPositionOffset(Directions.BOTTOM_LEFT).x, getPositionOffset(Directions.BOTTOM_LEFT).y).perform();
        Thread.sleep(100);

        log.info("Draggable check");
    }

    public Point getPositionOffset(Directions direction) {

        WebElement square = driver.findElement(By.cssSelector("#draggable"));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        switch (direction) {
            case BOTTOM_LEFT:
                return new Point(-(square.getLocation().x - square.getSize().width), (screenSize.height - square.getSize().width) - square.getLocation().y - 100);
            case BOTTOM_RIGHT:
                return new Point(0, (screenSize.height - square.getSize().height - square.getLocation().y - 100));
            case CENTER:
                return new Point((Math.floorDiv(screenSize.width, 2) - Math.floorDiv(square.getSize().width, 2) - square.getLocation().x),
                        (Math.floorDiv(screenSize.height, 2) - Math.floorDiv(square.getSize().height, 2)) - square.getLocation().y);
            case UPPER_RIGHT:
                return new Point((screenSize.width - square.getSize().width - square.getLocation().getX()), 0);
            default:
                throw new IllegalArgumentException("Illegal position value " + direction);
        }
    }

    public enum Directions {
        UPPER_RIGHT, BOTTOM_RIGHT, CENTER, BOTTOM_LEFT;
    }
}
