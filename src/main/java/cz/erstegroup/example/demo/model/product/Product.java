package cz.erstegroup.example.demo.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private String idhod;
    @JsonProperty("hodnota")
    private String value;
    @JsonProperty("reprcen_txt")
    private String description;
    @JsonProperty("uzemi_txt")
    private String place;

    public String getIdhod() {
        return idhod;
    }

    public void setIdhod(String idhod) {
        this.idhod = idhod;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idhod='" + idhod + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
