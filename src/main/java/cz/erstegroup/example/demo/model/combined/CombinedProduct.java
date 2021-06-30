package cz.erstegroup.example.demo.model.combined;

import javax.persistence.Entity;

@Entity
public class CombinedProduct {
    private double amount;
    private String description;
    private String place;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
        return "CombinedProduct{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
