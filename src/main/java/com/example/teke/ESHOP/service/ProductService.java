package com.example.teke.ESHOP.service;

import com.example.teke.ESHOP.dto.ProductDTO;
import com.example.teke.ESHOP.model.Product;
import com.example.teke.ESHOP.other.ContentBasedRecommender;
import com.example.teke.ESHOP.repository.ProductRepository;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.RuntimeMBeanException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    ContentBasedRecommender recommender = new ContentBasedRecommender();

    @Autowired
    ProductRepository productRepository;

    public Product addProduct(ProductDTO productDTO) throws Exception {

        Product existProduct = productRepository.findByBarcode(productDTO.getBarcode());

        if (!ObjectUtils.isEmpty(existProduct)) {
            throw new RuntimeException("Product exist!");
        }

        Product newProduct = new Product();

        UUID id = UUID.randomUUID();
        newProduct.setId(id);
        newProduct.setStock(productDTO.getStock());
        newProduct.setBarcode(productDTO.getBarcode());
        newProduct.setBrand(productDTO.getBrand());
        newProduct.setDetail(productDTO.getDetail());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setCategoryName(productDTO.getCategoryName());
        newProduct.setImageUrl(productDTO.getImageUrl());

        Product product = productRepository.save(newProduct);
        return product;
    }

    public Product updateProduct(ProductDTO productDTO) {
        Product product = productRepository.findByBarcode(productDTO.getBarcode());

        if (ObjectUtils.isEmpty(product)) {
            throw new RuntimeException("Product not exist!");
        }
        product.setImageUrl(productDTO.getImageUrl());
        product.setPrice(product.getPrice());
        product.setDetail(product.getDetail());
        product.setStock(product.getStock());

        Product product1 = productRepository.save(product);
        return product1;
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Iterable<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }


    public Product deleteByBarcode(String barcode) {
        Optional<Product> productDb = Optional.ofNullable(this.productRepository.findByBarcode(barcode));

        this.productRepository.delete(productDb.get());
        return null;
    }

    public Product sellProduct(String barcode, int count) {
        Product existProduct = productRepository.findByBarcode(barcode);
        if (!Objects.nonNull(existProduct)) {
            throw new RuntimeException("Product not exist!");
        }
        if (existProduct.getStock() <= count) {
            throw new RuntimeException("Yeterli ürün yok");
        }
        existProduct.setStock(existProduct.getStock() - count);
        Product product = productRepository.save(existProduct);
        return product;
    }

    public List<Product> productRecommender(Product product) throws IOException, ParseException {
        List<Product> similarProducts = recommender.recommendSimilarProducts(product, 3);
        return similarProducts;
    }


}
