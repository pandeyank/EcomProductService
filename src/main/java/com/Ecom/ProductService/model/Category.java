package com.Ecom.ProductService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String catInfo;
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
    public String getCatInfo() {
        return catInfo;
    }

    public void setCatInfo(String catInfo) {
        this.catInfo = catInfo;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
