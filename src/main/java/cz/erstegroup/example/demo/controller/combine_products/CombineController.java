package cz.erstegroup.example.demo.controller.combine_products;

import cz.erstegroup.example.demo.model.account.TransparentAccountWrapper;
import cz.erstegroup.example.demo.model.combined.CombinedResults;
import cz.erstegroup.example.demo.model.product.ProductWrapper;
import cz.erstegroup.example.demo.service.consumer.ExternalApi;
import cz.erstegroup.example.demo.service.product.CombineServiceBase;
import cz.erstegroup.example.demo.utils.converter.JsonConverter;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * The implementation for combine the transparent accounts with products.
 * Accepts the REST requests.
 */
@RestController
public class CombineController implements CombineProductsBaseController {
    private final CombineServiceBase combineService;
    private final ExternalApi externalApi;

    public CombineController(CombineServiceBase combineService, ExternalApi externalApi) {
        this.combineService = combineService;
        this.externalApi = externalApi;
    }

    @Override
    public String getTransparentAccountsWithProducts() throws URISyntaxException {
        final TransparentAccountWrapper accountWrapper = externalApi.getTransparentAccounts();
        final ProductWrapper productWrapper = externalApi.getProducts();

        final CombinedResults combinedResults = combineService.combineAccountsWithProduct(accountWrapper, productWrapper);

        return JsonConverter.convertObjectToJson(combinedResults);
    }

    @Override
    public String getTransparentAccountsWithProductsMoreThanGivenAmount(final Double amount) throws URISyntaxException {
        final TransparentAccountWrapper accountWrapper = externalApi.getTransparentAccounts();
        final ProductWrapper productWrapper = externalApi.getProducts();

        final CombinedResults combinedResults = combineService.combineAccountsWithProductMoreThanAmount(accountWrapper, productWrapper, amount);

        return JsonConverter.convertObjectToJson(combinedResults);
    }
}
