package Practicum.page;

import Practicum.config.Env;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver webDriver;
    //Верхняя кнопка заказа самоката
    private By createUpOrderLocator = By.
            xpath(".//div[contains(@class, 'Header')]/button[text()='Заказать']");
    //Ниэняя кнопка заказа самоката
    private By createDownOrderLocator = By.
            xpath(".//div[contains(@class, 'Home')]/button[text()='Заказать']");
    //Кнопка закрытия принятия куков
    private By cookiesButtonLocator = By.
            id("rcc-confirm-button");
    //Локатор выпадающего списка в аккордионе
    private final String questionLocator = "accordion__heading-%s";
    //Локатор выбора в аккордионе
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";

    public MainPage (WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void createUpOrder(){
        WebElement orderButtonUp = webDriver
                .findElement( createUpOrderLocator);
        orderButtonUp.click();
    }

    public void createDownOrder(){
        WebElement orderButtonDown = webDriver
                .findElement( createDownOrderLocator);
        new WebDriverWait(webDriver, Duration.ofSeconds(Env.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(createDownOrderLocator));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", orderButtonDown);
        orderButtonDown.click();
    }

    public void createOrder(String button){
        if (button.equals("Up") ) {
            createUpOrder();
        } else createDownOrder();
    }

    public void closeCookiesWindow(){
        webDriver.findElement(cookiesButtonLocator).click();
    }

    public void expandQuestion(int index) {
        WebElement element= webDriver.findElement(By.id(String.format(questionLocator, index)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(webDriver, Duration.ofSeconds(Env.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();

    }

    public boolean answerIsDisplayed(String expectedAnswer) {
        WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return element.isDisplayed();
    }
}