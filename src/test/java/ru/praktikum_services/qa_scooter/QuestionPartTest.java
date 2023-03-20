package ru.praktikum_services.qa_scooter;

import ru.praktikum_services.qa_scooter.objects.main.MainPage;
import ru.praktikum_services.qa_scooter.objects.main.QuestionsPart;
import ru.praktikum_services.qa_scooter.constants.TextOfAnswers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class QuestionPartTest {
    private final ChromeDriver driver = new ChromeDriver();
    private final int idOfQuestion;
    private final String expectedAnswerTextForQuestion;

    public QuestionPartTest(int idOfQuestion, String expectedAnswerTextForQuestion) {
        this.idOfQuestion = idOfQuestion;
        this.expectedAnswerTextForQuestion = expectedAnswerTextForQuestion;
    }

    @Parameterized.Parameters(name = "Q&A секция, вопрос №{0}")
    public static Object[][] getQuestionData() {
        return new Object[][] {
                { 0, TextOfAnswers.QUESTION_ID0},
                { 1, TextOfAnswers.QUESTION_ID1},
                { 2, TextOfAnswers.QUESTION_ID2},
                { 3, TextOfAnswers.QUESTION_ID3},
                { 4, TextOfAnswers.QUESTION_ID4},
                { 5, TextOfAnswers.QUESTION_ID5},
                { 6, TextOfAnswers.QUESTION_ID6},
                { 7, TextOfAnswers.QUESTION_ID7},
        };
    }

    @Before
    public void beforeSuite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadPage();
    }

    @Test
    public void checkAnswersPart() {
        QuestionsPart objQuestionsPart = new QuestionsPart(driver);
        objQuestionsPart.scrollToQuestionById(idOfQuestion);
        objQuestionsPart.openAnswerTextForQuestionById(idOfQuestion);
        objQuestionsPart.checkAnswerTextForQuestionById(idOfQuestion, expectedAnswerTextForQuestion);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
