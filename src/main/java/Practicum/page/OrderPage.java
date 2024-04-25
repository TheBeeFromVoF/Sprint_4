package Practicum.page;
import Practicum.config.Env;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    //Локатор имени для заказа
    private By inputNameLocator = By.xpath("//input[@placeholder='* Имя']");
    //Локатор фамилии для заказа
    private By inputLastNameLocator = By.xpath("//input[@placeholder='* Фамилия']");
    //Локатор адреса заказа
    private By inputAddressLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Локатор выпадающего списка станций
    private By subwayStationLocator = By.xpath("//input[@placeholder='* Станция метро']");
    //Локатор выбираемой станции
    private final String inputStationLocator = "//div[text()='%s']";
    //Локатор поля телефона
    private By phoneLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор кнопки далее при оформлении заказа
    private By nextButtonLocator = By.xpath(".//button[text()='Далее']");
    //Локатор  поля даты доставки самоката
    private By deliveryDateLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Локатор поля с выпадающим списком срока аренды
    private By rentalPeriodLocator = By.xpath("//div[text()='* Срок аренды']");
    //Локатор выбираемого элемента из выпадающего списка со сроком аренды
    private By rentalPeriodInputLocator = By.xpath("//div[text() = 'двое суток'] ");
    //Локатор кнопки заказать
    private By orderButtonLocator = By.xpath("//button[2][text()='Заказать']");
    //Локатор кнопки да при подтверждении заказа
    private By yesButtonLocator = By.xpath("//button[text()='Да']");





    private final WebDriver webDriver;
    public OrderPage (WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void customerData(String firstName, String lastName, String address, String subwayStation, String phoneNumber) {
        WebElement nameInput = webDriver
                .findElement(inputNameLocator);
        nameInput.sendKeys(firstName);

        WebElement lastNameInput = webDriver
                .findElement(inputLastNameLocator);
        lastNameInput.sendKeys(lastName);

        WebElement addressInput = webDriver
                .findElement(inputAddressLocator);
        addressInput.sendKeys(address);

        WebElement subwayStationInput = webDriver
                .findElement(subwayStationLocator);
        subwayStationInput.click();

        WebElement nameStation = webDriver
                .findElement(By.xpath(String.format(inputStationLocator, subwayStation)));
        nameStation.click();

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", subwayStationInput);
        subwayStationInput.click();

        WebElement numberOfPhoneInput = webDriver
                .findElement(phoneLocator);
        numberOfPhoneInput.sendKeys(phoneNumber);
    }


    public void clickNextButton(){
        WebElement nextButton = webDriver
                .findElement(nextButtonLocator);
        nextButton.click();
    }



    public void aboutRent(String deliveryDate){

        WebElement dateOfDelivery = webDriver
                .findElement(deliveryDateLocator);
        dateOfDelivery.sendKeys(deliveryDate, Keys.ENTER);

        WebElement rentPeriod = webDriver
                .findElement(rentalPeriodLocator);
        rentPeriod.click();

        WebElement rentPeriodInput = webDriver
                .findElement(rentalPeriodInputLocator);
        rentPeriodInput.click();

        WebElement orderButton = webDriver
                .findElement(orderButtonLocator);
        orderButton.click();

        new WebDriverWait(webDriver, Duration.ofSeconds(Env.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(yesButtonLocator));

        WebElement yesButton = webDriver
                .findElement(yesButtonLocator);
        yesButton.click();


    }


}
