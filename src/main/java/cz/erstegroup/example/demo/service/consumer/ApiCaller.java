package cz.erstegroup.example.demo.service.consumer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * The base for rest consuming services.
 */
public interface ApiCaller {
    /**
     * The method give an object of given type as result after external API call.
     * @param url the url of REST API endpoint.
     * @param responseType type of the object
     * @return the object given type
     * @throws RestClientException on api call error
     */
    default <T> T getResult(final URI url, final Class<T> responseType) throws RestClientException {
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        return getRestTemplate().exchange(url, HttpMethod.GET, entity, responseType).getBody();
    }

    /**
     * The method return rest template for API call.
     * @return
     */
    RestTemplate getRestTemplate();

    /**
     * The method create headers for current API call.
     * @return
     */
    HttpHeaders createHeaders();
}
