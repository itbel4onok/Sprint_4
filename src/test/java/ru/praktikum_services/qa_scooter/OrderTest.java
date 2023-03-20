package ru.praktikum_services.qa_scooter;

import ru.praktikum_services.qa_scooter.constants.OrderPageConst;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum_services.qa_scooter.objects.main.MainPage;
import ru.praktikum_services.qa_scooter.objects.order.OrderPageInitiallyForm;
import ru.praktikum_services.qa_scooter.objects.order.OrderPageSecondForm;

@RunWith(Parameterized.class)
public class OrderTest {
    private ChromeDriver driver = new ChromeDriver();
    private final String orderElement;
    private final String stationName;
    private final String data;
    private final String period;
    private final String colour;


    public OrderTest(String orderElement, String stationName, String data, String period, String colour) {
        this.orderElement = orderElement;
        this.stationName = stationName;
        this.data = data;
        this.period = period;
        this.colour = colour;
    }

    @Parameterized.Parameters(name = "Заказ через кнопку: {0}")
    public static Object[][] getQuestionData() {
        return new Object[][] {
                { OrderPageConst.ORDER_HEADER, OrderPageConst.METRO_ZIL, "5",
                        OrderPageConst.SROK_2DAYS, OrderPageConst.COLOR_BLACK },
                { OrderPageConst.ORDER_MIDDLE, OrderPageConst.METRO_SOKOL, "28",
                        OrderPageConst.SROK_7DAYS, OrderPageConst.COLOR_GRAY },
        };
    }

    @Before
    public void beforeSuite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkOrderProcess() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadPage();
        objMainPage.skipCookie();

        objMainPage.openOrderPage(orderElement);
        OrderPageInitiallyForm objOrderPage = new OrderPageInitiallyForm(driver);
        objOrderPage.waitForLoadOrderPage();
        objOrderPage.fillInitiallyFormOfOrderData(stationName);

        OrderPageSecondForm objOrderSecondPage = new OrderPageSecondForm(driver);
        objOrderSecondPage.waitForLoadOrderPage();
        objOrderSecondPage.fillSecondFormOfOrderData(data, period, colour);
        objOrderSecondPage.finalOrderAgreement();

        objOrderSecondPage.checkSuccessfulOrderHeader();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
