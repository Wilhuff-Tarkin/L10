package basic;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;
import static org.hamcrest.MatcherAssert.assertThat;

public class AlertsTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(AlertsTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/alerts.php";

    @Test
    void SimpleAlertPopUpTest() {
        driver.get(path);
        WebElement simpleAlertBtn = driver.findElement(By.cssSelector("#simple-alert"));
        simpleAlertBtn.click();
        assertThat("Text not visible or incorrect", driver.switchTo().alert().getText().equals("OK button pressed"));
        log.info(">>>>>  Simple Alert Pop up check");
    }

    @Test
    void PromptAlertBoxTest() {
        driver.get(path);
        WebElement promptAlertBtn = driver.findElement(By.cssSelector("#prompt-alert"));
        promptAlertBtn.click();
        driver.switchTo().alert().sendKeys("Lord Vader");
        driver.switchTo().alert().accept();
        WebElement promptMsg = driver.findElement(By.cssSelector("#prompt-label"));
        assertThat("Text not visible or incorrect", promptMsg.getText().equals("Hello Lord Vader! How are you today?"));
        log.info(">>>>>  Prompt Alert box check");
    }

    @Test
    void ConfirmAlertBox() {
        driver.get(path);
        WebElement confirmPopUpBtn = driver.findElement(By.cssSelector("#confirm-alert"));
        confirmPopUpBtn.click();
        driver.switchTo().alert().accept();
        WebElement confirmMsg = driver.findElement(By.cssSelector("#confirm-label"));
        assertThat("Text not visible or incorrect", confirmMsg.getText().equals("You pressed OK!"));
        confirmPopUpBtn.click();
        driver.switchTo().alert().dismiss();
        assertThat("Text not visible or incorrect", confirmMsg.getText().equals("You pressed Cancel!"));
        log.info(">>>>>  Confirm Alert box check");
    }

    @Test
    void DelayedAlertBox() {
        driver.get(path);
        WebElement delayedAlertBtn = driver.findElement(By.cssSelector("#delayed-alert"));
        delayedAlertBtn.click();
        new WebDriverWait(driver, 15)
                .ignoring(NoAlertPresentException.class)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        WebElement delayedAlertLabel = driver.findElement(By.cssSelector("#delayed-alert-label"));
        assertThat("Text not visible or incorrect", delayedAlertLabel.getText().equals("OK button pressed"));
        log.info(">>>>>  Delayed alert check");
    }
}
