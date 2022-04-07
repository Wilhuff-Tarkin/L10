package widgets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModalDialogTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(ModalDialogTest.class);
    private static final String path = "https://seleniumui.moderntester.pl/modal-dialog.php";



    @ParameterizedTest
    @MethodSource("newUsersSet")
    void shouldCreateNewUser(String newName, String newEmail, String newPassword) {

        driver.get(path);
        WebElement newUserBtn = driver.findElement(By.cssSelector("#create-user"));
        newUserBtn.click();

        WebElement name = driver.findElement(By.cssSelector("#name"));
        WebElement email = driver.findElement(By.cssSelector("#email"));
        WebElement password = driver.findElement(By.cssSelector("#password"));

        name.clear();
        name.sendKeys(newName);
        email.clear();
        email.sendKeys(newEmail);
        password.clear();
        password.sendKeys(newPassword);

        driver.findElement(By.cssSelector("div .ui-dialog-buttonset button:first-child")).click();
        log.info("New user created");

        WebElement lastTableRow = driver.findElement(By.cssSelector("tbody tr:last-child"));
        assertThat("Name incorrect", lastTableRow.findElement(By.cssSelector("td:nth-child(1)")).getText().equals(newName));
        assertThat("Email incorrect", lastTableRow.findElement(By.cssSelector("td:nth-child(2)")).getText().equals(newEmail));
        assertThat("Password incorrect", lastTableRow.findElement(By.cssSelector("td:nth-child(3)")).getText().equals(newPassword));
    }
}
