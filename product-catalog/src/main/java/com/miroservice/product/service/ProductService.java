package com.miroservice.product.service;

import com.miroservice.product.dao.ProductRepository;
import com.miroservice.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    public Product getProductDeatils(Long id){
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    public void createProduct(Product product){
        productRepository.save(product);
    }

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
