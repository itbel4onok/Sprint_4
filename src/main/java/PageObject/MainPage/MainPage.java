package PageObject.MainPage;

import constants.LinksURL;
import constants.MainPageConst;
import constants.OrderPageConst;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPage {
    private final WebDriver driver;

    // Заголовок страницы
    private final By pageTitle = By.className("Home_Header__iJKdX");

    //Кнопка Заказать в заголовке
    private final By orderHeaderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button");

    //Кнопка Заказать в середине страницы
    private final By orderMiddleButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");

    //Кнопка про Куки
    private final By buttonCookie  = By.className("App_CookieButton__3cvqF");

    //Лого Яндекс
    private final By logoYA  = By.xpath("//img[@alt='Yandex']");

    //Лого Самокат
    private final By logoScooter  = By.xpath("//img[@alt='Scooter']");

    //Кнопка Статус Заказа
    private final By buttonOrderStatus = By.className("Header_Link__1TAG7");

    //Поле для ввода Статуса Заказа
    private final By fieldOrderNumber = By.xpath("//input[contains(@class, 'Input_Input__1iN_Z')]");

    //Кнопка Go в поле поиска номера заказа
    private final By buttonGoSearch = By.xpath("//button[contains(@class, 'Header_Button__28dPO')]");

    //Картинка при отсутсвии заказа
    private final By imgOrderNotFound = By.xpath("//img[@alt = 'Not found']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadPage() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(pageTitle).getText() != null
                && !driver.findElement(pageTitle).getText().isEmpty()
        ));
    }

    public void openOrderPage(String OrderButton){
        if (OrderButton.equals(OrderPageConst.ORDER_HEADER)) {
            driver.findElement(orderHeaderButton).click();
        }
        else if (OrderButton.equals(OrderPageConst.ORDER_MIDDLE)) {
            WebElement element = driver.findElement(orderMiddleButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            element.click();
        }
    }

    public void skipCookie(){
        if (driver.findElements(buttonCookie).size() != 0) {
            driver.findElement(buttonCookie).click();
        }
    }

    public void openYaPageByLogo(){
        driver.findElement(logoYA).click();
    }

    public void openScooterPageByLogo(){
        driver.findElement(logoScooter).click();
    }

    public void checkScooterLink(String actualURL){
        String message = "Ожидаемый URL: " + LinksURL.SCOOTER_LINK;
        assertEquals(message, LinksURL.SCOOTER_LINK, actualURL);
    }

    public void openStatusOrderWindow(){
        driver.findElement(buttonOrderStatus).click();
        new WebDriverWait(driver, 3).until(driver -> (driver.findElement(buttonGoSearch).getText() != null
                && !driver.findElement(buttonGoSearch).getText().isEmpty()
        ));
    }

    public void searchOrderByNumber(String statusNumber){
        driver.findElement(fieldOrderNumber).sendKeys(statusNumber);
        driver.findElement(buttonGoSearch).click();
    }

    public void checkOrderNotFoundImgExist(){
        boolean isElementPresent = driver.findElements(imgOrderNotFound).size() != 0;
        String message = "Ожидается картинка с надписью: " + MainPageConst.ORDER_NOT_FOUND;
        assertTrue(message, isElementPresent);
    }
}


