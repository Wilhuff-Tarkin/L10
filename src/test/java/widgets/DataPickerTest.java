package widgets;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;



public class DataPickerTest extends TestBase {

    private static final String path = "https://seleniumui.moderntester.pl/datepicker.php";
    private static final Logger log = LoggerFactory.getLogger(DataPickerTest.class);



    @Test
    void dataPickerTest() {


        driver.get(path);
        driver.findElement(By.cssSelector("#datepicker")).click();

//        1.Today
        LocalDate today = tellMeWhatDayItIs();
        String dayOfTheMonth = String.valueOf(today.getDayOfMonth());

        WebElement dateWidgetFrom = driver.findElement(By.cssSelector(".ui-datepicker"));
        List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
        clickOnGivenDay(columns,dayOfTheMonth);

        WebElement chosenDate = driver.findElement(By.cssSelector("#datepicker"));

        assertThat("Date incorrect", chosenDate.getAttribute("value"),(is(equalTo("04/01/2022"))));


//        driver.findElement(By.cssSelector("#datepicker")).clear();


//        2.1st day from next month
//        3.Last day from January in next year
//        4.Select same day again (same was selected in step 3)
//        5.Random day from previous month
//        6.Random date from last year



    }

    private void clickOnGivenDay(List<WebElement> elementList, String day) {

            elementList.stream()
                    .filter(element -> element.getText().equals(day))
//                    .filter(element -> element.findElement(By.cssSelector(".ui-state-default")))
                    .findFirst()
                    .ifPresent(WebElement::click);
    }

    void dataPickerClear(){
        driver.findElement(By.cssSelector("#datepicker")).clear();

    }

    private LocalDate tellMeWhatDayItIs (){


        LocalDate now = LocalDate.now();
        log.info("Today is " + now);
        return now;

    }

}
