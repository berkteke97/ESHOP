package com.example.teke.ESHOP.controller;

import com.example.teke.ESHOP.dto.CustomerDTO;
import com.example.teke.ESHOP.model.Customer;
import com.example.teke.ESHOP.service.CustomerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/account")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
        return customerService.addCustomer(customerDTO);
    }

    @PostMapping("/updateCustomer")
    public Customer updateCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
        return customerService.updateCustomer(customerDTO);
    }

}

