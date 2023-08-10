package com.example.teke.ESHOP.controller;

import com.example.teke.ESHOP.dto.CustomerDTO;
import com.example.teke.ESHOP.model.Customer;
import com.example.teke.ESHOP.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.IndexPrefixes;
import org.springframework.web.bind.annotation.*;

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
    public Customer updateCustomer(@RequestBody CustomerDTO customerDTO) throws Exception{
        return customerService.updateCustomer(customerDTO);
    }

    @PostMapping("/login")
    public Boolean login(String username, String password) throws  Exception{
        return customerService.login(username,password);
    }

    @GetMapping("/customers")
    public Iterable<Customer> findAll(){
        return customerService.findAll();
    }





}
