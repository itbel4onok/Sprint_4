package ru.praktikum_services.qa_scooter.objects.order;

import ru.praktikum_services.qa_scooter.constants.OrderFieldErrors;
import ru.praktikum_services.qa_scooter.constants.OrderPageConst;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class OrderPageInitiallyForm {
    private WebDriver driver;

    // Кнопка "Далее"
    private By buttonNext = By.xpath("//button[text() = 'Далее']");

    // Поле Имя
    private By fieldName = By.xpath("//input[contains(@placeholder, 'Имя')]");

    // Поле Фамилия
    private By fieldSurname = By.xpath("//input[contains(@placeholder, 'Фамилия')]");

    // Поле Адрес
    private By fieldAddress = By.xpath("//input[contains(@placeholder, 'Адрес')]");

    // Поле станция метро
    private By fieldMetro = By.xpath("//input[contains(@placeholder, 'Станция')]");

    // Поле телефон
    private By fieldPhoneNumber = By.xpath("//input[contains(@placeholder, 'Телефон')]");

    // Ошибка для поля Имя
    private By errorNameMessage = By.xpath("//input[contains(@placeholder, 'Имя')]/following::div[1]");

    // Ошибка для поля Фамилия
    private By errorSurnameMessage = By.xpath("//input[contains(@placeholder, 'Фамилия')]/following::div[1]");

    // Ошибка для поля Адрес
    private By errorAddressMessage = By.xpath( "//input[contains(@placeholder, 'Адрес')]/following::div[1]");

    // Ошибка для поля Станция метро
    private By errorMetroStatiobMessage = By.xpath("//input[contains(@placeholder, 'Станция')]/following::div[1]");

    // Ошибка для поля Телефон
    private By errorPhoneNumberMessage = By.xpath("//input[contains(@placeholder, 'Телефон')]/following::div[1]");

    public OrderPageInitiallyForm(WebDriver driver) {
        this.driver = driver;
    }
    public void waitForLoadOrderPage() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(buttonNext).getText() != null
                && !driver.findElement(buttonNext).getText().isEmpty()
        ));
    }

    public void fillInitiallyFormOfOrderData(String stationName){
        driver.findElement(fieldName).sendKeys(OrderPageConst.CORRECT_NAME_VALUE);
        driver.findElement(fieldSurname).sendKeys(OrderPageConst.CORRECT_SURNAME_VALUE);
        driver.findElement(fieldAddress).sendKeys(OrderPageConst.CORRECT_ADDRESS_VALUE);
        selectMetroStationByName(stationName);
        driver.findElement(fieldPhoneNumber).sendKeys(OrderPageConst.CORRECT_PHONE_NUMBER_VALUE);
        clickNextButton();
    }

    private void selectMetroStationByName(String stationName){
        driver.findElement(fieldMetro).click();
        By metroStationElement = By.xpath(format("//div[@class = 'Order_Text__2broi' and text()='%s']", stationName));
        WebElement element = driver.findElement(metroStationElement);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public void clickNextButton(){
        driver.findElement(buttonNext).click();
    }

    public void fillOrderFieldsWithInvalidValue(String text)
    {
        driver.findElement(fieldName).sendKeys(text);
        driver.findElement(fieldSurname).sendKeys(text);
        driver.findElement(fieldAddress).sendKeys(text);
        driver.findElement(fieldPhoneNumber).sendKeys(text);
        clickNextButton();
    }

    public void verifyErrorMessage(String errorMessage)
    {
        String actualErrorMessage = "Отсутсвует текст ошибки";
        switch (errorMessage) {
            case OrderFieldErrors.ERROR_NAME_FIELD:
                actualErrorMessage = driver.findElement(errorNameMessage).getText();
                break;
            case OrderFieldErrors.ERROR_SURNAME_FIELD:
                actualErrorMessage = driver.findElement(errorSurnameMessage).getText();
                break;
            case OrderFieldErrors.ERROR_ADDRESS_FIELD:
                actualErrorMessage = driver.findElement(errorAddressMessage).getText();
                break;
            case OrderFieldErrors.ERROR_METRO_STATION_FIELD:
                actualErrorMessage = driver.findElement(errorMetroStatiobMessage).getText();
                break;
            case OrderFieldErrors.ERROR_PHONE_NUMBER:
                actualErrorMessage = driver.findElement(errorPhoneNumberMessage).getText();
                break;
        }
        String message = "Ожидаемый текст ошибки: " + errorMessage;
        assertEquals(message, errorMessage, actualErrorMessage);
    }
}
