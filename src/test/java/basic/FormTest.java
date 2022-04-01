package basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testbase.TestBase;

import java.io.File;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FormTest extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(FormTest.class);

    @Test
    public void shouldFillFormWithSuccess(){
        driver.get("https://seleniumui.moderntester.pl/form.php");

        WebElement firstName = driver.findElement(By.cssSelector("#inputFirstName3"));
        firstName.sendKeys("Arnold");
        log.info(">>>>>  First name check");

        WebElement lastName = driver.findElement(By.cssSelector("#inputLastName3"));
        lastName.sendKeys("Schwarzenegger");
        log.info(">>>>>  Last name check");


        WebElement email = driver.findElement(By.cssSelector("#inputEmail3"));
        email.sendKeys("arnold@rulez.pl");
        log.info(">>>>>  Email check");


        List <WebElement> sexs = driver.findElements(By.cssSelector("input[name='gridRadiosSex']"));
        getRandomElement(sexs).click();
        log.info(">>>>>  Sex randomized check");


        WebElement age = driver.findElement(By.cssSelector("#inputAge3"));
        age.sendKeys("29");
        log.info(">>>>>  Age sent check");

        List <WebElement> yearsOfExperience = driver.findElements(By.cssSelector("input[name='gridRadiosExperience']"));
        getRandomElement(yearsOfExperience).click();
        log.info(">>>>>  Years of experience randomized check");

        WebElement profession = driver.findElement(By.cssSelector("#gridCheckAutomationTester"));
        profession.click();
        log.info(">>>>>  Profession check");


        List <WebElement> continentOptions = driver.findElements(By.cssSelector("#selectContinents option"));
        assertThat("First option changed", (continentOptions.get(0).getText()).equals("Choose..."));
        log.info(">>>>>  Removing placeholder option from continents: " + continentOptions.get(0).getText());
        continentOptions.remove(0);
        getRandomElement(continentOptions).click();
        log.info(">>>>>  Continent check");


        WebElement seleniumCommands1 = driver.findElement(By.cssSelector("option[value=switch-commands]"));
        WebElement seleniumCommands2 = driver.findElement(By.cssSelector("option[value=wait-commands]"));
        Actions actionProvider = new Actions(driver);
        Action selectWithCtrl = actionProvider.keyDown(Keys.CONTROL).click(seleniumCommands1).click(seleniumCommands2).build();
        selectWithCtrl.perform();
        log.info(">>>>>  Commands check");


        File file = new File("src/main/resources/file.txt");
        driver.findElement(By.id("chooseFile")).sendKeys(file.getAbsolutePath());
        log.info(">>>>>  File sent check");


        WebElement additionalInformation = driver.findElement(By.cssSelector("#additionalInformations"));
        additionalInformation.click();
        additionalInformation.sendKeys("some additional information");
        log.info(">>>>>  Additional info check");


        WebElement signInBtn = driver.findElement(By.cssSelector(".btn-primary"));
        signInBtn.click();

        String actualMessage = driver.findElement(By.id("validator-message")).getText();
        assertThat("Message not as expected", actualMessage, (is(equalTo("Form send with success"))));
        log.info(">>>>>  FORM SENT CORRECTLY <<<<<<<");
    }

















    }

