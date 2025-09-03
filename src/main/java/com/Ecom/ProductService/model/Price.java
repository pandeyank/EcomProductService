package com.Ecom.ProductService.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Price extends BaseModel {
    private String currency;
    private double amount;
    private double discount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
