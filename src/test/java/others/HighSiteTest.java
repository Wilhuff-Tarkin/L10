package others;

import basic.WindowsTabsTest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;

public class HighSiteTest  extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(HighSiteTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/high-site.php";
//    private static final Path downloadPath = Paths.get(downloadFilepath);



    @Test
    public void shouldScrollTillButtonVisible() {


        WebElement submitButton = driver.findElement(By.cssSelector(".lead.high-site-paragraph.show-button"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",submitButton);



        log.info("Scrolled to button");

    }




}
