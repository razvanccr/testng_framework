package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public Properties loadProperties(String resourceName) {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName)) {

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("Unable to find resource: " + resourceName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
