package cz.erstegroup.example.demo.model.combined;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(
        title = "Erste Combined Accounts with Products Response Body",
        description = "Returns details about Erste transparent accounts combined with product."
)
public class CombinedResult implements Serializable {
    private String accountNumber;
    private List<CombinedProduct> products;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<CombinedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CombinedProduct> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CombinedResult{" +
                "accountNumber='" + accountNumber + '\'' +
                ", products=" + products +
                '}';
    }
}
