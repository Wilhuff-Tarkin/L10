package basic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DownloadTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(FormTest.class);
    private static final Path path = Paths.get(downloadFilepath);
    private static final String fileName = "test-file-to-download.xlsx";

    @BeforeAll
    static void additionalSetup() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", file.getAbsolutePath());
        log.info(">>>>>  Setting downloads location to " + file.getAbsolutePath());
        options.setExperimentalOption("prefs", prefs);
    }

    @Test
    public void shouldDownloadFile()  {
        if (!desiredLocationExists(path)) {
            createDesiredLocation();
        }
        log.info(">>>>>  Number of files in directory before download = " + checkNumberOfFilesInDir(downloadFilepath));
        driver.get("https://seleniumui.moderntester.pl/form.php");
        WebElement downloadBtn = driver.findElement(By.cssSelector(".col-sm-12 a"));
        downloadBtn.click();
        waitUntilDownloadsComplete(checkNumberOfFilesInDir(downloadFilepath));
        log.info(">>>>>  Download complete. Files in directory after download = " + checkNumberOfFilesInDir(downloadFilepath));
    }

    private int checkNumberOfFilesInDir(String downloadFilepath) {
        return Objects.requireNonNull(new File(downloadFilepath).list()).length;
    }

    private void createDesiredLocation() {
        File file = new File(String.valueOf(path));
        if (file.mkdir()) {
            log.info(">>>>>  Directory has been created successfully");
        } else {
            log.info(">>>>>  Directory cannot be created");
        }
    }

    private boolean desiredLocationExists(Path path) {
        return Files.exists(path);
    }

    private void waitUntilDownloadsComplete(int numberOfFilesBeforeDownload) {

        Path exactPath = Paths.get(String.valueOf(file));
        if (desiredLocationExists(exactPath)){
            log.info(">>>>>  File: " + fileName + " already exist in location.");
        }
        log.info(">>>>>  Waiting for new file in: " + path);
        FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(100));
        wait.until(x -> checkNumberOfFilesInDir(downloadFilepath)>numberOfFilesBeforeDownload);
    }
}
