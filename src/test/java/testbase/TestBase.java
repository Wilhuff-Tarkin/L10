package testbase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class TestBase {

    public static WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(TestBase.class);

    public static String downloadFilepath = "src/main/resources/downloads";
    public static File file = new File(downloadFilepath);
    public static ChromeOptions options = new ChromeOptions();


    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        log.info(">>>>>  Driver initiated successfully.");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", file.getAbsolutePath());
        log.info(">>>>>  Setting downloads location to " + file.getAbsolutePath());
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("Start-maximized");

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

    public static WebElement getRandomElement (List<WebElement> elements){
        return elements.get(new Random().nextInt(elements.size()));
    }


}
