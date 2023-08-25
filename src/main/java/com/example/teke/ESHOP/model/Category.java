package com.example.teke.ESHOP.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Category {

    @Id
    private UUID id;
    private String name;

}
