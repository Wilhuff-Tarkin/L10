package basic;

import org.junit.jupiter.api.Test;
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

    @Test
    public void shouldFillFormWithSuccess() {
        driver.get(url);
        FormPage formPage = new FormPage(driver);
        formPage.setFirstName("Arnold");
        formPage.setLastName("Schwarzenegger");
        formPage.setEmail("arnold@rulez.pl");
        formPage.selectRandomSex();
        formPage.selectRandomExperience();

        assertThat("Message not as expected", formPage.getActualMessage(), (is(equalTo("Form send with success"))));
        log.info(">>>>>  FORM SENT CORRECTLY <<<<<<<");

    }


}
