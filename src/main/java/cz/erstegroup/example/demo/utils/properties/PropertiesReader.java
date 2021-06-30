package cz.erstegroup.example.demo.utils.properties;

import java.io.IOException;

public interface PropertiesReader {
    void loadProperties(final String fileName) throws IOException;
    String getProperty(final String propertyName);
}
