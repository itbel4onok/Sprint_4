package ru.praktikum_services.qa_scooter.objects.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

// Объекты и методы для раздела "Вопросы о важном"
public class QuestionsPart {
    private final WebDriver driver;
    public QuestionsPart(WebDriver driver) {
        this.driver = driver;
    }

    // Поиск объекта вопроса (по id)
    private By findQuestionById(int questionId) {
        return By.id("accordion__heading-" + questionId);
    }

    // Поиск объекта поля ответа (по id вопроса)
    private By findAnswerById(int questionId) {
        return By.id("accordion__panel-" + questionId);
    }

    // Получение текста ответа
    private String getTextOfAnswerForQuestionById(int questionId) {
        return driver.findElement(findAnswerById(questionId)).getText();
    }

    // Нажатие на определённый вопрос
    public void openAnswerTextForQuestionById(int questionId) {
        By question = findQuestionById(questionId);
        driver.findElement(question).click();
    }

    public void checkAnswerTextForQuestionById(int questionId, String expectedAnswerTextForQuestion) {
        waitForLoadAnswerData(questionId);
        String message = "Для вопроса с id - "+ questionId + " должен быть следующий текст: " +
                expectedAnswerTextForQuestion;
        assertEquals(message, expectedAnswerTextForQuestion, getTextOfAnswerForQuestionById(questionId));
    }

    // Скролл до нужного вопроса
    public void scrollToQuestionById(int questionId) {
        WebElement element = driver.findElement(findQuestionById(questionId));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Метод ожидания прогрузки ответа
    public void waitForLoadAnswerData(int questionId) {
        WebElement element = driver.findElement(findAnswerById(questionId));
        new WebDriverWait(driver, 10).until(driver -> element.getText() != null
                && !element.getText().isEmpty());
    }
}


