package com.example.teke.ESHOP.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private BigDecimal price;
    private String categoryName;            //Category classındaki name ile eşdeğer ise
    private String brand;
    private String imageUrl;
    private int stock;
    private String detail;
    private String barcode;

}
