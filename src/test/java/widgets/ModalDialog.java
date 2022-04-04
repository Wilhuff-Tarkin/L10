package widgets;

import basic.FormTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v95.network.model.TrustTokenOperationDone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;


public class ModalDialog extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(ModalDialog.class);
    private static final String path = "https://seleniumui.moderntester.pl/modal-dialog.php";

    @Test
    void shouldCreateNewUser() {

        driver.get(path);
        WebElement newUserBtn = driver.findElement(By.cssSelector("#create-user"));
        newUserBtn.click();

        WebElement name = driver.findElement(By.cssSelector("#name"));
        WebElement email = driver.findElement(By.cssSelector("#email"));
        WebElement password = driver.findElement(By.cssSelector("#password"));

        name.clear();
        name.sendKeys("Bob");
        email.clear();
        email.sendKeys("Bob@Bob.pl");
        password.clear();
        password.sendKeys("teasdast");

        driver.findElement(By.cssSelector("div .ui-dialog-buttonset button:first-child")).click();




    }

    @Test
    void newUserShouldBePresent() {
        //TODO




    }


}
