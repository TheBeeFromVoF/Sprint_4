package testPackege;
import Practicum.config.Env;
import Practicum.config.DriverFactory;
import Practicum.page.MainPage;
import Practicum.page.OrderPage;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver webDriver;
    private String buttonChoose;
    private String name;
    private String lastName;
    private String address;
    private String subwayStation;
    private String phoneNumber;
    private String deliveryDate;
    private String orderIsProcessed;

    public OrderTest(String buttonChoose, String name, String lastName, String address, String subwayStation, String phoneNumber, String deliveryDate) {
        this.buttonChoose = buttonChoose;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Up", "Имя", "фамилия", "адресс", "Фрунзенская", "89171234567", "01.06.2024"},
                {"Down", "НеИмя", "Нефамилия", "Бобрград", "Комсомольская", "89177654321", "05.07.2024"},
        };
    }

    @Before
    public void setup(){
        webDriver = DriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        webDriver.get(Env.BASE_URL);
    }
    @Test
    public void createOrder(){

        MainPage mainPage = new MainPage(webDriver);
        mainPage.closeCookiesWindow();
        mainPage.createOrder(buttonChoose);
        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.customerData(name, lastName, address, subwayStation, phoneNumber);
        orderPage.clickNextButton();
        orderPage.aboutRent (deliveryDate);
        boolean orderIsDisplayed = orderPage.orderProcessed(orderIsProcessed);
        assertTrue(orderIsDisplayed);

    }

    @After
    public void closeBrowser(){
        webDriver.quit();
    }

}
