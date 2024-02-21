package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.*;

public class YouTubePage {
    private final SelenideElement inputSearch = $("#search-form #search");
    private final SelenideElement divWithSearchResult = $("#container #primary");
    private final ElementsCollection cardsWithVideos = $$("ytd-video-renderer .ytd-video-renderer");



    public YouTubePage openMainPage() {
        open("https://www.youtube.com/");
        return this;
    }

    public YouTubePage inputSearchValue(String text) {
        inputSearch.setValue(text).pressEnter();
        return this;
    }

    public void checkSearchResultIsNotEmpty() {
        divWithSearchResult.shouldNotBe(empty);
    }

    public void checkQueryNameInCard(String name) {
        cardsWithVideos.shouldHave(itemWithText(name));
    }
}


