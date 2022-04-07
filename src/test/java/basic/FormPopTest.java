package basic;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.FormPage;
import testbase.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FormPopTest extends TestBase {

    private static final String url = "https://seleniumui.moderntester.pl/form.php";
    private static final Logger log = LoggerFactory.getLogger(FormPopTest.class);

    @ParameterizedTest
    @MethodSource("newUsersSet2")
    public void shouldFillFormWithSuccess(String firstName, String lastName, String email, int age) {
        driver.get(url);
        FormPage formPage = new FormPage(driver);
        formPage.setFirstName(firstName);
        formPage.setLastName(lastName);
        formPage.setEmail(email);
        formPage.selectRandomSex();
        formPage.setAge(age);
        formPage.selectRandomExperience();
        formPage.setProfession();
        formPage.setRandomContinent();
        formPage.setCommands(driver);
        formPage.sendFile();
        formPage.fillAdditionalInfo();
        formPage.signIn();
        assertThat("Message not as expected", formPage.getActualMessage(), (is(equalTo("Form send with success"))));
        log.info(">>>>>  FORM SENT CORRECTLY <<<<<<<");
    }
}
