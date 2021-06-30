package cz.erstegroup.example.demo.service.consumer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public interface ApiCaller {
    RestTemplate restTemplate = new RestTemplate();

    default <T> T getResult(final URI url, final Class<T> responseType) throws RestClientException {
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        return restTemplate.exchange(url, HttpMethod.GET, entity, responseType).getBody();
    }

    HttpHeaders createHeaders();
}
