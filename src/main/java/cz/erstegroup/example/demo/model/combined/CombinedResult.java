package cz.erstegroup.example.demo.model.combined;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
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
