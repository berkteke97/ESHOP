package com.example.teke.ESHOP.repository;

import com.example.teke.ESHOP.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

    Customer findByUsername(String username);

}