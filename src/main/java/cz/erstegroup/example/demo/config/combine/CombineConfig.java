package cz.erstegroup.example.demo.config.combine;

import cz.erstegroup.example.demo.service.consumer.ApiCaller;
import cz.erstegroup.example.demo.service.consumer.CzechStatisticalCenterConsumerRestService;
import cz.erstegroup.example.demo.service.consumer.ErsteGroupConsumingRestService;
import cz.erstegroup.example.demo.service.product.CombineService;
import cz.erstegroup.example.demo.service.product.CombineServiceBase;
import cz.erstegroup.example.demo.utils.properties.ConsumerPropertiesUtils;
import cz.erstegroup.example.demo.utils.properties.PropertiesReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Configuration class for consuming external REST API.
 */
@Configuration
@EnableCaching
public class CombineConfig {
//
//    /**
//     * Caller for the Erste transparent account REST services.
//     * @return
//     */
//    @Bean(name = "ersteGroupConsumingRestService")
//    public ApiCaller ersteGroupConsumingRestService() {
//        return new ErsteGroupConsumingRestService();
//    }
//
//    /**
//     * Caller for the czech statistical center REST services.
//     * @return
//     */
//    @Bean(name = "czechStatisticalCenterConsumerRestService")
//    public ApiCaller czechStatisticalCenterConsumerRestService() {
//        return new CzechStatisticalCenterConsumerRestService();
//    }

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
