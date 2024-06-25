package com.miroservice.product.controller;

import com.miroservice.product.dto.ProductRequest;
import com.miroservice.product.dto.ProductResponse;
import com.miroservice.product.model.Product;
import com.miroservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
        return new ResponseEntity<>(productService.getProductDeatils(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getProductList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct1(@RequestBody ProductRequest productRequest) {
        productService.createProduct1(productRequest);
    }


    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
