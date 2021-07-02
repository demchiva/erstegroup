package cz.erstegroup.example.demo.service.product;

import cz.erstegroup.example.demo.model.account.TransparentAccountWrapper;
import cz.erstegroup.example.demo.model.combined.CombinedResults;
import cz.erstegroup.example.demo.model.product.ProductWrapper;
import org.springframework.stereotype.Service;

/**
 * The service base for combine transparent accounts with product functionality.
 */
@Service
public interface CombineServiceBase {
    /**
     * The method combine the transparent accounts with products.
     * @param accountWrapper the wrapper with transparent accounts.
     * @param productWrapper the wrapper with products.
     * @return the combined result.
     */
    CombinedResults combineAccountsWithProduct(final TransparentAccountWrapper accountWrapper, final ProductWrapper productWrapper);

    /**
     * The method combine the transparent accounts with products. Product amount must be greater then given amount.
     * @param accountWrapper the wrapper with transparent accounts.
     * @param productWrapper the wrapper with products.
     * @param amount must be greater then 0 and smaller then 100_000
     * @return the combined result.
     */
    CombinedResults combineAccountsWithProductMoreThanAmount(final TransparentAccountWrapper accountWrapper, final ProductWrapper productWrapper, final Double amount);
}
