package ru.praktikum_services.qa_scooter.objects;

import ru.praktikum_services.qa_scooter.constants.LinksURL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class YaPage {
    private WebDriver driver;

    // Кнопка Найти
    private By buttonSearch = By.xpath("//button[@type='submit']");

    public YaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadYaPage() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(buttonSearch).getText() != null
                && !driver.findElement(buttonSearch).getText().isEmpty()
        ));
    }

    public void checkYaLink(String actualURL){
        String message = "Ожидаемый URL: " + LinksURL.YA_LINKS;
        assertEquals(message, LinksURL.YA_LINKS, actualURL);
    }

    public void makeYaPageMain(String originalWindow)
    {
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
