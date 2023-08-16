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
    private String imageUrl;
    private int stock;
    private String detail;
    private String barcode;



}
