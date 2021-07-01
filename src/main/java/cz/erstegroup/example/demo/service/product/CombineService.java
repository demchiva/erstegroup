package cz.erstegroup.example.demo.service.product;

import cz.erstegroup.example.demo.model.account.TransparentAccount;
import cz.erstegroup.example.demo.model.account.TransparentAccountWrapper;
import cz.erstegroup.example.demo.model.combined.CombinedProduct;
import cz.erstegroup.example.demo.model.combined.CombinedResult;
import cz.erstegroup.example.demo.model.combined.CombinedResults;
import cz.erstegroup.example.demo.model.product.Product;
import cz.erstegroup.example.demo.model.product.ProductWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The implementation of combined service base.
 */
public class CombineService implements CombineServiceBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CombineService.class);

    @Override
    public CombinedResults combineAccountsWithProduct(final TransparentAccountWrapper accountWrapper, final ProductWrapper productWrapper) {
        final CombinedResults results = new CombinedResults();
        results.setCombinedResults(getCombinedResults(accountWrapper, productWrapper));
        return results;
    }

    @Override
    public CombinedResults combineAccountsWithProductMoreThanAmount(final TransparentAccountWrapper accountWrapper, final ProductWrapper productWrapper, final Double amount) {
        final CombinedResults results = new CombinedResults();
        List<CombinedResult> combinedResultsList = getCombinedResults(accountWrapper, productWrapper);
        combinedResultsList
                // For each account must filter products with amount greater then given amount
            .forEach((CombinedResult element) -> element.setProducts(
                element
                    .getProducts()
                    .stream()
                    .filter((final CombinedProduct product) -> {
                        boolean shouldFilter = product.getAmount() > amount;

                        if (shouldFilter) {
                            LOGGER.info("Product " + product + "was filtered!");
                        }

                        return shouldFilter;
                    })
                    .collect(Collectors.toList())
            )
        );
        results.setCombinedResults(combinedResultsList);
        return results;
    }

    private List<CombinedResult> getCombinedResults(final TransparentAccountWrapper accountWrapper, final ProductWrapper productWrapper) {
        return accountWrapper.getAccounts().stream().map((TransparentAccount account) -> {
            final Double balance = Double.parseDouble(account.getBalance());
            final String accountNumber = account.getAccountNumber();

            final CombinedResult combinedResult = new CombinedResult();
            combinedResult.setAccountNumber(accountNumber);
            combinedResult.setProducts(getCombinedProducts(balance, productWrapper));

            LOGGER.info("Account " + accountNumber + " has a products value " + combinedResult.getProducts());

            return combinedResult;
        }).collect(Collectors.toList());
    }

    private List<CombinedProduct> getCombinedProducts(final Double balance, final ProductWrapper productWrapper) {
        return productWrapper.getProducts().stream().map((Product product) -> {
            final Double price = Double.parseDouble(product.getValue());

            double productCountForCurrentBalance = 0.0;

            // Evaluate how much product (amount) could be bought for current account balance.
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
