package com.example.teke.ESHOP.repository;

import com.example.teke.ESHOP.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {

    Product findByBarcode(String barcode);

    Iterable<Product> findByCategoryName(String categoryName);

    Optional<Product> findById(UUID id);

}
