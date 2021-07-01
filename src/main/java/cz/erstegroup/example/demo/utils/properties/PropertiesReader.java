package cz.erstegroup.example.demo.utils.properties;

import java.io.IOException;

/**
 *  The custom property reader service.
 */
public interface PropertiesReader {
    /**
     * The method loads properties from a given file.
     * @param fileName name of the file in resources folder.
     * @throws IOException when file open problem.
     */
    void loadProperties(final String fileName) throws IOException;

    /**
     * The method give the property by given name.
     * @param propertyName the config property name.
     * @return property value.
     */
    String getProperty(final String propertyName);
}
