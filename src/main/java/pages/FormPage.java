package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;
import java.util.Random;

public class FormPage {

    @FindBy(id = "inputFirstName3")
    private WebElement firstName;

    @FindBy(id = "inputLastName3")
    private WebElement lastName;

    @FindBy(id = "inputEmail3")
    private WebElement email;

    @FindBy (name = "gridRadiosSex")
    private List <WebElement> sex;

    @FindBy (name = "gridRadiosExperience")
    private List <WebElement> yearsOfExperience;



    @FindBy (id = "validator-message")
    private WebElement actualMessage;

    public FormPage (WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void setFirstName (String name){
        this.firstName.sendKeys(name);
    }

    public void setLastName (String lastName){
        this.lastName.sendKeys(lastName);
    }
    public void setEmail (String email){
        this.email.sendKeys(email);
    }

    public void selectRandomSex () {
        getRandomElement(sex).click();
    }

    public void selectRandomExperience () {
        getRandomElement(yearsOfExperience).click();
    }

    public WebElement getActualMessage() {
        return actualMessage;
    }

//
//    List<WebElement> sexs = driver.findElements(By.cssSelector("input[name='gridRadiosSex']"));
//
//        log.info(">>>>>  Sex randomized check");
//
//
//    WebElement age = driver.findElement(By.cssSelector("#inputAge3"));
//        age.sendKeys("29");
//        log.info(">>>>>  Age sent check");
//
//    List <WebElement> yearsOfExperience = driver.findElements(By.cssSelector("input[name='gridRadiosExperience']"));
//    getRandomElement(yearsOfExperience).click();
//        log.info(">>>>>  Years of experience randomized check");
//
//    WebElement profession = driver.findElement(By.cssSelector("#gridCheckAutomationTester"));
//        profession.click();
//        log.info(">>>>>  Profession check");
//
//
//    List <WebElement> continentOptions = driver.findElements(By.cssSelector("#selectContinents option"));
//    assertThat("First option changed", (continentOptions.get(0).getText()).equals("Choose..."));
//        log.info(">>>>>  Removing placeholder option from continents: " + continentOptions.get(0).getText());
//        continentOptions.remove(0);
//    getRandomElement(continentOptions).click();
//        log.info(">>>>>  Continent check");
//
//
//    WebElement seleniumCommands1 = driver.findElement(By.cssSelector("option[value=switch-commands]"));
//    WebElement seleniumCommands2 = driver.findElement(By.cssSelector("option[value=wait-commands]"));
//    Actions actionProvider = new Actions(driver);
//    Action selectWithCtrl = actionProvider.keyDown(Keys.CONTROL).click(seleniumCommands1).click(seleniumCommands2).build();
//        selectWithCtrl.perform();
//        log.info(">>>>>  Commands check");
//
//
//    File file = new File("src/main/resources/file.txt");
//        driver.findElement(By.id("chooseFile")).sendKeys(file.getAbsolutePath());
//        log.info(">>>>>  File sent check");
//
//
//    WebElement additionalInformation = driver.findElement(By.cssSelector("#additionalInformations"));
//        additionalInformation.click();
//        additionalInformation.sendKeys("some additional information");
//        log.info(">>>>>  Additional info check");
//
//
//    WebElement signInBtn = driver.findElement(By.cssSelector(".btn-primary"));
//        signInBtn.click();
//
//    String actualMessage = driver.findElement(By.id("validator-message")).getText();
//




    public static WebElement getRandomElement(List<WebElement> elements) {
        return elements.get(new Random().nextInt(elements.size()));
    }


}
