package cz.erstegroup.example.demo.service;

import cz.erstegroup.example.demo.DemoApplication;
import cz.erstegroup.example.demo.model.account.TransparentAccount;
import cz.erstegroup.example.demo.model.account.TransparentAccountWrapper;
import cz.erstegroup.example.demo.service.consumer.ExternalApiService;
import cz.erstegroup.example.demo.service.consumer.caller.ApiCaller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = {DemoApplication.class})
@AutoConfigureMockMvc()
@ActiveProfiles("test")
class ExternalApiServiceTest {

    @Autowired
    ExternalApiService externalApi;

    @MockBean
    @Qualifier("ersteGroupConsumingRestService")
    private ApiCaller ersteApiCaller;

    @Autowired
    CacheManager cacheManager;

    private static TransparentAccountWrapper getTransparentAccounts(final String accountNumber) {
        final TransparentAccountWrapper accountWrapper = new TransparentAccountWrapper();
        final TransparentAccount account = new TransparentAccount();
        account.setAccountNumber(accountNumber);
        account.setBalance("100");

        accountWrapper.setAccounts(Collections.singletonList(account));
        return accountWrapper;
    }

    @Test
    void testTransparentAccountsCache() throws Exception {
        final TransparentAccountWrapper first = getTransparentAccounts("123");
        final TransparentAccountWrapper second = getTransparentAccounts("234");

        when(ersteApiCaller.getResult(any(), any())).thenReturn(first, second);

        // First invocation
        TransparentAccountWrapper result = externalApi.getTransparentAccounts();
        Assertions.assertEquals(first, result);

        // Second invocation
        result = externalApi.getTransparentAccounts();
        Assertions.assertEquals(first, result);

        // Verify repository method was invoked once
        verify(ersteApiCaller, times(1)).getResult(any(), any());
    }
}
