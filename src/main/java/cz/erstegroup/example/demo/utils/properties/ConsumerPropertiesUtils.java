package cz.erstegroup.example.demo.utils.properties;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Properties;

@Service
public class ConsumerPropertiesUtils implements PropertiesReader {
    public static final String FILE_NAME = "config/consumer.properties";

    public static final String TRANSPARENT_ACCOUNTS_URL_PROPERTY_NAME = "transparentAccountsUrl";
    public static final String PLACES_URL_PROPERTY_NAME = "placesUrl";
    public static final String AVERAGE_CONSUMER_PRICES_URL_PROPERTY_NAME = "averageConsumerPricesUrl";

    public static final String ERSTE_GROUP_TOKEN_PROPERTY_NAME = "ersteGroupWebApiToken";
    public static final String CZECH_STATISTICAL_CENTER_TOKEN_PROPERTY_NAME = "czechStatisticalCenterWebApiToken";

    private Properties props;

    ConsumerPropertiesUtils() throws IOException {
        loadProperties(FILE_NAME);
    }

    public String getProperty(final String propertyName) {
        return props.getProperty(propertyName);
    }

    public void loadProperties(final String fileName) throws IOException {
        props = new Properties();
        File file = ResourceUtils.getFile("classpath:" + fileName);
        InputStream in = new FileInputStream(file);
        props.load(in);
    }
}
