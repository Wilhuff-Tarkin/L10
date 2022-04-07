package widgets;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;

public class SliderTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(SliderTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/slider.php";

    @Test
    void shouldMovieSlider() throws InterruptedException {
        driver.get(path);
        WebElement handle = driver.findElement(By.cssSelector("#custom-handle"));
        moveSliderToTheRight(50, handle);
        moveSliderToTheRight(80, handle);
        moveSliderToTheRight(80, handle);
        moveSliderToTheLeft(20, handle);
        moveSliderToTheLeft(0, handle);
        log.info(">>>>>  Slider check");
    }

    private void moveSliderToTheLeft(int expectedPosition, WebElement handle) throws InterruptedException {
        int currentPosition = getHandlePosition(handle);
        handle.click();
        while (currentPosition > expectedPosition) {
            handle.sendKeys(Keys.ARROW_LEFT);
            currentPosition--;
        }
        assertThat("Position not as expected", Integer.valueOf(handle.getText()).equals(expectedPosition));
        Thread.sleep(200);
    }

    private void moveSliderToTheRight(int expectedPosition, WebElement handle) throws InterruptedException {
        int currentPosition = getHandlePosition(handle);
        handle.click();
        while (currentPosition < expectedPosition) {
            handle.sendKeys(Keys.ARROW_RIGHT);
            currentPosition++;
        }
        assertThat("Position not as expected", Integer.valueOf(handle.getText()).equals(expectedPosition));
        Thread.sleep(100);
    }

    private int getHandlePosition(WebElement handle) {
        return Integer.parseInt(handle.getText());
    }
}
