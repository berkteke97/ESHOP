package com.example.teke.ESHOP.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

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
    private String username;		//  username
    private String password;		//  Password
    private String name;			//  Name
    private String surname;			//  surname
    private String email;		    //  Email
    private String phone;		        //  phone number
    private String address;         //  Address
    private Boolean agreement;
    private Boolean activeOrder = false;    //
}
