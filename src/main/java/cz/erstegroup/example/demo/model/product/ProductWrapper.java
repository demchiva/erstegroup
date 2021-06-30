package cz.erstegroup.example.demo.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductWrapper {
    @JsonProperty("data")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductWrapper{" +
                "products=" + products +
                '}';
    }
}
