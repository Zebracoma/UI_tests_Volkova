package otc_ui.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import otc_ui.config.ConfigProvider;
import otc_ui.core.BaseTest;
import otc_ui.pages.CatalogPage;
import otc_ui.util.FileReportUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SearchTest extends BaseTest {

    @Test
    @DisplayName("Поиск товаров, смена региона и парсинг результатов пагинации")
    public void testSearchAndFilter() {
        CatalogPage catalogPage = new CatalogPage();

        catalogPage.openPage(ConfigProvider.getBaseUrl())
                .openRegionSelector()
                .toggleKrasnodar()
                .toggleMoscow()
                .applyRegion(ConfigProvider.getSearchCity())
                .enterSearchQuery(ConfigProvider.getSearchItem())
                .clickSearch();

        List<String> allProducts = new ArrayList<>(catalogPage.collectProductsData());

        catalogPage.goToSecondPage();

        allProducts.addAll(catalogPage.collectProductsData());

        Path filePath = Paths.get("target/products.txt");
        FileReportUtil.saveToFile(filePath, allProducts);

        Assertions.assertTrue(Files.exists(filePath), "Файл products.txt не был создан!");
        Assertions.assertFalse(allProducts.isEmpty(), "Список товаров пуст! Проверь локаторы сбора.");
    }
}