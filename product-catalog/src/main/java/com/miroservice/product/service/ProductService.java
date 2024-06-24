package com.miroservice.product.service;

import com.miroservice.product.ProductServiceApp;
import com.miroservice.product.dao.ProductRepository;
import com.miroservice.product.dto.ProductRequest;
import com.miroservice.product.dto.ProductResponse;
import com.miroservice.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void createProduct1(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();

        productRepository.save(product);
    }

    public List<ProductResponse> getProductList(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

    }

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
