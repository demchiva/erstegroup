package cz.erstegroup.example.demo.service.consumer;

import cz.erstegroup.example.demo.model.account.TransparentAccountWrapper;
import cz.erstegroup.example.demo.model.product.ProductWrapper;
import cz.erstegroup.example.demo.service.consumer.caller.ApiCaller;
import cz.erstegroup.example.demo.utils.properties.ConsumerPropertiesUtils;
import cz.erstegroup.example.demo.utils.properties.PropertiesReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

import static cz.erstegroup.example.demo.config.cache.CacheConfig.PRODUCTS_CACHE_NAME;
import static cz.erstegroup.example.demo.config.cache.CacheConfig.TRANSPARENT_ACCOUNTS_CACHE_NAME;

/**
 * The implementation of external API service.
 */
@Service
public class ExternalApiService implements ExternalApi {
    private static final String ERSTE_GROUP_WEB_API_KEY_QUERY_NAME = "WEB-API-key=";
    private static final String QUERY_MARK = "?";

    private final ApiCaller ersteGroupConsumingRestService;

    private final ApiCaller czechStatisticalCenterConsumerRestServices;

    private final PropertiesReader consumerPropertiesReader;

    public ExternalApiService(@Qualifier("ersteGroupConsumingRestService") ApiCaller ersteGroupConsumingRestService,
                             @Qualifier("czechStatisticalCenterConsumerRestService") ApiCaller czechStatisticalCenterConsumerRestServices,
                             PropertiesReader consumerPropertiesReader) {
        this.ersteGroupConsumingRestService = ersteGroupConsumingRestService;
        this.czechStatisticalCenterConsumerRestServices = czechStatisticalCenterConsumerRestServices;
        this.consumerPropertiesReader = consumerPropertiesReader;
    }

    @Override
    @Cacheable(TRANSPARENT_ACCOUNTS_CACHE_NAME)
    public TransparentAccountWrapper getTransparentAccounts() throws URISyntaxException {
        final URI transparentAccountUri = createTransparentAccountUri();
        return ersteGroupConsumingRestService.getResult(transparentAccountUri, TransparentAccountWrapper.class);
    }

    @Override
    @Cacheable(PRODUCTS_CACHE_NAME)
    public ProductWrapper getProducts() throws URISyntaxException {
        final URI productUri = createProductUri();
        return czechStatisticalCenterConsumerRestServices.getResult(productUri, ProductWrapper.class);
    }

    private URI createTransparentAccountUri() throws URISyntaxException {
        final String url = consumerPropertiesReader.getProperty(ConsumerPropertiesUtils.TRANSPARENT_ACCOUNTS_URL_PROPERTY_NAME);
        final String token = consumerPropertiesReader.getProperty(ConsumerPropertiesUtils.ERSTE_GROUP_TOKEN_PROPERTY_NAME);
        return new URI(url + QUERY_MARK + ERSTE_GROUP_WEB_API_KEY_QUERY_NAME + token);
    }

    private URI createProductUri() throws URISyntaxException {
        final String url = consumerPropertiesReader.getProperty(ConsumerPropertiesUtils.AVERAGE_CONSUMER_PRICES_URL_PROPERTY_NAME);
        return new URI(url);
    }
}
