package tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.*;
import pages.YouTubePage;



@DisplayName("Параметризованные wеб-тесты")
public class ParametrizedTest extends TestBase {
    YouTubePage youTubePage = new YouTubePage();

    @BeforeEach
    void setUp()
    {Configuration.baseUrl = "https://www.youtube.com";}

    @ValueSource(strings = {
            "Станислав Васенков",
            "Дмитрий Тучс",
            "QA"
    })
    @DisplayName("При поисковом запросе ответ содержит элементы")
    @ParameterizedTest(name = "WEB: для поискового запроса {0} отдается не пустой список карточек")
    @Tag("WEB")
    void nonEmptyCardListAfterSearch(String queryLength) {
        youTubePage
                .openMainPage()
                .inputSearchValue(queryLength)

                .checkSearchResultIsNotEmpty();
    }

    @CsvFileSource(resources = "/test_data/cardShouldExpectedText.csv", delimiter = '|')
    @DisplayName("При поисковом запросе заголовок видео содержит текст")
    @ParameterizedTest(name = "WEB: для поискового запроса {0} в списке есть карточка с названием {1}")
    @Tag("WEB")
    void cardShouldContainExpectedText(String queryLength, String expectedName) {
        youTubePage
                .openMainPage()
                .inputSearchValue(queryLength)

                .checkQueryNameInCard(expectedName);
    }

    @CsvSource(value = {
            "Cтанислав Васенков | QA GURU. Станислав Васенков. «Учимся быстро разрабатывать готовые проекты для тестовых заданий»",
            "Дмитрий Тучс | Дмитрий Тучс — JUnit 5 Parallel test execution. Теория и практика",
            "Infomaximum | Вебинар «Процессная аналитика — 2022: реальные бизнес-кейсы»"
    }, delimiter = '|')
    @DisplayName("При поисковом запросе заголовок видео содержит текст")
    @ParameterizedTest(name = "WEB: для поискового запроса {0} в списке есть карточка с названием {1}")
    @Tag("WEB")
    void cardShouldContainsExpectedText(String queryLength, String expectedName) {
        youTubePage
                .openMainPage()
                .inputSearchValue(queryLength)

                .checkQueryNameInCard(expectedName);
    }

    @Disabled("JIRA MT-355")
    @Test
    @DisplayName("Проверка отображения датапровайдерa @Disabled")
    void testWillBeSkipped() {
        youTubePage
                .openMainPage();

    }
}