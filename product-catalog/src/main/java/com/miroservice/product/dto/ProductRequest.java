package com.miroservice.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductRequest {

    private Long id;

    private String name;

    private Double price;

    private String description;
}
