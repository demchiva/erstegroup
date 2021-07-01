package cz.erstegroup.example.demo.service.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * The implementation of ApiCaller for czech statistical center.
 */
public class ErsteGroupConsumingRestService implements ApiCaller {
    @Autowired
    private RestTemplate restTemplate;

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
