package widgets;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class DataPickerTest extends TestBase {

    private static final String path = "https://seleniumui.moderntester.pl/datepicker.php";
    private static final Logger log = LoggerFactory.getLogger(DataPickerTest.class);
    DateTimeFormatter usDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate today = tellMeWhatDayItIs();
    LocalDate firstDayFromNextMonth = LocalDate.of(2022, 5, 1);


    @Test
    void pickToday() {
        openDatePicker();
        String todayDay = String.valueOf(today.getDayOfMonth());
        clickOnGivenDay(getCalendarPage(), todayDay);
        assertThat("Date incorrect", getChosenDate(), (is(equalTo(today.format(usDateFormat)))));
        log.info("Pick todays date check");
    }

    @Test
    void pick1stDayFromNextMonth() {
        openDatePicker();
        WebElement nextBtn = driver.findElement(By.cssSelector("a[data-handler='next']"));
        nextBtn.click();
        clickOnGivenDay(getCalendarPage(), "1");
        assertThat("Date incorrect", getChosenDate(), (is(equalTo(firstDayFromNextMonth.format(usDateFormat)))));
        log.info("Pick 1st date from next month check");
    }

    @Test
    void pickLastDayFromJanuaryNextYear() {
        openDatePicker();
        jumpTo("January", 2022);
    }

    private void jumpTo(String month, int year) {

        WebElement yearOnCalender = driver.findElement(By.cssSelector(".ui-datepicker-year"));
        WebElement monthOnCalender = driver.findElement(By.cssSelector(".ui-datepicker-month"));
        int yearDisplayed = Integer.parseInt(yearOnCalender.getText());
        int monthDisplayed = Integer.parseInt(monthOnCalender.getText());

        while (yearDisplayed != year) {

            if (yearDisplayed > year) {

            } else {

            }

            yearDisplayed = Integer.parseInt(yearOnCalender.getText());
        }




    }

    @Test
    void pickSameDateAgain() {

    }

    @Test
    void pickRandomDayFromPreviousMonth() {

    }

    @Test
    void pickRandomDayFromPreviousYear() {

    }

    private void clickOnGivenDay(List<WebElement> elementList, String day) {
        elementList.stream()
                .filter(element -> element.getText().equals(day))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    void dataPickerClear() {
        driver.findElement(By.cssSelector("#datepicker")).clear();

    }

    private LocalDate tellMeWhatDayItIs() {


        LocalDate now = LocalDate.now();
        log.info("Today is " + now);
        return now;

    }

    private void openDatePicker() {
        driver.get(path);
        driver.findElement(By.cssSelector("#datepicker")).click();

    }

    private String getChosenDate() {
        WebElement chosenDate = driver.findElement(By.cssSelector("#datepicker"));
        return chosenDate.getAttribute("value");
    }

    private List<WebElement> getCalendarPage() {
        WebElement dateWidgetFrom = driver.findElement(By.cssSelector(".ui-datepicker"));
        List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));

        return columns;
    }

}
