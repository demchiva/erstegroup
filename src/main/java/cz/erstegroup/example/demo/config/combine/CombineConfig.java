package cz.erstegroup.example.demo.config.combine;

import cz.erstegroup.example.demo.service.product.CombineService;
import cz.erstegroup.example.demo.service.product.CombineServiceBase;
import cz.erstegroup.example.demo.utils.properties.ConsumerPropertiesUtils;
import cz.erstegroup.example.demo.utils.properties.PropertiesReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Configuration class for consuming external REST API.
 */
@Configuration
public class CombineConfig {
    /**
     * The properties reader for consuming services.
     * @return
     * @throws IOException
     */
    @Bean
    public PropertiesReader consumerPropertiesUtils() throws IOException {
        return new ConsumerPropertiesUtils();
    }

    /**
     * The service for combine transparent accounts with products.
     * @return
     */
    @Bean(name = "combineService")
    public CombineServiceBase combineServiceBase() {
        return new CombineService();
    }
}
