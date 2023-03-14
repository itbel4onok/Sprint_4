package Scooter.MainPage;

import PageObject.MainPage.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainFunctionsTest {
    private final ChromeDriver driver = new ChromeDriver();
    private final MainPage objMainPage = new MainPage(driver);

    @Before
    public void beforeSuite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        objMainPage.waitForLoadPage();
    }

    @Test
    public void orderNotFoundTest() {
        objMainPage.openStatusOrderWindow();
        objMainPage.searchOrderByNumber("000");
        objMainPage.checkOrderNotFoundImgExist();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
