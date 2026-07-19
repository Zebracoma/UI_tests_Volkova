package otc_ui.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = ConfigProvider.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("Файл application.properties не найден в resources");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла конфигурации", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getSearchItem() {
        return properties.getProperty("search.item");
    }

    public static String getSearchCity() {
        return properties.getProperty("search.city");
    }
}