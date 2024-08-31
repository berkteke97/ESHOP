package com.example.teke.ESHOP.controller;

import com.example.teke.ESHOP.dto.ProductDTO;
import com.example.teke.ESHOP.model.Product;
import com.example.teke.ESHOP.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;


    /*
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody ProductDTO productDTO) throws Exception{
        return productService.addProduct(productDTO);
    }*/

    @PostMapping("/addProduct")
    public Product addProduct(@ModelAttribute ProductDTO productDTO) throws Exception {
        return productService.addProduct(productDTO);
    }

    @PostMapping("/updateProduct")
    public Product updateProduct(@RequestBody ProductDTO productDTO) throws Exception{
        return productService.updateProduct(productDTO);
    }

    @GetMapping
    public Iterable<Product> getAllProducts (){
        return productService.getAllProducts();
    }
    @GetMapping("products/{categoryName}")
    public Iterable<Product> getProductsByCategory(@PathVariable("categoryName") String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }


    @DeleteMapping("/deleteProduct/{barcode}")
    public Product deleteByBarcode(@PathVariable("barcode") String barcode){
        return productService.deleteByBarcode(barcode);
    }

    @PostMapping("/sellProduct")
    public Product sellProduct(String barcode, int count){
        return productService.sellProduct(barcode, count);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

}
