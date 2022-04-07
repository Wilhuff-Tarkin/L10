package basic;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.util.List;

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
        showSpecificMountains("Switzerland", 4000);
        driver.close();
        driver.switchTo().window(winHandleBeforeSwitch);
        log.info(">>>>>  New window check");
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
        log.info(">>>>>  New message window check");
    }

    @Test
    void shouldOpenNewTab() {
        driver.get(path);
        WebElement newTabBtn = driver.findElement(By.cssSelector("#newBrowserTab"));
        String tabHandleBeforeSwitch = driver.getWindowHandle();
        newTabBtn.click();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        showSpecificMountains("France", 3500);
        driver.close();
        driver.switchTo().window(tabHandleBeforeSwitch);
        log.info(">>>>>  New tab check");
    }

    private void showSpecificMountains(String country, int height) {
        List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody tr"));
        log.info("Mountains that are in " + country + " and are higher than " + height + "m:");
        for (WebElement row : tableRows) {
            String rank = row.findElements(By.cssSelector("th")).get(0).getText();
            String peakName = row.findElements(By.cssSelector("td")).get(0).getText();
            String range = row.findElements(By.cssSelector("td")).get(1).getText();
            String state = row.findElements(By.cssSelector("td")).get(2).getText();
            String heightOfPeak = row.findElements(By.cssSelector("td")).get(3).getText();

            if (state.contains(country) && Integer.parseInt(heightOfPeak) > 4000) {
                log.info(rank + ". " + peakName + " " + "(" + range + ")");
            }
        }
    }
}
