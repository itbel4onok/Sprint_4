package PageObject.OrderPage;

import constants.OrderPageConst;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class OrderPageSecondForm {
    private WebDriver driver;

    // Поле когда привезти
    private By fieldWhen = By.xpath("//input[contains(@placeholder, 'Когда')]");

    // Поле срок аренды
    private By fieldPeriod = By.className("Dropdown-root");

    // Чек бокс - чёрный цвет
    private By checkBoxBlackColor = By.id("black");

    // Чек бокс - серый цвет
    private By checkBoxGreyColor = By.id("grey");

    // Поле комментарий
    private By fieldComment = By.xpath("//input[contains(@placeholder, 'Комментарий')]");

    // Кнопка Заказать
    private By buttonOrder = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text() = 'Заказать']");

    // Финальное подтверждение - Кнопка "Да"
    private By buttonFinalYes = By.xpath("//button[text() = 'Да']");

    //Заголовок в модальном окне (финальное подтверждение)
    private By headerFinalAgreement = By.xpath("//div[@class = 'Order_ModalHeader__3FDaJ']");

    public OrderPageSecondForm(WebDriver driver) {
        this.driver = driver;
    }

    public void fillSecondFormOfOrderData(String data, String period, String colour) {
        fillData(data);
        fillPeriod(period);
        if(colour.equals(OrderPageConst.COLOR_BLACK)) {
            driver.findElement(checkBoxBlackColor).click();}
        else if(colour.equals(OrderPageConst.COLOR_GRAY)) {
            driver.findElement(checkBoxGreyColor).click();
        }
        driver.findElement(fieldComment).sendKeys("Очень жду заказ");
        driver.findElement(buttonOrder).click();
    }

    private void fillPeriod(String period) {
        driver.findElement(fieldPeriod).click();
        By dataElementBy = By.xpath(format("//div[@class='Dropdown-option' and text () = '%s']", period));
        WebElement dataElementWeb = driver.findElement(dataElementBy);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", dataElementWeb);
        dataElementWeb.click();
    }

    private void fillData(String data) {
        driver.findElement(fieldWhen).click();
        By element = By.xpath(format("//div[contains(@class, 'react-datepicker') and text() = '%s']", data));
        driver.findElement(element).click();
    }

    public void waitForLoadOrderPage() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(buttonOrder).getText() != null
                && !driver.findElement(buttonOrder).getText().isEmpty()
        ));
    }

    public void finalOrderAgreement() {
        WebElement buttonYes = driver.findElement(buttonFinalYes);
        new WebDriverWait(driver, 10).until(driver -> (buttonYes.getText() != null
                && !buttonYes.getText().isEmpty()
        ));
        buttonYes.click();
    }

    public void checkSuccessfulOrderHeader() {
        String actualHeader = driver.findElement(headerFinalAgreement).getText().split("\n")[0];
        String message = "Ожидаемый заголовок: '" + OrderPageConst.SUCCESSFUL_ORDER + "'";
        assertEquals(message, OrderPageConst.SUCCESSFUL_ORDER, actualHeader);
    }
}
