package others;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class HighSiteTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(HighSiteTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/high-site.php";

    @Test
    public void shouldScrollTillButtonVisible() {
        driver.get(path);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        while (!isElementVisible("#scroll-button")) {
            jse.executeScript("window.scrollBy(0,50)");
        }
        log.info("Button visible!");
        try {
            takeScreenShot();
            log.info("Screenshot taken!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void takeScreenShot() throws IOException {
        Random random = new Random();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshotsFilepath + "\\" + "screenshot" + random.nextInt(666) + ".png"));
    }
}
