package utils;

import org.tinylog.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class ConfigurationProperties {
    private static final String CONFIG_FILE =  "C:\\Users\\giuse\\IdeaProjects\\Watchers\\config.properties";
    private static final Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
            Logger.debug("URI: {}, USER: {}, PSW: {}", getUrl(), getUsername(), getPassword());
        } catch (IOException e) {
            Logger.error(e, "Cannot load configuration file: {}", CONFIG_FILE);
        }
    }

    public static String getUrl() {
        return properties.getProperty("db.url");
    }

    public static String getUsername() {
        return properties.getProperty("db.username");
    }

    public static String getPassword() {
        return properties.getProperty("db.password");
    }
}
