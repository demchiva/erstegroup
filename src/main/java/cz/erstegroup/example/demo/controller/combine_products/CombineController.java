package cz.erstegroup.example.demo.controller.combine_products;

import cz.erstegroup.example.demo.model.account.TransparentAccountWrapper;
import cz.erstegroup.example.demo.model.combined.CombinedResults;
import cz.erstegroup.example.demo.model.product.ProductWrapper;
import cz.erstegroup.example.demo.service.consumer.ApiCaller;
import cz.erstegroup.example.demo.service.product.CombineServiceBase;
import cz.erstegroup.example.demo.utils.converter.JsonConverter;
import cz.erstegroup.example.demo.utils.properties.PropertiesReader;
import cz.erstegroup.example.demo.utils.properties.ConsumerPropertiesUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

import static cz.erstegroup.example.demo.config.cache.CacheConfig.PRODUCTS_CACHE_NAME;
import static cz.erstegroup.example.demo.config.cache.CacheConfig.TRANSPARENT_ACCOUNTS_CACHE_NAME;

/**
 * The implementation for combine the transparent accounts with products.
 * Accepts the REST requests.
 */
@RestController
public class CombineController implements CombineProductsBaseController {
    private static final String ERSTE_GROUP_WEB_API_KEY_QUERY_NAME = "WEB-API-key=";
    private static final String QUERY_MARK = "?";

    private final ApiCaller ersteGroupConsumingRestService;

    private final ApiCaller czechStatisticalCenterConsumerRestServices;

    private final PropertiesReader consumerPropertiesReader;

    private final CombineServiceBase combineService;

    public CombineController(@Qualifier("ersteGroupConsumingRestService") ApiCaller ersteGroupConsumingRestService,
                             @Qualifier("czechStatisticalCenterConsumerRestService") ApiCaller czechStatisticalCenterConsumerRestServices,
                             PropertiesReader consumerPropertiesReader,
                             CombineServiceBase combineService) {
        this.ersteGroupConsumingRestService = ersteGroupConsumingRestService;
        this.czechStatisticalCenterConsumerRestServices = czechStatisticalCenterConsumerRestServices;
        this.consumerPropertiesReader = consumerPropertiesReader;
        this.combineService = combineService;
    }

    @Override
    public String getTransparentAccountsWithProducts() throws URISyntaxException {
        final TransparentAccountWrapper accountWrapper = getTransparentAccounts();
        final ProductWrapper productWrapper = getProducts();

        final CombinedResults combinedResults = combineService.combineAccountsWithProduct(accountWrapper, productWrapper);

        return JsonConverter.convertObjectToJson(combinedResults);
    }

    @Override
    public String getTransparentAccountsWithProductsMoreThanGivenAmount(final Double amount) throws URISyntaxException {
        final TransparentAccountWrapper accountWrapper = getTransparentAccounts();
        final ProductWrapper productWrapper = getProducts();

        final CombinedResults combinedResults = combineService.combineAccountsWithProductMoreThanAmount(accountWrapper, productWrapper, amount);

        return JsonConverter.convertObjectToJson(combinedResults);
    }

    @Cacheable(TRANSPARENT_ACCOUNTS_CACHE_NAME)
    public TransparentAccountWrapper getTransparentAccounts() throws URISyntaxException {
        final URI transparentAccountUri = createTransparentAccountUri();
        return ersteGroupConsumingRestService.getResult(transparentAccountUri, TransparentAccountWrapper.class);
    }

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
