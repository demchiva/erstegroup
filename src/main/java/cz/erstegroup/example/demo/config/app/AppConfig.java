package cz.erstegroup.example.demo.config.app;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    /**
     * The global app rest template for using in consuming services.
     * @param builder builder for template
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
