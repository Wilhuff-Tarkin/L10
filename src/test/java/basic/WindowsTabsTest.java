package basic;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;
public class WindowsTabsTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(WindowsTabsTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/windows-tabs.php";


    @Test
    void shouldOpenNewWindow() {

        driver.get(path);
        WebElement newWindowBtn = driver.findElement(By.cssSelector("#newBrowserWindow"));

        String winHandleBeforeSwitch = driver.getWindowHandle();
        newWindowBtn.click();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
//todo mountain test how to pass driver?

        driver.close();
        driver.switchTo().window(winHandleBeforeSwitch);
        log.info("New window check");

    }

    @Test
    void shouldOpenNewMessageWindow() {

        driver.get(path);
        WebElement newMessageBtn = driver.findElement(By.cssSelector("#newMessageWindow"));
        String winHandleBeforeSwitch = driver.getWindowHandle();
        newMessageBtn.click();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        log.info(driver.findElement(By.cssSelector("body")).getText());
        driver.close();
        driver.switchTo().window(winHandleBeforeSwitch);
        log.info("New message window check");
    }

    @Test
    void shouldOpenNewTab() throws InterruptedException {
        driver.get(path);
        WebElement newTabBtn = driver.findElement(By.cssSelector("#newBrowserTab"));
        newTabBtn.click();
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);
//todo mountain test how to pass driver?
        Thread.sleep(500);
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
        driver.navigate().to(path);
        log.info("New tab check");
    }
}
