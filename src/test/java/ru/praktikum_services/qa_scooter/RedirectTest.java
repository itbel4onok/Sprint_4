package ru.praktikum_services.qa_scooter;

import ru.praktikum_services.qa_scooter.constants.MainPageConst;
import ru.praktikum_services.qa_scooter.constants.OrderPageConst;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum_services.qa_scooter.objects.main.MainPage;
import ru.praktikum_services.qa_scooter.objects.YaPage;


@RunWith(Parameterized.class)
public class RedirectTest {
    private ChromeDriver driver = new ChromeDriver();
    private MainPage objMainPage = new MainPage(driver);
    private String originalWindow;
    private String orderElement;

    public RedirectTest(String orderElement) {
        this.orderElement = orderElement;
    }

    @Parameterized.Parameters(name = "Редирект со страницы: {0}")
    public static Object[][] getRedirectData() {
        return new Object[][] {
                { MainPageConst.MAIN_PAGE },
                { OrderPageConst.ORDER_HEADER },
                { OrderPageConst.ORDER_MIDDLE },
        };
    }

    @Before
    public void beforeSuite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        objMainPage.waitForLoadPage();
        originalWindow = driver.getWindowHandle();
        if (!orderElement.equals(MainPageConst.MAIN_PAGE )){
            objMainPage.openOrderPage(orderElement);
        }
    }

    @Test
    public void checkRedirectByYaLogo() {
        objMainPage.openYaPageByLogo();

        YaPage objYaPage = new YaPage(driver);
        objYaPage.makeYaPageMain(originalWindow);
        objYaPage.waitForLoadYaPage();
        objYaPage.checkYaLink(driver.getCurrentUrl());
    }

    @Test
    public void checkRedirectByScooterLogo() {
        objMainPage.openScooterPageByLogo();
        objMainPage.waitForLoadPage();
        objMainPage.checkScooterLink(driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
