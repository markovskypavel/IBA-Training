package by.iba.markovsky.festivalorganisation.configuration;

import java.io.IOException;
import java.util.Properties;

public class DatabaseConfiguration {

    private static final String PROPERTIES_PATH = "database.properties";
    private static DatabaseConfiguration ourInstance = new DatabaseConfiguration();
    private static final Properties properties = new Properties();
    static{
        try {
            properties.load(DatabaseConfiguration.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH));
        } catch (IOException ioe) {
            throw new ExceptionInInitializerError(ioe.getMessage());
        }
    }

    private DatabaseConfiguration() {
    }

    public static DatabaseConfiguration getInstance() {
        return ourInstance;
    }
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    public static Properties getProperties() {
        return properties;
    }

}
