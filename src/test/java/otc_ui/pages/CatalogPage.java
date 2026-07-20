package otc_ui.pages;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {

    private final SelenideElement searchInput = $("input[placeholder='Название товара']");
    private final SelenideElement regionSelectorButton = $("div[class*='SeoRegionSelector']");
    private final SelenideElement searchButton = $x("//span[text()='Найти']");
    private final SelenideElement checkboxKrasnodar = $x("//*[text()='г. Краснодар']");
    private final SelenideElement checkboxMoscow = $x("//*[text()='г. Москва']");
    private final SelenideElement buttonSubmitRegion = $x("//span[text()='Применить']");

    private final ElementsCollection productNames = $$("a[itemprop='name']");
    private final ElementsCollection productPrices = $$("[itemprop='price']");

    private final SelenideElement pageTwoButton = $x("//a[text()='2']");

    public CatalogPage openPage(String url) {
        Selenide.open(url);
        return this;
    }

    public CatalogPage enterSearchQuery(String query) {
        searchInput.setValue(query);

        searchInput.shouldHave(value(query));

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

    public CatalogPage applyRegion(String expectedCity) {
        buttonSubmitRegion.click();

        regionSelectorButton.shouldHave(text(expectedCity));

        return this;
    }

    public CatalogPage clickSearch() {
        searchButton.click();
        return this;
    }

    public List<String> collectProductsData() {
        List<String> parsedProducts = new ArrayList<>();

        productNames.shouldHave(CollectionCondition.sizeGreaterThan(0));

        for (int i = 0; i < productNames.size(); i++) {
            String name = productNames.get(i).getText();
            String rawPrice = productPrices.get(i).getText();

            String cleanPrice = rawPrice.replaceAll("[^0-9,]", "");

            parsedProducts.add(name + ", " + cleanPrice + " Руб/Штука");
        }
        return parsedProducts;
    }

    public CatalogPage goToSecondPage() {
        pageTwoButton.scrollTo().click();
        return this;
    }
}