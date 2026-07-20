package otc_ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {

    private final SelenideElement searchInput = $("input[placeholder='Название товара']");
    private final SelenideElement regionSelectorButton = $("div[class*='SeoRegionSelector']");
    private final SelenideElement searchButton = $x("//span[text()='Найти']");
        private final SelenideElement checkboxKrasnodar = $x("//*[text()='г. Краснодар']");
    private final SelenideElement checkboxMoscow = $x("//*[text()='г. Москва']");
    private final SelenideElement buttonSubmitRegion = $x("//span[text()='Применить']");

    public CatalogPage openPage(String url) {
        Selenide.open(url);
        return this;
    }

    public CatalogPage enterSearchQuery(String query) {
        searchInput.setValue(query);
        return this;
    }

    public CatalogPage openRegionSelector() {
        regionSelectorButton.click();
        return this;
    }

    public CatalogPage toggleKrasnodar() {
        checkboxKrasnodar.click();
        return this;
    }

    public CatalogPage toggleMoscow() {
        checkboxMoscow.click();
        return this;
    }

    public CatalogPage applyRegion() {
        buttonSubmitRegion.click();
        return this;
    }

    public CatalogPage clickSearch() {
        searchButton.click();
        return this;
    }
}