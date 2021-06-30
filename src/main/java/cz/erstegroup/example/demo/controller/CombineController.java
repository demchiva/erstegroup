package cz.erstegroup.example.demo.controller;

import cz.erstegroup.example.demo.model.account.TransparentAccountWrapper;
import cz.erstegroup.example.demo.model.combined.CombinedResults;
import cz.erstegroup.example.demo.model.product.ProductWrapper;
import cz.erstegroup.example.demo.service.consumer.ApiCaller;
import cz.erstegroup.example.demo.service.product.CombineService;
import cz.erstegroup.example.demo.utils.converter.JsonConverter;
import cz.erstegroup.example.demo.utils.properties.PropertiesReader;
import cz.erstegroup.example.demo.utils.properties.ConsumerPropertiesUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class CombineController {
    private static final String ERSTE_GROUP_WEB_API_KEY_QUERY_NAME = "WEB-API-key=";
    private static final String QUERY_MARK = "?";

    private final ApiCaller ersteGroupConsumingRestService;

    private final ApiCaller czechStatisticalCenterConsumerRestServices;

    private final PropertiesReader consumerPropertiesReader;

    private final CombineService combineService;

    public CombineController(@Qualifier("ersteGroupConsumingRestService") ApiCaller ersteGroupConsumingRestService,
                             @Qualifier("czechStatisticalCenterConsumerRestService") ApiCaller czechStatisticalCenterConsumerRestServices,
                             PropertiesReader consumerPropertiesReader,
                             CombineService combineService) {
        this.ersteGroupConsumingRestService = ersteGroupConsumingRestService;
        this.czechStatisticalCenterConsumerRestServices = czechStatisticalCenterConsumerRestServices;
        this.consumerPropertiesReader = consumerPropertiesReader;
        this.combineService = combineService;
    }

    @ResponseBody
    @GetMapping("/combinedResults")
    public String getTransparentAccountsWithProducts() throws URISyntaxException {
        URI transparentAccountUri = createTransparentAccountUri();
        URI productUri = createProductUri();

        final TransparentAccountWrapper accountWrapper = ersteGroupConsumingRestService.getResult(transparentAccountUri, TransparentAccountWrapper.class);
        final ProductWrapper productWrapper = czechStatisticalCenterConsumerRestServices.getResult(productUri, ProductWrapper.class);

        final CombinedResults combinedResults = combineService.combineAccountsWithProduct(accountWrapper, productWrapper);

        return JsonConverter.convertObjectToJson(combinedResults);
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
