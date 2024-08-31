package com.example.teke.ESHOP.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;
@Data
@Entity
public class Product {

    @Id
    private UUID id;
    private BigDecimal price;
    private String categoryName;            //Category classındaki name ile eşdeğer ise
    private String brand;
    private int stock;
    private byte[] imageUrl;
    private String detail;
    private String barcode;
    //private int counter;

    public Product(){}

    public Product(String barcode, String brand, String categoryName,String detail){
        this.barcode = barcode;
        this.brand = brand;
        this.categoryName = categoryName;
        this.detail = detail;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDetail() {
        return detail;
    }

    public byte[] getImage() {
        return imageUrl;
    }

    public void setImage(byte[] image) {
        this.imageUrl = imageUrl;
    }
}
