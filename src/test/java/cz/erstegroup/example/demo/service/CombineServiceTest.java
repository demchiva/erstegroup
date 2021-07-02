package cz.erstegroup.example.demo.service;

import cz.erstegroup.example.demo.DemoApplication;
import cz.erstegroup.example.demo.model.account.TransparentAccount;
import cz.erstegroup.example.demo.model.account.TransparentAccountWrapper;
import cz.erstegroup.example.demo.model.combined.CombinedProduct;
import cz.erstegroup.example.demo.model.combined.CombinedResult;
import cz.erstegroup.example.demo.model.combined.CombinedResults;
import cz.erstegroup.example.demo.model.product.Product;
import cz.erstegroup.example.demo.model.product.ProductWrapper;
import cz.erstegroup.example.demo.service.product.CombineServiceBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

@SpringBootTest(classes = {DemoApplication.class})
@AutoConfigureMockMvc()
@ActiveProfiles("test")
public class CombineServiceTest {

    @Autowired
    private CombineServiceBase combineServiceBase;

    private CombinedResults getExpectedCombinedResult() {
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

    private TransparentAccountWrapper getTransparentAccounts() {
        final TransparentAccountWrapper accountWrapper = new TransparentAccountWrapper();
        final TransparentAccount account = new TransparentAccount();
        account.setAccountNumber("123");
        account.setBalance("100");

        accountWrapper.setAccounts(Collections.singletonList(account));
        return accountWrapper;
    }

    private ProductWrapper getProductWrapper() {
        final ProductWrapper productWrapper = new ProductWrapper();
        final Product product = new Product();
        product.setDescription("Rohlik 1kg");
        product.setIdhod("228");
        product.setPlace("Ceska Republika");
        product.setValue("1");

        productWrapper.setProducts(Collections.singletonList(product));

        return productWrapper;
    }

    @Test
    public void testCombineAccountsWithProduct() {
        final CombinedResults expectedResults = getExpectedCombinedResult();
        final CombinedResults combinedResults = combineServiceBase.combineAccountsWithProduct(getTransparentAccounts(), getProductWrapper());
        Assertions.assertEquals(combinedResults.getCombinedResults().get(0).getAccountNumber(), expectedResults.getCombinedResults().get(0).getAccountNumber());
        Assertions.assertEquals(combinedResults.getCombinedResults().get(0).getProducts().get(0).getAmount(), expectedResults.getCombinedResults().get(0).getProducts().get(0).getAmount());
        Assertions.assertEquals(combinedResults.getCombinedResults().get(0).getProducts().get(0).getDescription(), expectedResults.getCombinedResults().get(0).getProducts().get(0).getDescription());
        Assertions.assertEquals(combinedResults.getCombinedResults().get(0).getProducts().get(0).getPlace(), expectedResults.getCombinedResults().get(0).getProducts().get(0).getPlace());
    }

    @Test
    public void testCombineAccountsWithException() {
        Assertions.assertThrows(NullPointerException.class, () -> combineServiceBase.combineAccountsWithProduct(null, getProductWrapper()));
    }

    @Test
    public void combineAccountsWithProductMoreThanAmount() {
        final CombinedResults combinedResults = combineServiceBase.combineAccountsWithProductMoreThanAmount(getTransparentAccounts(), getProductWrapper(), 120.0);
        Assertions.assertEquals(combinedResults.getCombinedResults().get(0).getProducts(), Collections.emptyList());
        Assertions.assertEquals(combinedResults.getCombinedResults().get(0).getAccountNumber(), "123");
    }
}
