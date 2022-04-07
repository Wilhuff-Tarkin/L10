package others;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

public class HighSiteTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(HighSiteTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/high-site.php";

    @FindBy(css = "#scroll-button")
    private WebElement submitButton;

    @Test
    public void shouldScrollTillButtonVisible() {

        driver.get(path);
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        while (!buttonIsVisible()) {
            jse.executeScript("window.scrollBy(0,50)");
        }

        System.out.println("button displayed");
    }

    public boolean buttonIsVisible() {
        try {
            return submitButton.isDisplayed();
        } catch (NoSuchElementException err) {
            return false;
        }
    }

}
