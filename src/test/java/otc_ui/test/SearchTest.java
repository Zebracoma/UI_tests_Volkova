package otc_ui.test;

import org.junit.jupiter.api.Test;
import otc_ui.config.ConfigProvider;
import otc_ui.core.BaseTest;
import otc_ui.pages.CatalogPage;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchAndFilter() {
        CatalogPage catalogPage = new CatalogPage();

        catalogPage.openPage(ConfigProvider.getBaseUrl())
                .openRegionSelector()
                .toggleKrasnodar()
                .toggleMoscow()
                .applyRegion()
                .enterSearchQuery(ConfigProvider.getSearchItem())
                .clickSearch();

    }
}