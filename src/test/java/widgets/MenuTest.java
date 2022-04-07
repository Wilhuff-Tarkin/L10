package widgets;

import basic.FormTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.time.Duration;

public class MenuTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(MenuTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/menu-item.php";

    @Test
    void shouldEnterJazz() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(path);
        WebElement music = driver.findElement(By.cssSelector("#ui-id-9"));
        wait.until(ExpectedConditions.visibilityOf(music));
        music.click();
        WebElement jazz = driver.findElement(By.cssSelector("#ui-id-13"));
        wait.until(ExpectedConditions.visibilityOf(jazz));
        jazz.click();
        WebElement modern = driver.findElement(By.cssSelector("#ui-id-16"));
        wait.until(ExpectedConditions.visibilityOf(modern));
        modern.click();
        log.info(">>>>>  Menu test check");
    }
}
