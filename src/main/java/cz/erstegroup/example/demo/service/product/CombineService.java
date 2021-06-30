package cz.erstegroup.example.demo.service.product;

import cz.erstegroup.example.demo.model.account.TransparentAccount;
import cz.erstegroup.example.demo.model.account.TransparentAccountWrapper;
import cz.erstegroup.example.demo.model.combined.CombinedProduct;
import cz.erstegroup.example.demo.model.combined.CombinedResult;
import cz.erstegroup.example.demo.model.combined.CombinedResults;
import cz.erstegroup.example.demo.model.product.Product;
import cz.erstegroup.example.demo.model.product.ProductWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CombineService {
    public CombinedResults combineAccountsWithProduct(final TransparentAccountWrapper accountWrapper, final ProductWrapper productWrapper) {
        final CombinedResults results = new CombinedResults();
        results.setCombinedResults(getCombinedResults(accountWrapper, productWrapper));
        return results;
    }

    private List<CombinedResult> getCombinedResults(final TransparentAccountWrapper accountWrapper, final ProductWrapper productWrapper) {
        return accountWrapper.getAccounts().stream().map((TransparentAccount account) -> {
            final Double balance = Double.parseDouble(account.getBalance());
            final String accountNumber = account.getAccountNumber();

            final CombinedResult combinedResult = new CombinedResult();
            combinedResult.setAccountNumber(accountNumber);
            combinedResult.setProducts(getCombinedProducts(balance, productWrapper));

            return combinedResult;
        }).collect(Collectors.toList());
    }

    private List<CombinedProduct> getCombinedProducts(final Double balance, final ProductWrapper productWrapper) {
        return productWrapper.getProducts().stream().map((Product product) -> {
            final Double price = Double.parseDouble(product.getValue());

            double productCountForCurrentBalance = 0.0;

            if (balance >= price) {
                productCountForCurrentBalance = balance / price;
            }

            final CombinedProduct combinedProduct = new CombinedProduct();
            combinedProduct.setAmount(productCountForCurrentBalance);
            combinedProduct.setDescription(product.getDescription());
            combinedProduct.setPlace(product.getPlace());

            return combinedProduct;
        }).collect(Collectors.toList());
    }
}
