package com.example.teke.ESHOP.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.Transient;


@JsonPropertyOrder({
        "id",
        "username",
        "password",
        "name",
        "surname",
        "email",
        "phone",
        "address",
        "agreement",
        "activeOrder"
})
@Data
@Entity
public class Customer {



    @Id
    private UUID id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Boolean agreement;
    private Boolean activeOrder = false;
    private String user_role;

}
