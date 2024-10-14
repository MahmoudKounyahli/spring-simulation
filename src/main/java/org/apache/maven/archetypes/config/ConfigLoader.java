package org.apache.maven.archetypes.config;


import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class ConfigLoader {
    private static ConfigLoader instance;

    private String databaseFileUrl;

    private ConfigLoader() {
        loadProperties();
    }

    public static ConfigLoader getInstance() {
        if (Objects.isNull(instance)) {
            return new ConfigLoader();
        }
        return instance;
    }

    private void loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("META-INF/application.properties")) {
            if (Objects.isNull(input)) {
                return;
            }
            properties.load(input);
            databaseFileUrl = properties.getProperty("database.txt.file.url");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getDatabaseFileUrl() {
        return databaseFileUrl;
    }

}
