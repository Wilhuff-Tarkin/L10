package interactions;

import basic.FormTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public class DraggableTest extends TestBase {


    private static final Logger log = LoggerFactory.getLogger(DraggableTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/draggable.php";


//    Dimension currentDimension = driver.manage().window().getSize();
//    int height = currentDimension.getHeight();
//    int width = currentDimension.getWidth();
//        log.info("Current screen dimensions are " + height + " " + width);
//
//
//    Dimension newDimension = new Dimension(800, 600);
//        driver.manage().window().setSize(newDimension);


    @Test
    void shouldDragElements() throws InterruptedException {

        driver.get(path);

        WebElement square = driver.findElement(By.cssSelector("#draggable"));

        Actions action = new Actions(driver);

        action.dragAndDropBy(square, 500, 0).build().perform();
        Thread.sleep(250);

        action.dragAndDropBy(square, 0, 200).build().perform();
        Thread.sleep(250);

        action.dragAndDropBy(square, -500, 0).build().perform();
        Thread.sleep(250);

        action.dragAndDropBy(square, 150, -150).build().perform();
        Thread.sleep(250);

        log.info("Draggable check");
    }



}
