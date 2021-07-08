package cz.erstegroup.example.demo.service.consumer;

import cz.erstegroup.example.demo.model.account.TransparentAccountWrapper;
import cz.erstegroup.example.demo.model.product.ProductWrapper;

import java.net.URISyntaxException;

public interface ExternalApi {
    /**
     * The method call erste group external API and get the transparent accounts.
     * @return the wrapper of list of accounts.
     * @throws URISyntaxException on a bad uri.
     */
    public TransparentAccountWrapper getTransparentAccounts() throws URISyntaxException;

    /**
     * The method call czech statistic center external API and get the transparent accounts.
     * @return the wrapper of list of products.
     * @throws URISyntaxException on a bad uri.
     */
    public ProductWrapper getProducts() throws URISyntaxException;
}
