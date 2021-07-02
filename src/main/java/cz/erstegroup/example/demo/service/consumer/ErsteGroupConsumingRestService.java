package cz.erstegroup.example.demo.service.consumer;

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
public class ErsteGroupConsumingRestService implements ApiCaller {
    private final RestTemplate restTemplate;

    public ErsteGroupConsumingRestService(RestTemplate restTemplate) {
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
        return headers;
    }
}
