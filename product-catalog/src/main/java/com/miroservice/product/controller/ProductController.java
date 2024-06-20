package com.miroservice.product.controller;

import com.miroservice.product.model.Product;
import com.miroservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
        return new ResponseEntity<>(productService.getProductDeatils(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
