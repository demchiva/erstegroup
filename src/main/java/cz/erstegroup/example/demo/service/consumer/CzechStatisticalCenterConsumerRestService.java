package cz.erstegroup.example.demo.service.consumer;

import cz.erstegroup.example.demo.utils.properties.ConsumerPropertiesUtils;
import cz.erstegroup.example.demo.utils.properties.PropertiesReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * The implementation of ApiCaller for czech statistical center.
 */
@Service
public class CzechStatisticalCenterConsumerRestService implements ApiCaller {
    private static final String CZECH_STATISTICAL_CENTER_HEADER = "x-api-key";

    private final PropertiesReader consumerPropertiesReader;

    private final RestTemplate restTemplate;

    public CzechStatisticalCenterConsumerRestService(PropertiesReader consumerPropertiesReader, RestTemplate restTemplate) {
        this.consumerPropertiesReader = consumerPropertiesReader;
        this.restTemplate = restTemplate;
    }

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    @Override
    public HttpHeaders createHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        final String token = consumerPropertiesReader.getProperty(ConsumerPropertiesUtils.CZECH_STATISTICAL_CENTER_TOKEN_PROPERTY_NAME);
        headers.set(CZECH_STATISTICAL_CENTER_HEADER, token);

        return headers;
    }
}
