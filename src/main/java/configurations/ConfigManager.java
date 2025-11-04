package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties;
    static {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }
    public static String getProperty(String key) {
        String value = System.getProperty(key);
        return value != null ? value : properties.getProperty(key);
    }
}
