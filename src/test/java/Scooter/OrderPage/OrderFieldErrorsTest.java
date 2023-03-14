package Scooter.OrderPage;

import PageObject.MainPage.*;
import PageObject.OrderPage.*;
import constants.OrderFieldErrors;
import constants.OrderPageConst;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class OrderFieldErrorsTest {
    private ChromeDriver driver = new ChromeDriver();
    private final String orderField;

    public OrderFieldErrorsTest(String orderField) {
        this.orderField = orderField;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderField() {
        return new Object[][] {
                { OrderFieldErrors.ERROR_NAME_FIELD },
                { OrderFieldErrors.ERROR_SURNAME_FIELD },
                { OrderFieldErrors.ERROR_ADDRESS_FIELD },
                { OrderFieldErrors.ERROR_METRO_STATION_FIELD },
                { OrderFieldErrors.ERROR_PHONE_NUMBER },
        };
    }

    @Before
    public void beforeSuite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadPage();
        objMainPage.skipCookie();
        objMainPage.openOrderPage(OrderPageConst.ORDER_HEADER);
    }

    @Test
    public void checkErrorMessagesForOrderFields() {
        OrderPageInitiallyForm objOrderPage = new OrderPageInitiallyForm(driver);
        objOrderPage.waitForLoadOrderPage();
        objOrderPage.fillOrderFieldsWithInvalidValue(OrderFieldErrors.INVALID_VALUE);
        objOrderPage.verifyErrorMessage(orderField);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
