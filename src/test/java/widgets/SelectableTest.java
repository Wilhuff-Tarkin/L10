package widgets;

import basic.FormTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.util.List;

public class SelectableTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(FormTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/selectmenu.php";


    @BeforeEach
    void additionalSetup() {
        driver.get(path);
    }


    @Test
    void selectRandomSpeed() {

        WebElement speedBtn = driver.findElement(By.cssSelector("#speed-button"));
        speedBtn.click();
        List<WebElement> speedOptions = driver.findElements(By.cssSelector("#speed-menu li"));
        getRandomElement(speedOptions).click();
        log.info("Select random check");
    }

    @Test
    void selectFileByTextOption() {
        WebElement fileBtn = driver.findElement(By.cssSelector("#files-button"));
        fileBtn.click();
        WebElement fileSelect = driver.findElement(By.cssSelector("#files"));
        Select selectFile = new Select(fileSelect);
        selectFile.selectByVisibleText("jQuery.js");
        log.info("Select by text check");
    }

    @Test
    void selectNumberByIx()  {
        WebElement numberBtn = driver.findElement(By.cssSelector("#number-button"));
        numberBtn.click();
        WebElement firstOption = driver.findElement(By.cssSelector("#number-menu .ui-menu-item:first-child"));
        firstOption.click();
        log.info("Select by index check");
    }

    @Test
    void selectRandomTitle() {
        WebElement salutationBtn = driver.findElement(By.cssSelector("#salutation-button"));
        salutationBtn.click();
        List<WebElement> salutations = driver.findElements(By.cssSelector("#salutation-menu li:not(:first-child)"));
        getRandomElement(salutations).click();
        log.info("Select random title check");
    }

}
