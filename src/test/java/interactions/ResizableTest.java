package interactions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;


public class ResizableTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(DraggableTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/resizable.php";

//
//    resize window to the right (10px)
//2.resize window to the bottom (10px)
//3.resize windwo to the right and bottom (10px,10px

    @Test
    void resizeTest() throws InterruptedException {

        driver.get(path);
        WebElement resizableSquare = driver.findElement(By.cssSelector("#resizable"));


//        int width = resizableSquare.getSize().getWidth();
        Actions action = new Actions(driver);
        action.clickAndHold(resizableSquare).moveByOffset(10, 0).release().build().perform();
        Thread.sleep(122);




    }

}
