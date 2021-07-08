package cz.erstegroup.example.demo.controller.combine_products;

import cz.erstegroup.example.demo.DemoApplication;
import cz.erstegroup.example.demo.model.account.TransparentAccountWrapper;
import cz.erstegroup.example.demo.model.combined.CombinedProduct;
import cz.erstegroup.example.demo.model.combined.CombinedResult;
import cz.erstegroup.example.demo.model.combined.CombinedResults;
import cz.erstegroup.example.demo.model.product.ProductWrapper;
import cz.erstegroup.example.demo.service.consumer.caller.ApiCaller;
import cz.erstegroup.example.demo.service.product.CombineServiceBase;
import cz.erstegroup.example.demo.utils.properties.ConsumerPropertiesUtils;
import cz.erstegroup.example.demo.utils.properties.PropertiesReader;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

@SpringBootTest(classes = {DemoApplication.class})
@AutoConfigureMockMvc()
@ActiveProfiles("test")
class CombineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CombineServiceBase combineServiceBase;

    @MockBean
    private PropertiesReader propertiesReader;

    @MockBean
    @Qualifier("ersteGroupConsumingRestService")
    private ApiCaller ersteApiCaller;

    @MockBean
    @Qualifier("czechStatisticalCenterConsumerRestService")
    private ApiCaller cscApiCaller;

    private CombinedResults getCombinedResult() {
        final CombinedResults results = new CombinedResults();
        final CombinedResult combinedResult = new CombinedResult();
        final CombinedProduct combinedProduct = new CombinedProduct();

        combinedResult.setAccountNumber("123");
        combinedProduct.setPlace("Ceska Republika");
        combinedProduct.setDescription("Rohlik 1kg");
        combinedProduct.setAmount(100);

        combinedResult.setProducts(Collections.singletonList(combinedProduct));
        results.setCombinedResults(Collections.singletonList(combinedResult));
        return results;
    }

    @BeforeEach
    public void before() {
        final CombinedResults results = getCombinedResult();

        when(combineServiceBase.combineAccountsWithProduct(any(), any())).thenReturn(results);
        when(combineServiceBase.combineAccountsWithProductMoreThanAmount(any(), any(), anyDouble())).thenReturn(results);

        when(ersteApiCaller.getResult(any(), any())).thenReturn(new TransparentAccountWrapper());
        when(cscApiCaller.getResult(any(), any())).thenReturn(new ProductWrapper());

        when(propertiesReader.getProperty(eq(ConsumerPropertiesUtils.TRANSPARENT_ACCOUNTS_URL_PROPERTY_NAME))).thenReturn("https://www.google.com");
        when(propertiesReader.getProperty(eq(ConsumerPropertiesUtils.ERSTE_GROUP_TOKEN_PROPERTY_NAME))).thenReturn("dawdawdawdawdawd");
        when(propertiesReader.getProperty(eq(ConsumerPropertiesUtils.AVERAGE_CONSUMER_PRICES_URL_PROPERTY_NAME))).thenReturn("https://www.google.com");
        when(propertiesReader.getProperty(eq(ConsumerPropertiesUtils.CZECH_STATISTICAL_CENTER_TOKEN_PROPERTY_NAME))).thenReturn("dawdawdawdawdawd");
    }

    @Test
    public void testCombineAccountsWithProduct() throws Exception {
        final CombinedResults results = getCombinedResult();
        mockMvc.perform(MockMvcRequestBuilders.get("/combinedResults"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.combinedResults.[*]", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.combinedResults.[0].accountNumber").value(results.getCombinedResults().get(0).getAccountNumber()))
                .andExpect(jsonPath("$.combinedResults.[0].products.[0].amount").value(results.getCombinedResults().get(0).getProducts().get(0).getAmount()))
                .andExpect(jsonPath("$.combinedResults.[0].products.[0].description").value(results.getCombinedResults().get(0).getProducts().get(0).getDescription()))
                .andExpect(jsonPath("$.combinedResults.[0].products.[0].place").value(results.getCombinedResults().get(0).getProducts().get(0).getPlace()))
                .andReturn();
    }

    @Test
    public void testCombineAccountsWithProductGreaterThenGivenAmountWithSuccess() throws Exception {
        final CombinedResults results = getCombinedResult();
        mockMvc.perform(MockMvcRequestBuilders.get("/combinedProductsMoreThanAmount").queryParam("amount", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.combinedResults.[*]", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.combinedResults.[0].accountNumber").value(results.getCombinedResults().get(0).getAccountNumber()))
                .andExpect(jsonPath("$.combinedResults.[0].products.[0].amount").value(results.getCombinedResults().get(0).getProducts().get(0).getAmount()))
                .andExpect(jsonPath("$.combinedResults.[0].products.[0].description").value(results.getCombinedResults().get(0).getProducts().get(0).getDescription()))
                .andExpect(jsonPath("$.combinedResults.[0].products.[0].place").value(results.getCombinedResults().get(0).getProducts().get(0).getPlace()))
                .andReturn();
    }

    @Test
    public void testCombineAccountsWithProductGreaterThenGivenAmountWithError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/combinedProductsMoreThanAmount").queryParam("amount", "-2"))
                .andExpect(status().isBadRequest());
    }
}
