package widgets;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class DataPickerTest extends TestBase {

    private static final String path = "https://seleniumui.moderntester.pl/datepicker.php";
    private static final Logger log = LoggerFactory.getLogger(DataPickerTest.class);
    DateTimeFormatter usDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate today = tellMeWhatDayItIs();
    LocalDate firstDayFromNextMonth = LocalDate.of(2022, 5, 1);
    LocalDate lastDayOfJanuaryNextYear = LocalDate.of(2023, 1, 31);

    public enum Direction {
        FORWARD, BACKWARD;
    }

    @Test
    void pickToday() {
        openDatePicker();
        String todayDay = String.valueOf(today.getDayOfMonth());
        List <WebElement> calendarPage = getCalendarPage();
        clickOnGivenDay(calendarPage, todayDay);
        assertThat("Date incorrect", getChosenDate(), (is(equalTo(today.format(usDateFormat)))));
        log.info("Pick todays date check");
    }

    @Test
    void pick1stDayFromNextMonth() {
        openDatePicker();
        clickOnNextBtn();
        clickOnGivenDay(getCalendarPage(), "1");
        assertThat("Date incorrect", getChosenDate(), (is(equalTo(firstDayFromNextMonth.format(usDateFormat)))));
        log.info("Pick 1st date from next month check");
    }

    @Test
    void pickLastDayFromJanuaryNextYear() {
        openDatePicker();
        jumpTo("January", 2023, Direction.FORWARD);
        clickOnGivenDay(getCalendarPage(), "31");
        assertThat("Date incorrect", getChosenDate(), (is(equalTo(lastDayOfJanuaryNextYear.format(usDateFormat)))));
        log.info("Pick last day from January next year check");
    }

    @Test
    void pickSameDateAgain() {
        openDatePicker();
        jumpTo("January", 2023, Direction.FORWARD);
        clickOnGivenDay(getCalendarPage(), "31");
        driver.findElement(By.cssSelector("#datepicker")).click();
        clickOnGivenDay(getCalendarPage(), "31");
        assertThat("Date incorrect", getChosenDate(), (is(equalTo(lastDayOfJanuaryNextYear.format(usDateFormat)))));
        log.info("Pick day again check");
    }

    @Test
    void pickRandomDayFromPreviousMonth() throws ParseException {
        openDatePicker();
        clickOnPrevBtn();
        List<WebElement> calendarPage = getCalendarPage();
        String day = getRandomDay(calendarPage);
        int parsedMonth = parseMonth(getMonthFromPage().getText());
        LocalDate randomDateFromPreviousMonth = LocalDate.of(Integer.parseInt(getYearFromPage().getText()), parsedMonth, Integer.parseInt(day));
        clickOnGivenDay(calendarPage, day);
        assertThat("Date incorrect", getChosenDate(), (is(equalTo(randomDateFromPreviousMonth.format(usDateFormat)))));
        log.info("Pick random day from previous month check");
    }

    @Test
    void pickRandomDayFromPreviousYear() {
        int previousYear = tellMeWhatDayItIs().getYear() - 1;
        int randomMonth = getRandomMonth() + 1;
        log.info("Random month is " + (randomMonth));
        LocalDate tempDate = LocalDate.of(previousYear, randomMonth, 1);
        openDatePicker();
        jumpTo(tempDate.getMonth().toString(), previousYear, Direction.BACKWARD);
        List<WebElement> calendarPage = getCalendarPage();
        String day = getRandomDay(calendarPage);
        clickOnGivenDay(calendarPage, day);
        LocalDate expectedDate = LocalDate.of(previousYear, randomMonth, Integer.parseInt(day));
        log.info("Random date from prev year is " + expectedDate.format(usDateFormat));
        log.info("Chosen date is " + getChosenDate());
        assertThat("Date incorrect", getChosenDate(), (is(equalTo(expectedDate.format(usDateFormat)))));
        log.info("Pick random day from previous year check");
    }

    private int getRandomMonth() {
        Random random = new Random();
        return random.nextInt(12);
    }

    private void jumpTo(String month, int year, Direction direction) {
        WebElement yearDisplayedOnCal = getYearFromPage();
        WebElement monthDisplayedOnCal = getMonthFromPage();
        int yearDisplayed = Integer.parseInt(yearDisplayedOnCal.getText());
        String monthDisplayed = monthDisplayedOnCal.getText();

        while (yearDisplayed != year || !monthDisplayed.equalsIgnoreCase(month)) {

            if (direction.equals(Direction.FORWARD)) {
                clickOnNextBtn();
            } else {
                clickOnPrevBtn();
            }
            WebElement changedYearDisplayedOnCal = getYearFromPage();
            WebElement changedMonthDisplayedOnCal = getMonthFromPage();
            yearDisplayed = Integer.parseInt(changedYearDisplayedOnCal.getText());
            monthDisplayed = changedMonthDisplayedOnCal.getText();
        }
    }

    private int parseMonth(String text) throws ParseException {
        Date date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(text);
        return date.getMonth() + 1;
    }

    private WebElement getYearFromPage() {
        return driver.findElement(By.cssSelector(".ui-datepicker-year"));
    }

    private WebElement getMonthFromPage() {
        return driver.findElement(By.cssSelector(".ui-datepicker-month"));
    }

    private String getRandomDay(List<WebElement> calendarPage) {
        Random random = new Random();
        int bound = calendarPage.size();
        return calendarPage.get(random.nextInt(bound)).getText();
    }

    private void clickOnGivenDay(List<WebElement> elementList, String day) {
        elementList.stream()
                .filter(element -> element.getText().equals(day))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    private LocalDate tellMeWhatDayItIs() {
        LocalDate now = LocalDate.now();
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
        List<WebElement> elements = dateWidgetFrom.findElements(By.cssSelector("td:not(.ui-datepicker-other-month)"));
        return elements;
    }

    private void clickOnNextBtn() {
        WebElement nextBtn = driver.findElement(By.cssSelector("a[data-handler='next']"));
        nextBtn.click();
    }

    private void clickOnPrevBtn() {
        WebElement prevBtn = driver.findElement(By.cssSelector("a[data-handler='prev']"));
        prevBtn.click();
    }
}
