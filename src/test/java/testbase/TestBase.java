package testbase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;


public class TestBase {

    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    public static String downloadFilepath = "src/main/resources/downloads";
    public static String screenshotsFilepath = "src/main/resources/screenshots";

    public static File file = new File(downloadFilepath);
    public static ChromeOptions options = new ChromeOptions();
    public WebDriver driver;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        log.info(">>>>>  Driver initiated successfully.");
        options.addArguments("Start-maximized");
    }

    public static WebElement getRandomElement(List<WebElement> elements) {
        return elements.get(new Random().nextInt(elements.size()));
    }

    private static Stream<Arguments> newUsersSet() {
        return Stream.of(
                Arguments.of("Ace Ventura", "pet@detective.com", "123456789"),
                Arguments.of("Bob Ross", "fun@painting.com", "abcdefg"),
                Arguments.of("Gary Moore", "sustain@infinity.com", "123123123")
        );
    }

    private static Stream<Arguments> newUsersSet2() {
        return Stream.of(
                Arguments.of("Ace", "Ventura", "pet@detective.com", 39),
                Arguments.of("Bob", "Ross", "fun@painting.com", 66),
                Arguments.of("Gary", "Moore", "sustain@infinity.com", 29)
        );
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        log.info(">>>>>  Driver closed successfully.");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean isElementVisible(String element) {
        try {
            return driver.findElement(By.cssSelector(element)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
