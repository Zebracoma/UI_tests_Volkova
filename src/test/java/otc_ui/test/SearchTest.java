package otc_ui.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import otc_ui.config.ConfigProvider;
import otc_ui.core.BaseTest;
import otc_ui.pages.CatalogPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchAndFilter() throws IOException {
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

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        allProducts.addAll(catalogPage.collectProductsData());

        Path filePath = Paths.get("target/products.txt");
        Files.write(filePath, allProducts);

        Assertions.assertTrue(Files.exists(filePath), "Файл products.txt не был создан!");
        Assertions.assertFalse(allProducts.isEmpty(), "Список товаров пуст! Проверь локаторы сбора.");

        System.out.println("Собрано товаров суммарно: " + allProducts.size());
        System.out.println("Данные успешно сохранены в: " + filePath.toAbsolutePath());
    }
}