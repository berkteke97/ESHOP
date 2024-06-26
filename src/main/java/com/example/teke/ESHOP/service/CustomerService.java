package com.example.teke.ESHOP.service;

import com.example.teke.ESHOP.dto.CustomerDTO;
import com.example.teke.ESHOP.model.Customer;
import com.example.teke.ESHOP.repository.CustomerRepository;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import java.util.Objects;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer addCustomer(CustomerDTO customerDTO) throws Exception {
        Customer existCustomer = customerRepository.findByUsername(customerDTO.getUsername());

        if (Objects.nonNull(existCustomer)){
            throw new RuntimeException("Customer exist");
        }

        Customer newCustomer = new Customer();
        newCustomer.setId(UUID.randomUUID());
        newCustomer.setUsername(customerDTO.getUsername());
        newCustomer.setPassword(customerDTO.getPassword());
        newCustomer.setName(customerDTO.getName());
        newCustomer.setSurname(customerDTO.getSurname());
        newCustomer.setEmail(customerDTO.getEmail());
        newCustomer.setPhone(customerDTO.getPhone());
        newCustomer.setAddress(customerDTO.getAddress());
        newCustomer.setUser_role(customerDTO.getUser_role());

        return customerRepository.save(newCustomer);
    }   

    public Customer updateCustomer(CustomerDTO customerDTO) throws Exception{

        Customer existCustomer = customerRepository.findByUsername(customerDTO.getUsername());
        if (!Objects.nonNull(existCustomer)){
            throw new RuntimeException("Customer not exist");
        }

        existCustomer.setName(customerDTO.getName());
        existCustomer.setSurname(customerDTO.getSurname());
        existCustomer.setAddress(customerDTO.getAddress());
        existCustomer.setEmail(customerDTO.getEmail());
        existCustomer.setPassword(customerDTO.getPassword());
        existCustomer.setPhone(customerDTO.getPhone());

        return customerRepository.save(existCustomer);
    }
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

/*
    public Boolean login(String username, String password) {

        SecurityConfig securityConfig = new SecurityConfig();


        Customer existCustomer = customerRepository.findByUsername(username);
        if (existCustomer.getUsername().equals(username) && existCustomer.getPassword().equals(password)) {

            return true;
            //securityConfig.userDetailsService().loadUserByUsername().getPassword();
        }
        return false;
    }


*/
}
