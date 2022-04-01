package basic;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class IframesTest extends TestBase {

//    Test steps:
//            1.Switch to the first iframe and fill form
//2.Switch to the second iframe and fill form
//3.Switch to the main frame and click on 'Basic' button in the main menu

    private static final Logger log = LoggerFactory.getLogger(IframesTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/iframes.php";

    @Test
    public void iFrameTest (){
        driver.get(path);
        driver.switchTo().frame("iframe1");
        driver.findElement(By.cssSelector("#inputFirstName3")).sendKeys("Steven");
        driver.findElement(By.cssSelector("#inputSurname3")).sendKeys("Seagal");
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.switchTo().parentFrame();

        driver.switchTo().frame("iframe2");
        driver.findElement(By.cssSelector("#inputLogin")).sendKeys("Steven");
        driver.findElement(By.cssSelector("#inputPassword")).sendKeys("topsecret");
        List<WebElement> continentOptions = driver.findElements(By.cssSelector("#inlineFormCustomSelectPref"));
        getRandomElement(continentOptions).click();
        List <WebElement> yearsOfExperience = driver.findElements(By.cssSelector("input[name='gridRadios']"));
        getRandomElement(yearsOfExperience).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();

        driver.switchTo().parentFrame();
        log.info(">>>>>  Back to: " + driver.switchTo().parentFrame().getTitle());
        driver.findElement(By.cssSelector(".navbar li:first-child")).click();
        log.info(">>>>>  iFrames test check");
    }


}



